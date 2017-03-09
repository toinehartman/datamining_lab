import java.util.ArrayList;
import java.util.List;

/**
 * MinHashSignature class contains a matrix like structure,
 * where the matrix is stored in column-major order.
 * @author hansgaiser
 *
 */
public class MinHashSignature {

	private List<Integer> signature;
	private int rows;
	private int cols;
	
	/**
	 * Constructor for the MinHashSignature.
	 * @param rows The number of rows.
	 * @param cols The number of cols.
	 */
	public MinHashSignature(int rows, int cols) {
		signature = new ArrayList<Integer>(rows*cols);
		this.rows = rows;
		this.cols = cols;
		
		for (int i = 0; i < rows*cols; i++)
			signature.add(i, Integer.MAX_VALUE);
	}

	/**
	 * Sets the value in the signature matrix.
	 * @param r The row of the value to be set.
	 * @param c The column of the value to be set.
	 * @param value The value to set at (r, c).
	 */
	public void set(int r, int c, int value) {
		signature.set((rows*c) + r, value);
	}
	
	/**
	 * Returns the value of the signature matrix.
	 * @param r The row of the value.
	 * @param c The column of the value.
	 * @return The value at (r, c).
	 */
	public int get(int r, int c) {
		return signature.get((rows*c) + r);
	}
	
	/**
	 * 
	 * @return Returns the number of rows in the signature matrix.
	 */
	public int rows() {
		return rows;
	}
	
	/**
	 * 
	 * @return Returns the number of columns in the signature matrix.
	 */
	public int cols() {
		return cols;
	}
	
	/**
	 * Returns a segment of a column in a String format.
	 * @param c The column c to be extracted.
	 * @param start The start index of the column.
	 * @param end The end index of the column.
	 * @return Returns the segment of the column in the form of a String.
	 */
	public String colSegment(int c, int start, int end) {
		String result = "";
		List<Integer> subList = signature.subList((rows*c) + start, (rows*c) + end);
		for(Integer i: subList)
			result += i;
		return result;
	}
	
	/**
	 * Puts the signature matrix in a String format.
	 */
	@Override
	public String toString() {
		String result = "\n";
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				result += get(r, c) + ", ";
			}
			result += "\n";
		}
		return result.substring(0, result.length() - 3);
	}
	

}
