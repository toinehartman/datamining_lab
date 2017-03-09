import java.util.HashMap;
import java.util.Map;


public class TaxationPageRank extends PageRank {
	/**
	 * The taxation value.
	 */
	double beta;

	/**
	 * Constructor.
	 * @param b The beta parameter for the taxation version of PageRank.
	 */
	public TaxationPageRank(double b) {
		super();

		beta = b;
	}

	/**
	 * Calculates the PageRank using the taxation method.
	 */
	@Override
	public Map<String, Double> calculatePageRank(int iterations) {
		// the result
		HashMap<String, Double> result = new HashMap<String, Double>();

		// the tools
		Matrix randomSurfer = null;
		Matrix transitionMatrix = null;

        // FILL IN YOUR CODE HERE

		// fill the results, match names with PageRank values
		int count = 0;
		for (String s: data.keySet()) {
			result.put(s, randomSurfer.get(count));
			count++;
		}

		return result;
	}
}
