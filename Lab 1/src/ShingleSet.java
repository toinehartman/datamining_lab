import apple.laf.JRSUIUtils;

import java.util.SortedSet;
import java.util.TreeSet;


/**
 * The ShingleSet class contains a sorted set of shingles.
 * @author hansgaiser
 *
 */
public class ShingleSet extends TreeSet<String> implements SortedSet<String> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2143400328259342668L;
	
	/**
	 * The size of the shingles.
	 */
	private int k;
	
	/**
	 * Constructor for the ShingleSet.
	 * @param k The size of the shingles.
	 */
	public ShingleSet(int k) {
		this.k = k;
	}
	
	/**
	 * Add shingles of size k to the set from String s.
	 * @param s The string that is to be transformed to shingles.
	 */
	public void shingleString(String s) {
		// ADD CODE HERE
		for (int i = 0; i <= s.length() - k; i++) {
            String sh = s.substring(i, i + k);
            this.add(sh);
        }
	}
	
	/**
	 * Add shingles of size k to the set from String s, where all white spaces from s are removed.
	 * @param s The string that is to be transformed to shingles.
	 */
	public void shingleStrippedString(String s) {
		// ADD CODE HERE
        String stripped = s.replaceAll(" ", "");
        for (int i = 0; i <= stripped.length() - k; i++) {
            String sh = stripped.substring(i, i + k);
            this.add(sh);
        }
	}
	
	/**
	 * Calculates the Jaccard distance between this set and the other set.
	 * @param other The other set of shingles that this set will be compared to.
	 * @return The Jaccard distance between this set and the other set.
	 */
	public double jaccardDistance(TreeSet<String> other) {
		// ADD CODE HERE
	    TreeSet<String> intersection = new TreeSet<>();
	    intersection.addAll(this);
	    intersection.retainAll(other);

        TreeSet<String> union = new TreeSet<>();
        union.addAll(this);
        union.addAll(other);

        double similarity = (double)intersection.size() / union.size();

	    return 1 - similarity;
	}

}
