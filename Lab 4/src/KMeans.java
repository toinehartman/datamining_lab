import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KMeans {
	/**
	 * The number of clusters to detect.
	 */
	private int k;
	
	/**
	 * A collection of (k) clusters.
	 */
	private List<Cluster> clusters;
	
	/**
	 * The unclustered data.
	 */
	private Cluster data;

	/**
	 * Constructor.
	 * @param k The number of clusters to detect.
	 * @param fileName The filename which holds the cluster data.
	 */
	public KMeans(int k, String fileName) {
		this.k = k;
		clusters = new ArrayList<Cluster>();
		data = new Cluster();

		readData(fileName);
	}

	/**
	 * @param i The index of the cluster that is to be retrieved.
	 * @return The cluster at index i.
	 */
	public Cluster getCluster(int i) {
		return clusters.get(i);
	}
	
	/**
	 * @return The unclustered data.
	 */
	public Cluster getData() {
		return data;
	}
	
	/**
	 * @return The collection of clusters.
	 */
	public List<Cluster> getClusters() {
		return clusters;
	}
	
	/**
	 * @return The number of clusters.
	 */
	public int getClusterSize() {
		return clusters.size();
	}

	/**
	 * Reads in the data of filename.
	 * @param fileName The file which is to be read.
	 */
	private void readData(String fileName) {
		data.readData(fileName);
	}

	/**
	 * Adds a new init point at a random location in the dataset.
	 */
	private void addInitPoint() {
		int i = Math.min((int)(Math.random() * data.size()), data.size());

		Cluster c = new Cluster();
		FeatureVector initPoint = data.get(i);

		c.add(initPoint);
		clusters.add(c);

//		System.out.println("Random init point: " + i);
	}
	
	/**
	 * Clears all elements from the clusters.
	 */
	private void clearClusters() {
		for (Cluster c: clusters) {
			c.clear();
		}
	}

	/**
	 * Computes clusters based on the centroids of the clusters in the previous round.
	 * If no such clusters exist yet, it will select k random points.
	 */
	public void update() {
		if (clusters.isEmpty()) {
			for (int i = 0; i < k; i++)
				addInitPoint();
		}

		List<FeatureVector> centroids = new ArrayList<>();
		for (Cluster c : clusters)
			centroids.add(c.centroid());

		this.clearClusters();

		for (FeatureVector v : data) {
			int cluster_id = closestCentroid(v, centroids);
			clusters.get(cluster_id).add(v);
			v.setLabel(cluster_id);
		}
	}

	private int closestCentroid(FeatureVector v, List<FeatureVector> centroids) {
		int closest_id = Integer.MAX_VALUE;
		double closest_dist = Integer.MAX_VALUE;

		for (int i = 0; i < centroids.size(); i++) {
			FeatureVector centroid = centroids.get(i);
			double d = v.distance(centroid);

			if (d < closest_dist) {
				closest_id = i;
				closest_dist = d;
			}
		}

		return closest_id;
	}
}
