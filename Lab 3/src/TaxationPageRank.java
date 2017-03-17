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
		int n = data.size();
		Matrix e = new Matrix(n, 1);
		for (int i = 0; i < n; i++)
			e.set(i, 1.0);

		// the result
		HashMap<String, Double> result = new HashMap<String, Double>();

		// the tools
		Matrix transitionMatrix = constructTransitionMatrix();
		Matrix randomSurfer = getRandomSurferVector();
		
		//Iterative step:
        for(int i = 0; i < iterations ; i++)
        	randomSurfer = transitionMatrix
					.multiply(beta)
					.dot(randomSurfer)
					.add(e.multiply((1 - beta) / n));

		// fill the results, match names with PageRank values
		int count = 0;
		for (String s: data.keySet()) {
			result.put(s, randomSurfer.get(count));
			count++;
		}

		return result;
	}
}
