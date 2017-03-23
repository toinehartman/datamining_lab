import java.util.List;

public class main {
	
	public static String METHOD;
	
	private static void hierarchical() {
			METHOD = "mean";
//			METHOD = "min";
		int k = 3;
		new HierarchicalClusteringPlotter(k, "data/cluster_lines.txt");
		
	}
	
	private static void hierarchicalDigits() {
			METHOD = "mean"; 
		//	METHOD = "min";
		int k = 10;
		HierarchicalClustering hc = new HierarchicalClustering(k, "data/train_digits.txt");

		while (hc.getClusterSize() > 10) hc.update();
		
		List<Cluster> clusters = hc.getClusters();
		for(Cluster c: clusters)
			new DigitFrame("Centroid voor cluster",c.centroid(),8,8);
	}
	
	private static void kmeans() {
		KMeansPlotter kp = new KMeansPlotter(3, "data/cluster.txt");
	}
	
	private static void kmeansTuneK() {
		int min = 1;
		int max = 10;

		for (int k = min; k <= max; k++) {
			KMeans kmeans = new KMeans(k, "data/cluster.txt");
			for (int i = 0; i < 10; i++) kmeans.update();
			System.out.print("Iterations: " + k);
			System.out.print("\t");

			for (int i = 0; i < kmeans.getClusterSize(); i++) System.out.print(kmeans.getCluster(i).calculateAverageRSS() + " ");
			System.out.println();
		}
	}
	
	private static void kmeansDigits() {
		KMeans kmeans = new KMeans(10, "data/train_digits.txt");
		kmeans.update();
		for (int i = 0; i < 100; i++) {
			kmeans.update();
		}
		for (Cluster c : kmeans.getClusters())
			new DigitFrame("Centroid voor cluster", c.centroid(), 8, 8);
	}

	public static void main(String[] args) {
//		hierarchical();
//		hierarchicalDigits();
//		kmeans();
//		kmeansTuneK();
		kmeansDigits();
	}

}
