/**
 * 
 * @author hansgaiser
 *
 */
public class HashFunction {
	
	private int alpha;
	private int beta;
	
	/**
	 * Constructor for the HashFunction object.
	 * Hashes in the form of h(x) = alpha * x + beta
	 * @param alpha The alpha factor.
	 * @param beta The additional beta factor.
	 */
	public HashFunction(int alpha, int beta) {
		this.alpha = alpha;
		this.beta = beta;
	}
	
	/**
	 * The hashing function. Hashes h(x) = (alpha * x + beta) mod n.
	 * @param x The value that is to be hashed.
	 * @param n The maximum value.
	 * @return The hashed value h(x).
	 */
	public int hashCode(int x, int n) {
		return (alpha * x + beta) % n;
	}
	
	/**
	 * Maps the hash function to a string.
	 */
	@Override
	public String toString() {
		return alpha + "x + " + beta;
	}

}
