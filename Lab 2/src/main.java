import java.util.Iterator;
import java.util.List;

public class main {

	public static void perceptron() {
		Perceptron pc = new Perceptron(1);
		PerceptronPlotter pcp = new PerceptronPlotter("1","2");
		Dataset ds = new Dataset();
		
		ds.readData("data/gaussian.txt", true);
		
		pc.updateWeights(ds);
		pcp.plotData(ds, pc);
		
	}

	public static void perceptronDigits() {
		Perceptron pc = new Perceptron(1);
		Dataset ds = new Dataset();
		ds.readData("data/train_digits.txt", true);
		
//		for(int i = 49; i <= 54; i++){
//			new DigitFrame("Row " + i, ds.get(i),8,8);
//		}

		pc.updateWeights(ds);
		pc.updateWeights(ds);

//		new DigitFrame("weights", pc.weights, 8, 8);

		Dataset ds2 = new Dataset();
		ds2.readData("data/test_digits.txt", true);

		int misclassified = 0;

		Iterator<FeatureVector> iter = ds2.iterator();
		while(iter.hasNext()){
			FeatureVector fv = iter.next();
			if(pc.predict(fv) != fv.getLabel()){
				misclassified++;
//				new DigitFrame("Misclassified",fv,8,8);
			}
		}

		double errorRate = (double) misclassified / ds2.size();
		System.out.println("Error rate = " + errorRate);
	}

	public static void nearestNeighbour() {
		//TODO: implement the nearestNeighbour method. This method should create
		// a NearestNeighbour and NearestNeighbourPlotter object with k set to 3. Let
		// the NearestNeighbour object read in the data from the file "data/banana.txt". Let the
		// NearestNeighbourPlotter plot the data of the NearestNeighbour classifier.
		
		//Note: You can click on the scatterplot to add points. These points will then be classified and shown
		//on screen. Added points will not contribute to further classifications.
		
		//Add code here
		NearestNeighbour nn = new NearestNeighbour();
		NearestNeighbourPlotter nnp = new NearestNeighbourPlotter(3);

		nn.readData("data/banana.txt");
		nnp.plotData(nn);

	}
	
	public static void nearestNeighbourDigits() {
		//TODO: implement the nearestNeighbourDigits method. This method
		// trains a nearest neighbour classifier on the same dataset of digits as before with the perceptron.
		// Again, calculate the error rate on the test set and compare the results.
		
		// add code here
		NearestNeighbour nn = new NearestNeighbour();
		nn.readData("data/train_digits.txt");

		Dataset ds_test = new Dataset("data/test_digits.txt", false);
		int misclassified = 0;

		for (FeatureVector fv : ds_test) {
			int actual = fv.getLabel();
			int prediction = nn.predict(fv, 3);

			if (actual != prediction)
				misclassified++;
		}

		double error_rate = (double) misclassified / ds_test.size();
		System.out.println(String.format("Error rate (digits using nn): %f", error_rate));
	}

	public static void main(String[] args) {
//		perceptron();
//		perceptronDigits();
//		nearestNeighbour();
		nearestNeighbourDigits();
	}

}
