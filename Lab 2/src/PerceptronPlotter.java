import java.awt.BasicStroke;
import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class PerceptronPlotter {
	/**
	 * Used for visualization.
	 */
	XYSeriesCollection collection;
	JFreeChart chart;
	ChartFrame frame;
	
	/**
	 * The labels of the classes.
	 */
	String first;
	String second;

	/**
	 * Constructor.
	 */
	public PerceptronPlotter(String first, String second) {
		this.first = first;
		this.second = second;

		// initialize the chart
		collection = new XYSeriesCollection();
		chart = ChartFactory.createScatterPlot(
				"Perceptron",					// chart title
				"Weight",						// x axis label
				"Size",							// y axis label
				null,							// data
				PlotOrientation.VERTICAL,
				true,							// include legend
				true,							// tooltips
				true							// urls
				);

		frame = new ChartFrame("Perceptron", chart);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		//frame.addKeyListener(this);
	}

	/**
	 * Plots the data.
	 * @param p The perceptron that needs to be plotted.
	 */
	public void plotData(Dataset d, Perceptron p) {
		// remove any visualizations
		collection.removeAllSeries();
		chart.getXYPlot().clearAnnotations();
		
		// split over apples and pears
		XYSeries apples = new XYSeries(first);
		XYSeries pears = new XYSeries(second);

		// fill the datasets
		for (FeatureVector fv: d) {
			if (fv.getLabel() == -1)
				apples.add(fv.get(0), fv.get(1));
			else if (fv.getLabel() == 1)
				pears.add(fv.get(0), fv.get(1));
		}

		// add them to the collection
		collection.addSeries(apples);
		collection.addSeries(pears);

		// plot the data
		chart.getXYPlot().setDataset(collection);
		
		// plot the decision boundary
		double x1 = -200;
		double x2 = 200;
		double y1 = (-p.getWeights().get(0) * x1 - p.getWeights().get(2)) / p.getWeights().get(1);
		double y2 = (-p.getWeights().get(0) * x2 - p.getWeights().get(2)) / p.getWeights().get(1);

		XYLineAnnotation la = new XYLineAnnotation(x1, y1, x2, y2, new BasicStroke(2.0f), Color.black);
		chart.getXYPlot().addAnnotation(la);
		
		// plot the weights
		x1 = p.getWeights().get(0) * 100;
		y1 = p.getWeights().get(1) * 100;
		x2 = p.getWeights().get(0) * -100;
		y2 = p.getWeights().get(1) * -100;
		
		XYLineAnnotation la2 = new XYLineAnnotation(x1, y1, x2, y2, new BasicStroke(1.0f), Color.yellow);
		chart.getXYPlot().addAnnotation(la2);
	}
}
