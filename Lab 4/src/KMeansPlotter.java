import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYShapeAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class KMeansPlotter implements KeyListener {
	/**
	 * Chart object, maintains the scatterplot.
	 */
	private JFreeChart chart;

	/**
	 * The main frame.
	 */
	private ChartFrame frame;

	/**
	 * The KMeans object which does the processing and holds the data.
	 */
	private KMeans km;

	/**
	 * Constructor.
	 * @param k The number of clusters to detect.
	 * @param fileName The filename which holds the cluster data.
	 */
	public KMeansPlotter(int k, String fileName) {
		chart = ChartFactory.createScatterPlot(
				"K-Means (k = " + k + ")",		// chart title
				"X",							// x axis label
				"Y",							// y axis label
				null,							// data
				PlotOrientation.VERTICAL,
				true,							// include legend
				true,							// tooltips
				true							// urls
				);

		frame = new ChartFrame("K-Means", chart);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addKeyListener(this);
		
		km = new KMeans(k, fileName);
		
		plotData();
	}

	/**
	 * Plots the KMeans data.
	 */
	private void plotData() {
		chart.getXYPlot().clearAnnotations();

		if (km.getClusterSize() > 0) {
			XYSeriesCollection collection = new XYSeriesCollection();
			for (int i = 0; i < km.getClusterSize(); i++) {
				if (km.getCluster(i).size() == 0)
					continue;
				
				XYSeries serie = new XYSeries("Cluster" + i);
				for (FeatureVector fv: km.getCluster(i)) {
					serie.add(fv.get(0), fv.get(1));
				}
				collection.addSeries(serie);
				
				FeatureVector centroid = km.getCluster(i).centroid();
				double x = centroid.get(0);
				double y = centroid.get(1);
	            XYShapeAnnotation annotation = new XYShapeAnnotation(new Ellipse2D.Double(x - 0.1, y - 0.1, 0.2, 0.2), 
	            		new BasicStroke(1.0f), Color.black, Color.black);
				chart.getXYPlot().addAnnotation(annotation);
			}
			
			// plot the clusters
			chart.getXYPlot().setDataset(collection);
		}
		else {
			// plot the data
			XYSeries data = new XYSeries("Unclustered data");
			for (FeatureVector fv: km.getData()) {
				data.add(fv.get(0), fv.get(1));
			}
			chart.getXYPlot().setDataset(new XYSeriesCollection(data));
		}
	}

	/**
	 * Update method which is called when the user presses the space bar.
	 */
	private void update() {
		km.update();
		plotData();
	}

	/**
	 * Calls update when the space bar has been hit.
	 */
	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getKeyChar() == ' ')
			update();
	}

	/**
	 * Unused methods.
	 */
	@Override
	public void keyReleased(KeyEvent key) {}
	@Override
	public void keyTyped(KeyEvent key) {}
}
