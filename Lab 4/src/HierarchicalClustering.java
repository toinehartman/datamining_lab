import java.util.ArrayList;
import java.util.List;

public class HierarchicalClustering {
	/**
	 * The number of clusters to detect.
	 */
	private int k;
	
	/**
	 * The original unclustered data.
	 */
	private Cluster data;
	
	/**
	 * A collection of clusters.
	 */
	private List<Cluster> clusters;
	
	/**
	 * Constructor.
	 * @param k The number of clusters to detect.
	 * @param fileName The filename at which the data is stored.
	 */
	public HierarchicalClustering(int k, String fileName) {
		this.k = k;
		clusters = new ArrayList<Cluster>();
		
		data = new Cluster();
		
		readData(fileName);
	}
	
	/**
	 * Reads in data from a filename.
	 * @param fileName The filename that needs to be read.
	 */
	public void readData(String fileName) {
		data.readData(fileName);
		
		for (FeatureVector fv: data) {
			Cluster c = new Cluster();
			c.add(fv);
			clusters.add(c);
		}
	}
	
	/**
	 * @return The unclustered data.
	 */
	public Cluster getData() {
		return data;
	}
	
	/**
	 * @return The clusters that have been computed so far.
	 */
	public List<Cluster> getClusters() {
		return clusters;
	}
	
	/**
	 * 
	 * @return Returns the number of clusters in the list of clusters.
	 */
	public int getClusterSize() {
		return clusters.size();
	}
	
	/**
	 * 
	 * @param i Index of the cluster that is to be retrieved.
	 * @return Returns the cluster belonging to index i.
	 */
	public Cluster getCluster(int i) {
		return clusters.get(i);
	}
	
	/**
	 * Performs one update step of the algorithm.
	 */
	public void update() {
		String method = main.METHOD;
		//if there are more than k clusters:
		if(clusters.size() > k){
			Cluster candidateA = null;
			Cluster candidateB = null;
			double minDistance = Integer.MAX_VALUE;
			// check the distance between each pair of clusters
			for(int i = 0; i < clusters.size(); i++){
				Cluster a = getCluster(i);
				for(int j = i + 1; j < clusters.size(); j++){
					Cluster b = getCluster(j);
					double distance;
					
					if(method == "mean"){
						distance = a.meanDistanceTo(b);
					} else {
						distance = a.minDistanceTo(b);
					}
					if(distance <= minDistance){
						candidateA = a;
						candidateB = b;
						minDistance = distance;
					}					
				}
			}
				
			//the two clusters with minimum distance will be merged
			if(candidateA != null){	
				candidateB.addAll(candidateA);
				clusters.remove(candidateA);
				System.out.println(clusters.size());
				candidateA = null;
			}
		}
	}
}
