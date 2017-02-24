import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ClassificationTest {

	@Test
	public void testInnerProduct() {
		FeatureVector fv = new FeatureVector(1);
		List<Double> w = new ArrayList<Double>();
		fv.add(1.0);fv.add(2.0);fv.add(3.0);fv.add(4.0);
		w.add(10.0);w.add(10.0);w.add(10.0);w.add(10.0);
		
		assertEquals((double)(1*10+2*10+3*10+4*10),fv.product(w),0.05);
		
	}
	
	@Test
	public void testEuclidianDistance() {
		FeatureVector fv = new FeatureVector(1);
		List<Double> v = new ArrayList<Double>();
		fv.add(1.0);fv.add(2.0);fv.add(3.0);fv.add(4.0);
		v.add(1.0);v.add(2.0);v.add(4.0);v.add(5.0);
		
		assertEquals((double)(Math.sqrt(Math.pow(1.0-1.0, 2)+Math.pow(2.0-2.0, 2)
								+Math.pow(3.0-4.0, 2)+Math.pow(4.0-5.0, 2))), fv.distance(v),0.05);
		
		
	}

	@Test
	public void testPerceptronTrain() {
		Perceptron pc = new Perceptron(0.5);
		FeatureVector fv = new FeatureVector(0);
		fv.add(1.0);fv.add(2.0);fv.add(-3.0);
		pc.train(fv);
		
		List<Double> answer = new ArrayList<Double>();
		answer.add(0,0.5);
		answer.add(1,1.0);
		answer.add(2,-1.5);
		
		assertEquals(answer,pc.weights);
	}
	
}
