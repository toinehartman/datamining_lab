import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class HierarchicalClusteringPlotter implements KeyListener {
	/**
	 * Chart object, maintains the scatterplot.
	 */
	private JFreeChart chart;

	/**
	 * The main frame.
	 */
	private ChartFrame frame;

	private HierarchicalClustering hc;

	/**
	 * Constructor.
	 * 
	 * @param clusterSize
	 *            The number of clusters to be detected.
	 */
	public HierarchicalClusteringPlotter(int k, String fileName) {
		chart = ChartFactory.createScatterPlot("Hierarchical Clustering (k = "
				+ k + ")", // chart title
				"X", // x axis label
				"Y", // y axis label
				null, // data
				PlotOrientation.VERTICAL, false, // include legend
				true, // tooltips
				true // urls
				);

		frame = new ChartFrame("Hierarchical Clustering", chart);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addKeyListener(this);

		hc = new HierarchicalClustering(k, fileName);

		plotData();
	}

	/**
	 * Plots the data in hc as a scatterplot.
	 */
	private void plotData() {
		// plot the clusters
		XYSeriesCollection collection = new XYSeriesCollection();
		for (int i = 0; i < hc.getClusterSize(); i++) {
			XYSeries serie = new XYSeries(i + "");
			for (FeatureVector fv : hc.getCluster(i)) {
				serie.add(fv.get(0), fv.get(1));
			}
			collection.addSeries(serie);
		}

		chart.getXYPlot().setDataset(collection);

		// set the colors/shapes
		for (int i = 0; i < hc.getClusterSize(); i++) {
			chart.getXYPlot().getRenderer()
					.setSeriesPaint(i, hc.getCluster(i).getColor());
			chart.getXYPlot().getRenderer()
					.setSeriesShape(i, hc.getCluster(i).getShape());
		}
	}

	/**
	 * Calls update when the space bar has been hit.
	 */
	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getKeyChar() == ' ') {
			hc.update();
			plotData();
		}
	}

	/**
	 * Unused methods.
	 */
	@Override
	public void keyReleased(KeyEvent key) {}
	@Override
	public void keyTyped(KeyEvent key) {}
}
