import java.util.ArrayList;
import java.util.List;


public class Perceptron {	
	/**
	 * The learning rate for the perceptron.
	 */
	double learningRate;

	/**
	 * The weights calculated by the perceptron.
	 */
	List<Double> weights;

	/**
	 * Constructor.
	 * @param fs The feature size.
	 * @param lr The learning rate.
	 */
	public Perceptron(double lr) {
		learningRate = lr;
		weights = null;
	}

	/**
	 * @return Returns the list of weights calculated by the perceptron.
	 */
	public List<Double> getWeights() {
		return weights;
	}

	/**
	 * Train the weights based on a training feature vector.
	 * @param fv The feature vector used for training.
	 */
	public void train(FeatureVector fv) {
		if (weights == null) {
			weights = new ArrayList<Double>();

			// add initially only zeroes
			for (int i = 0; i < fv.size(); i++)
				weights.add(0.0);
		}
		//System.out.println("fv= "+fv);
	//	System.out.println("w = "+weights);
		double yAccent = fv.product(weights);
	//	System.out.println("w*x = "+yAccent);
		if ((fv.getLabel() <= 0.0 && yAccent > 0.0) || yAccent == 0.0){
			fv.label = 1;
			for(int i = 0; i < weights.size(); i++){
				double w = weights.remove(i);
				weights.add(i, w+(learningRate*fv.getLabel()*fv.get(i))); //update weight
	//			System.out.println("delta = "+learningRate*fv.getLabel()*fv.get(i));
			}
		} else if ((fv.getLabel() > 0.0 && yAccent < 0.0) || yAccent == 0.0) {
			fv.label = -1;
			for(int i = 0; i < weights.size(); i++){
				double w = weights.remove(i);
				weights.add(i, w+(learningRate*fv.getLabel()*fv.get(i))); //update weight
	//			System.out.println("delta = "+learningRate*fv.getLabel()*fv.get(i));
			}
		}
		
		
	}

	/**
	 * Uses all training items to train the perceptron.
	 * @param p The perceptron to be trained.
	 */
	public void updateWeights(Dataset dataset) {		
		for (FeatureVector fv: dataset) {
			train(fv);
		}
	}

	/**
	 * Predicts the label of a feature vector.
	 * @param fv The feature vector to predict.
	 * @return The predicted label of the feature vector.
	 */
	public int predict(FeatureVector fv) {
		return fv.product(weights) <= 0.0 ? -1 : 1;
	}
}