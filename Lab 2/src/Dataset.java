import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Dataset extends ArrayList<FeatureVector> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The number of features for each element in the dataset.
	 */
	private int featureSize;
	
	public Dataset() {
		featureSize = -1;
	}
	
	/**
	 * Constructor.
	 */
	public Dataset(String fileName, boolean addThresh) {
		this();
		readData(fileName, addThresh);
	}
	
	/**
	 * @return Returns the feature size.
	 */
	public int getFeatureSize() {
		return featureSize;
	}
	
	/**
	 * Reads in the data from a text file.
	 * @param fileName The name of the text file.
	 * @param addThresh Determines whether to add a value for a threshold.
	 */
	public void readData(String fileName, boolean addThresh) {
		try {
			// read in the text file
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			String line = null;
			while ((line = br.readLine()) != null) {				
				String[] parts = line.split(" ");
				
				if (featureSize == -1)
					featureSize = parts.length - 1;
				
				assert(parts.length == featureSize + 1);

				// add all features in this feature vector
				FeatureVector fv = new FeatureVector(Integer.parseInt(parts[0]));
				for (int i = 1; i < parts.length; i++)
					fv.add(Double.parseDouble(parts[i]));
				if (addThresh)
					fv.add(-1.0); // the threshold

				add(fv);
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
