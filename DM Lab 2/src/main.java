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
		
		for(int i = 1; i <= 2; i++){
			new DigitFrame("Row "+i,ds.get(i),8,8);
		}
		pc.updateWeights(ds);
		pc.updateWeights(ds);
		
		//new DigitFrame("weights",pc.weights,8,8);
		
		Dataset ds2 = new Dataset();
		ds2.readData("data/test_digits.txt", true);
		
		int misclassified = 0;
		
		Iterator<FeatureVector> iter = ds2.iterator();
		while(iter.hasNext()){
			FeatureVector fv = iter.next();
			if(pc.predict(fv) != fv.getLabel()){
				misclassified++;
				new DigitFrame("",fv,8,8);
			}
		}
		
		double errorRate = (double) misclassified / ds2.size();
		System.out.println("Error rate = "+errorRate);
	}

	public static void nearestNeighbour() {
		// add code here
	}
	
	public static void nearestNeighbourDigits() {
		// add code here
	}

	public static void main(String[] args) {
		//perceptron();
		perceptronDigits();
		//nearestNeighbour();
		//nearestNeighbourDigits();
	}

}
