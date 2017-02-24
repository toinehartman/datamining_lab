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
	 * @return Returns the label.
	 */
	public int getLabel() {
		return label;
	}

	/**
	 * Calculates the product of this feature vector with vector weights.
	 * @param weights The vector with which the product is calculated.
	 * @return The product of the two vectors.
	 */
	public double product(List<Double> weights) {
		assert(weights.size() == size());

		double result = 0.0;
		
		//Inproduct = x1*y1 + x2*y2 + x3*y3 + ... + xd*yd
		for(int i = 0; i < this.size(); i++)
			result += this.get(i) * weights.get(i); 

		return result;
	}

	/**
	 * Calculates the (Euclidean) distance between the given vector and this feature vector.
	 * @param vector The vector to calculate the distance to.
	 * @return The distance to vector.
	 */
	public double distance(List<Double> vector) {
		assert(vector.size() == size());

		double result = 0.0;
		
		//d(X,Y) = SQRT( (x1-y1)^2 + (x2-y2)^2 + (x3-y3)^2 + ... + (xd-yd)^2 )		
		for(int i = 0; i < this.size(); i++)
			result += Math.pow( (this.get(i) - vector.get(i)), 2); 

		return Math.sqrt(result);
	}

	/**
	 * Converts this object to a String object.
	 */
	@Override
	public String toString() {
		return "<" + label + ", " + super.toString() + ">";
	}
}
