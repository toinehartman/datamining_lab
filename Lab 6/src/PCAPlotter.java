import java.awt.BasicStroke;
import java.awt.Color;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class PCAPlotter {
	/**
	 * Chart object, maintains the scatterplot.
	 */
	private JFreeChart chart;

	/**
	 * The main frame.
	 */
	private ChartFrame frame;
	
	/**
	 * The data that is shown in the scatterplot.
	 */
	private XYSeries data;

	/**
	 * Constructor.
	 * @param k The number of clusters to detect.
	 * @param fileName The filename which holds the cluster data.
	 */
	public PCAPlotter() {
		chart = ChartFactory.createScatterPlot(
				"Principal Component Analysis",	// chart title
				"X",							// x axis label
				"Y",							// y axis label
				null,							// data
				PlotOrientation.VERTICAL,
				true,							// include legend
				true,							// tooltips
				true							// urls
				);

		frame = new ChartFrame("Principal Components Analysis", chart);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
				
		data = null;
	}

	/**
	 * Plots the matrix data.
	 */
	public void plotData(Matrix m) {
		assert(m.cols() == 2);
		
		chart.getXYPlot().clearAnnotations();
		
		data = new XYSeries("Data");
		for (int i = 0; i < m.rows(); i++) {
			data.add(m.get(i, 0), m.get(i, 1));
		}
		chart.getXYPlot().setDataset(new XYSeriesCollection(data));
	}

	/**
	 * Plots the eigen vectors.
	 * @param eigenvectors The eigen vectors that are plotted.
	 */
	public void plotEigenvectors(List<Matrix> eigenvectors) {
		for (Matrix m: eigenvectors) {
			// plot the vectors
			double x1 = m.get(0) * 100;
			double y1 = m.get(1) * 100;
			double x2 = m.get(0) * -100;
			double y2 = m.get(1) * -100;
			
			XYLineAnnotation la2 = new XYLineAnnotation(x1, y1, x2, y2, new BasicStroke(1.0f), Color.yellow);
			chart.getXYPlot().addAnnotation(la2);
		}
	}
}
