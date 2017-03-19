import java.awt.Color;
import java.awt.Shape;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jfree.util.ShapeUtilities;

public class Cluster extends ArrayList<FeatureVector> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7478823693516467914L;
	
	/**
	 * Is true when an element has changed since last computing the centroid.
	 */
	private boolean changed;
	
	/**
	 * The cached centroid.
	 */
	private FeatureVector centroid;
	
	/**
	 * The color of this cluster in the plot.
	 */
	private Color color;
	
	/**
	 * The shape of this cluster in the plot.
	 */
	private Shape shape;
	
	/**
	 * Constructor.
	 * @param key
	 */
	public Cluster() {
		changed = true;
		color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
		shape = ShapeUtilities.createDiagonalCross(3, 1);
		centroid = null;
	}
	
	/**
	 * @return Returns the color of this cluster.
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * @return Returns the shape of this cluster.
	 */
	public Shape getShape() {
		return shape;
	}
	
	/**
	 * Parses a text file with features and stores them in a cluster.
	 * @param fileName
	 */
	public void readData(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));

			// every line is in the form of "x y", where x and y are double values
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				FeatureVector fv = new FeatureVector();
				for (int i = 0; i < split.length; i++)
					fv.add(Double.parseDouble(split[i]));
				add(fv);
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	/**
	 * Overrides List.add, in order to set the changed variable.
	 */
	public boolean add(FeatureVector fv) {
		changed = true;
		return super.add(fv);
	}
	
	/**
	 * Overrides List.add, in order to set the changed variable.
	 */
	@Override
	public boolean addAll(Collection<? extends FeatureVector> c) {
		changed = true;
		return super.addAll(c);
	}
	
	/**
	 * If the cluster did not change since it has calculated its previous centroid,
	 * it will return the buffered centroid point. Otherwise it will recalculate the
	 * centroid and return that point.
	 * @return The centroid of the cluster.
	 */
	public FeatureVector centroid() {
		if (size() == 0)
			return null;
		
		if(!changed){
			return centroid;
		} else {
		
			int N = this.size();
			FeatureVector newCentroid = new FeatureVector();
			for(FeatureVector fv: this){
				newCentroid.sum(fv);
			}
			
			newCentroid.divide(N);
			centroid = newCentroid;
			return centroid;
		}
	}
	
	/**
	 * Calculates the (mean) distance of a cluster with this cluster.
	 * In other words, it calculates the Euclidean distance between
	 * the two centroids.
	 * @param other The other cluster to which the distance is calculated.
	 * @return The distance between this cluser and the other cluster.
	 */
	public double meanDistanceTo(Cluster other) {
		return this.centroid().distance(other.centroid());
	}
	
	/**
	 * Calculates each pairwise distance from this cluster to another.
	 * @param other The cluster to calculate the distances to.
	 * @return A list of distances from this cluster to other.
	 */
	public List<Double> distancesToCluster(Cluster other) {
		List<Double> result = new ArrayList<Double>();
		
		
		for (int i = 0; i < this.size(); i++) {
			for(int j = i + 1; j < other.size();j++){
				result.add(Math.pow(this.get(i).distance(other.get(j)), 2));
			}
		}
	
		return result;
	}
	
	/**
	 * @return Returns the minimum distance between two clusters.
	 */
	public double minDistanceTo(Cluster other) {
		assert (size() != 0 && other.size() != 0);
		
		double result = Integer.MAX_VALUE;
		for(double dist: distancesToCluster(other)){
			if(dist < result)
				result = dist;
		}
		
		return result;
	}
	
	/**
	 * Calculates the residual sum of squares (squares of difference with centroid).
	 * @return The RSS.
	 */
	public double calculateAverageRSS() {		
		if (size() == 0)
			return Double.NaN;
		
		double rss = 0.0;
		// add code here
		return rss / size();
	}
}
