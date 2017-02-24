
/**
 * Helper class. Useful for sorting FeatureVectors based on distances.
 */
class Measurement implements Comparable<Measurement> {
	/**
	 * Distance between the query and the feature vector.
	 */
	double distance;

	/**
	 * The feature vector.
	 */
	FeatureVector featureVector;

	/**
	 * Constructor.
	 * @param fv The feature vector.
	 * @param dist The distance of the query point to the feature vector.
	 */
	public Measurement(FeatureVector fv, double dist) {
		featureVector = fv;
		distance = dist;
	}

	/**
	 * @return Returns the distance value.
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Returns the FeatureVector object.
	 * @return
	 */
	public FeatureVector getFeatureVector() {
		return featureVector;
	}

	/**
	 * Method used to compare two measurements.
	 */
	@Override
	public int compareTo(Measurement o) {
		return o.distance > distance ? -1 : 1;
	}

	/**
	 * Converts a Measurement object to a String.
	 */
	@Override
	public String toString() {
		return "<" + featureVector + ", " + distance + ">";
	}

}