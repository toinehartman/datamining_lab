import java.util.*;

/**
 * 
 * @author hansgaiser
 *
 */
public class LSH {
	
	/**
	 * Computes the candidate pairs using the LSH technique.
	 * @param mhs The minhash signature object.
	 * @param bs The number of buckets to be used in the LSH table.
	 * @param r The number of rows per band to be used.
	 * @return Returns a set of indices pairs that are candidate to being similar.
	 */
	public static Set<List<Integer>> computeCandidates(MinHashSignature mhs, int bs, int r) {
		// assert that the number of rows can be evenly divided by r
		assert(mhs.rows() % r == 0);
		
		// the number of bands
		int b = mhs.rows() / r;
		
		// the result
		Set<List<Integer>> candidates = new HashSet<List<Integer>>();
		
		// ADD CODE HERE
//		List<List<List<Integer>>> buckets = new ArrayList<>();

		Map<Integer, Map<Integer, List<Integer>>> buckets = new HashMap<>();

        System.out.println("Store all items");
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < mhs.cols(); j++) {
				String seg = mhs.colSegment(j, i, i + r);
				if (buckets.get(seg.hashCode()) == null)
				    buckets.put(seg.hashCode(), new HashMap<>());
				if (buckets.get(seg.hashCode()).get(i) == null)
                    buckets.get(seg.hashCode()).put(i, new ArrayList<>());

                buckets.get(seg.hashCode()).get(i).add(j);
			}
		}

        System.out.println("Retrieve candidates");
		for (int i = 0; i < b; i++) {
		    for (int j = 0; j < mhs.cols(); j++) {
                String seg = mhs.colSegment(j, i, i + r);
                List<Integer> bucket = buckets.get(seg.hashCode()).get(i);
                for (int item : bucket) {
                    if (item != j) {
                        if (!candidates.contains(Arrays.asList(item, j)))
                            candidates.add(Arrays.asList(j, item));
                    }
                }
            }
        }
		
		return candidates;
	}
	
}
