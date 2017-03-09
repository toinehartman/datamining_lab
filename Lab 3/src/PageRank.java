import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PageRank {
	/**
	 * Container for the data. LinkedHashMap keeps the order of insertion, for easier reading.
	 */
	LinkedHashMap<String, LinkedHashMap<String, Integer>> data;

	/**
	 * Constructor.
	 */
	public PageRank() {
		data = new LinkedHashMap<String, LinkedHashMap<String, Integer>>();
	}

	/**
	 * Imports the data from a text file in the container.
	 * @param fileName The name of the file that is to be imported.
	 */
	public void importData(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));

			// every line is in the form of "source|destination|weight"

			// parse the file once, adding all nodes
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] split = line.split("\\|");
				assert(split.length == 3);

				if (data.get(split[0]) == null)
					data.put(split[0], new LinkedHashMap<String, Integer>());
			}

			// make sure every connection is there, but with a weight of 0
			for (String s: data.keySet()) {
				for (String s2: data.keySet()) {
					data.get(s).put(s2, 0);
				}
			}

			br.close();
			br = new BufferedReader(new FileReader(fileName));

			// parse the file again, but now adding the v
			while ((line = br.readLine()) != null) {
				String[] split = line.split("\\|");
				assert(split.length == 3);

				data.get(split[0]).put(split[1], Integer.parseInt(split[2]));
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructs the transition matrix where each element is the probability that
	 * a random surfer ends up in a vertex (rows) if he departs from some vertex (columns).
	 * @return The transition matrix.
	 */
	public Matrix constructTransitionMatrix() {
		// the resulting matrix
		Matrix transitionMatrix = new Matrix(data.size(), data.size());
        
        // FILL IN YOUR CODE HERE

		return transitionMatrix;
	}

	/**
	 * @return A vector with probabilities 1/n that a random surfer starts at some vertex.
	 */
	public Matrix getRandomSurferVector() {
		Matrix result = new Matrix(data.size(), 1);

        // FILL IN YOUR CODE HERE

		return result;
	}

	/**
	 * Calculates the actual PageRank. This method uses the previous methods to
	 * get the transition matrix and the random surfer vector. It then iteratively
	 * multiplies the transition matrix with the random surfer vector for iterations times.
	 * @param iterations The number of iterations.
	 * @return A map with names and PageRank values.
	 */
	public Map<String, Double> calculatePageRank(int iterations) {
		// the result
		HashMap<String, Double> result = new HashMap<String, Double>();

		// the tools
		Matrix transitionMatrix = null;
		Matrix randomSurfer = null;

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
