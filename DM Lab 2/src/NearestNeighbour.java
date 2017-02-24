import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
		
		//TODO: Calculate the distance of the object to each element in the dataset.
		FeatureVector fv = (FeatureVector) features;
		double distance = 1;
		Measurement m = new Measurement(fv,distance);
		
		
		//TODO: Sort them by distance, smallest distance first.
		
		//TODO: Select k of the nearest objects
		
		//TODO: Calculate which label has the highest majority vote
		
		//TODO: Assign the label to that class
		return label;
	}
}
