import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class NearestNeighbour {
	/**
	 * List of training vectors.
	 */
	Dataset dataset;

	/**
	 * Constructor.
	 */
	public NearestNeighbour() {
		dataset = new Dataset();
	}

	/**
	 * Reads in the data from a text file.
	 * @param fileName
	 */
	public void readData(String fileName) {
		dataset.readData(fileName, false);
	}

	/**
	 * 
	 * @return
	 */
	public Dataset getDataset() {
		return dataset;
	}

	/**
	 * Classifies a query. Finds the k nearest neighbours and scales them if necessary.
	 * @param features The query features.
	 * @param k The number of neighbours to select.
	 * @return Returns the label assigned to the query.
	 */
	public int predict(List<Double> features, int k) {
		// the result
		int label = -1;
		
		System.out.println("fv = "+features + ", k = "+ k);
		
		//Calculate the distance of the object to each element in the dataset.
		FeatureVector fv = (FeatureVector) features;
		List<Measurement> measurements = new ArrayList<>();
		
		//	iterate through the featureVectors in the dataset to calculate each distance to fv
		Iterator<FeatureVector> iter = dataset.iterator();
		while(iter.hasNext()){
			FeatureVector elem = iter.next();
			double distance = fv.distance(elem);
			Measurement m = new Measurement(elem,distance);
			measurements.add(m);
			
		}
		
		//Sort them by distance, smallest distance first.
		//Use a list of Measurement objects, it can easily be sorted using Collections.sort
		
		Collections.sort(measurements);
		
		//Select k of the nearest objects
		
		for(int i = k; i < measurements.size(); i++)
			measurements.remove(i);
		
		//Calculate which label has the highest majority voted
		
		int teamPositive = 0;
		int teamNegative = 0;
		
		for(Measurement m : measurements){
			if(m.getFeatureVector().getLabel() == 1)
				teamPositive++;
			else{
				teamNegative++;
			}
		}
		
		//TODO: Assign the label to that class
		if(teamPositive > teamNegative)
			label = 1;
		
		return label;
	}
}
