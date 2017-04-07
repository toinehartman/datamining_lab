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

		boolean log = false;
		
		List<Matrix> eigenvectors = new ArrayList<Matrix>();
		
		//Construct a column vector of ones of length D x 1.
		Matrix v = new Matrix(m.rows(), 1,  1);

		while(eigenvectors.size() < nrVectors){
			//Find a new eigenvalue with power iteration
			System.out.println(String.format("\tCalculating eigenvector %d/%d", eigenvectors.size() + 1, nrVectors));
			
			//Until convergence compute v_k+1
			double delta = Integer.MAX_VALUE;
			while(delta > e){
				//compute v_k+1
				Matrix Mv = m.dot(v);
				if (log) System.out.println("Matrix m: " + m);
				if (log) System.out.println("Matrix v: " + v);
				if (log) System.out.println("Matrix Mv: " + Mv);
				if (log) System.out.println("Matrix Mv.norm(): " + Mv.norm());

				//update the delta
				Matrix vNext = Mv.multiply(1 / Mv.norm());
				if (log) System.out.println("Matrix vNext: " + vNext);
				
				//update the delta, based on the L2 norm of the difference matrix (v_k - v_k+1)
				delta = v.subtract(vNext).norm();
				if (log) System.out.println("Delta: " + delta);
				v = vNext;
			}
		
			eigenvectors.add(v);
			if (log) System.out.println("\nEigenvector " + eigenvectors.size() + " = " + v);
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
		System.out.println("Reading matrix data...");
		Matrix m = Matrix.readData("data/gaussian.txt");
		PCAPlotter pcap = new PCAPlotter();
		pcap.plotData(m);

		System.out.println("Calculating mean row...");
		Matrix mr = m.meanRow();
		int N = m.rows();
		System.out.println("Calculating covariance matrix...");
		Matrix cov = m.subtractRow(mr).transpose().dot(m.subtractRow(mr)).multiply(1.0 / N);
		System.out.println("Covariance matrix: " + cov);

		int nrVectors = 2;
		System.out.println("Calculating " + nrVectors + " eigenvectors...");
		List<Matrix> eigenvectors = powerIteration(cov, nrVectors, 10e-5);
		System.out.println("Done! Plotting eigenvectors...");
		pcap.plotEigenvectors(eigenvectors);
	}
	
	/**
	 * Computes some principal components from a dataset
	 * of face images.
	 */
	public static void pcaFaces() {
		int nrVectors = 8;

		System.out.println("Reading matrix data...");
		Matrix m = Matrix.readData("data/faces.txt");
		System.out.println(String.format("Data matrix [%d, %d]", m.rows(), m.cols()));
		System.out.println("Calculating mean row...");
		Matrix mr = m.meanRow();
		int N = m.rows();
		System.out.println("Calculating covariance matrix...");
		System.out.println("\tCalculating m dot mr");
		Matrix second = m.subtractRow(mr);
		System.out.println("\tCalculating transpose");
		Matrix first = second.transpose();
		System.out.println("\tCalculating first dot second");
		Matrix dot = first.dot(second);
		System.out.println("\tCalculating result");
		Matrix cov = dot.multiply(1.0 / N);
//		Matrix cov = m.subtractRow(mr).transpose().dot(m.subtractRow(mr)).multiply(1.0 / N);
		System.out.println(String.format("Covariance matrix [%d, %d]", cov.rows(), cov.cols()));
		System.out.println("Calculating " + nrVectors + " eigenvectors...");
		List<Matrix> eigenvectors = powerIteration(cov, nrVectors, 10e-5);
		System.out.println("Done! Displaying " + nrVectors + " eigenvectors...");

		for (int i = 0; i < eigenvectors.size(); i++)
			new ImageFrame("Eigenvector " + i, eigenvectors.get(i), 32, 32);
	}

	public static void main(String[] args) {
		powerIterationTest();
//		pca();
//		pcaFaces();
	}

}
