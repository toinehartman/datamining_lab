import java.util.ArrayList;
import java.util.List;

public class FeatureVector extends ArrayList<Double> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Label of the feature.
	 */
	int label;

	/**
	 * Constructor
	 * @param l The label of this feature vector.
	 */
	public FeatureVector(int l) {
		label = l;
	}
	
	/**
	 * Default constructor.
	 */
	public FeatureVector() {
		label = -1;
	}
	
	/**
	 * Sets label.
	 * @param label
	 */
	public void setLabel(int label) {
		this.label = label;
	}

	/**
	 * @return Returns the label.
	 */
	public int getLabel() {
		return label;
	}

	/**
	 * Calculates the (Euclidean) distance between the given vector and this feature vector.
	 * @param vector The vector to calculate the distance to.
	 * @return The distance to vector.
	 */
	public double distance(List<Double> vector) {
		assert(vector.size() == size());

		double result = 0.0;
		for (int i = 0; i < vector.size(); i++) {
			result += Math.pow(vector.get(i) - get(i), 2);
		}

		return Math.sqrt(result);
	}
	
	/**
	 * Sums up values in fv to this vector.
	 * @param fv The values to add.
	 */
	@SuppressWarnings("unused")
	public void sum(FeatureVector fv) {
		if (size() == 0) {
			for (Double d: fv)
				add(0.0);
		}
		
		assert(fv.size() == this.size());
		
		for (int i = 0; i < size(); i++) {
			set(i, get(i) + fv.get(i));
		}
	}
	
	/**
	 * Divides all values by d.
	 * @param d
	 */
	public void divide(double d) {
		assert(d != 0.0);
		
		for (int i = 0; i < size(); i++) {
			set(i, get(i) / d);
		}
	}

	/**
	 * Converts this object to a String object.
	 */
	@Override
	public String toString() {
		return "<" + label + ", " + super.toString() + ">";
	}
}
