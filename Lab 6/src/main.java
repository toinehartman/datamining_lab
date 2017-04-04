import java.util.ArrayList;
import java.util.List;


public class main {
	
	/**
	 * Computes nrVectors eigen vectors of m where e is the
	 * stopping criterion for the norm of the difference for an
	 * eigenvector in between two rounds.
	 * @param m The matrix of which eigenvectors should be computed.
	 * @param nrVectors The number of eigenvectors to compute.
	 * @param e The threshold for the stopping criterion.
	 * @return A list of eigenvectors in m.
	 */
	public static List<Matrix> powerIteration(Matrix m, int nrVectors, double e) {
		assert(m.cols() == m.rows() && m.cols() >= nrVectors);
		
		List<Matrix> eigenvectors = new ArrayList<Matrix>();
		
		//Construct a column vector of ones of length D x 1.
		Matrix v = new Matrix(m.rows(), 1,  1);
		while(eigenvectors.size() < nrVectors){
			//Find a new eigenvalue with power iteration
			
			//Until convergence compute v_k+1
			double delta = Integer.MAX_VALUE;
			while(delta > e){
				//compute v_k+1
				Matrix Mv = m.dot(v);
				//update the delta
				Matrix vNext = Mv.multiply(1/Mv.norm());
				
				//update the delta, based on the L2 norm of the difference matrix (v_k - v_k+1)
				delta = v.subtract(vNext).norm();
			//	System.out.println("Delta = "+delta);
				v = vNext;
				 
			}
		
			eigenvectors.add(v);
			//System.out.println("\nEigenvector "+eigenvectors.size()+" = " + v); 
			//vT * M * v results in a 1x1 matrix (the eigenvalue of v)
			double lambda = v.transpose().dot(m).dot(v).get(0, 0);
			//System.out.println("Lambda = " + lambda);

			//Calculate M* = M - lambda * v * vT
			Matrix eigenPair = v.dot(v.transpose()).multiply(lambda);
			m = m.subtract(eigenPair);
			//System.out.println("M* = " + m);
		
		}
		
		return eigenvectors;
	}
	
	/**
	 * Computes two eigenvectors of a small matrix example.
	 */
	public static void powerIterationTest() {
		Matrix m = Matrix.readData("data/matrix.txt");
		
		List<Matrix> eigenvectors = powerIteration(m, 2, 10e-5);
		
		System.out.println(eigenvectors);
		
	}

	/**
	 * Computes the principal components from a Gaussian
	 * distributed dataset.
	 */
	public static void pca() {
		// add code here
	}
	
	/**
	 * Computes some principal components from a dataset
	 * of face images.
	 */
	public static void pcaFaces() {
		// add code here
	}

	public static void main(String[] args) {
		powerIterationTest();
		//pca();
		//pcaFaces();
	}

}
