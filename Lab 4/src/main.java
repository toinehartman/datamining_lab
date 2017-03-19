import java.util.List;

public class main {
	
	public static String METHOD;
	
	private static void hierarchical() {
		//	METHOD = "mean";
			METHOD = "min";
		int k = 3;
		new HierarchicalClusteringPlotter(k, "data/cluster.txt");
		
	}
	
	private static void hierarchicalDigits() {
			METHOD = "mean"; 
		//	METHOD = "min";
		int k = 10;
		HierarchicalClustering hc = new HierarchicalClustering(k, "data/train_digits.txt");
		
		//List<Cluster> clusters = hc.getClusters();
		//if(clusters.size() == k){
		//	for(Cluster c: clusters)
		//		new DigitFrame("Centroid voor cluster",c.centroid(),64,64);
		//}
	}
	
	private static void kmeans() {
		// add code here
	}
	
	private static void kmeansTuneK() {
		// add code here
	}
	
	private static void kmeansDigits() {
		// add code here
	}

	public static void main(String[] args) {
		//hierarchical();
		hierarchicalDigits();
		//kmeans();
		//kmeansTuneK();
		//kmeansDigits();
	}

}
