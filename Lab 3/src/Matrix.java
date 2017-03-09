import java.util.ArrayList;


public class Matrix extends ArrayList<Double> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The number of rows in this matrix.
	 */
	int rows;

	/**
	 * The number of columns in this matrix.
	 */
	int cols;

	/**
	 * Constructor.
	 * @param r Number of rows.
	 * @param c Number of columns.
	 */
	public Matrix(int r, int c) {
		super(r*c);
		rows = r;
		cols = c;

		for (int i = 0; i < rows*cols; i++)
			add(i, 0.0);
	}

	/**
	 * Sets the value in the signature matrix.
	 * @param r The row of the value to be set.
	 * @param c The column of the value to be set.
	 * @param value The value to set at (r, c).
	 */
	public void set(int r, int c, double value) {
		set((rows*c) + r, value);
	}

	/**
	 * Returns the value of the signature matrix.
	 * @param r The row of the value.
	 * @param c The column of the value.
	 * @return The value at (r, c).
	 */
	public double get(int r, int c) {
		return get((rows*c) + r);
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
	 * Calculates the dot product between this matrix and the other matrix.
	 * In other words, it calculates "this * other"
	 * @param other The matrix that is to be multiplied with.
	 * @return The product of the two matrices.
	 */
	public Matrix dot(Matrix other) {
		assert(this.cols() == other.rows());

		Matrix result = new Matrix(this.rows(), other.cols());

		for (int c2 = 0; c2 < other.cols(); c2++) {
			for (int r = 0; r < rows; r++) {
				double sum = 0.0;
				for (int c = 0; c < cols; c++) {
					sum += get(r, c) * other.get(c, c2);
				}
				result.set(r, c2, sum);
			}
		}

		return result;
	}

	/**
	 * Multiplies the matrix with a scalar, scaling each element in the matrix.
	 * @param scalar The scalar with which to multiply each element.
	 * @return A new matrix with scaled elements.
	 */
	public Matrix multiply(double scalar) {
		Matrix result = (Matrix) this.clone();

		for (int i = 0; i < size(); i++) {
			result.set(i, get(i) * scalar);
		}

		return result;
	}

	/**
	 * Adds one matrix to another. They need to be of the same size.
	 * @param other The other matrix.
	 * @return A new matrix where all elements of the two matrices have been added.
	 */
	public Matrix add(Matrix other) {
		assert(cols() == other.cols() && rows() == other.rows());

		Matrix result = new Matrix(rows(), cols());
		for (int i = 0; i < size(); i++) {
			result.set(i, get(i) + other.get(i));
		}

		return result;
	}

	/**
	 * Puts the matrix in a String format.
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
