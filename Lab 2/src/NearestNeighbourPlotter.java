import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class NearestNeighbourPlotter implements ChartMouseListener {
	/**
	 * Used for visualization.
	 */
	XYSeriesCollection collection;
	JFreeChart chart;
	ChartFrame frame;
	
	/**
	 * Nearest Neighbour classifier.
	 */
	NearestNeighbour nearestNeigbour;
	
	/**
	 * Number of neighbours to check when predicting a label.
	 */
	int k;

	/**
	 * Constructor.
	 * @param k The number of neighbours to check when predicting a label.
	 */
	public NearestNeighbourPlotter(int k) {
		nearestNeigbour = null;
		this.k = k;
		
		// initialize the chart
		collection = new XYSeriesCollection();
		chart = ChartFactory.createScatterPlot(
				"Nearest Neighbour",			// chart title
				"X",						// x axis label
				"Y",							// y axis label
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
		frame.getChartPanel().addChartMouseListener(this);
	}

	/**
	 * Plots the data.
	 */
	public void plotData(NearestNeighbour nn) {
		collection.removeAllSeries();
		chart.getXYPlot().clearAnnotations();

		// split over apples and pears
		List<XYSeries> series = new ArrayList<XYSeries>();
		series.add(new XYSeries("First"));
		series.add(new XYSeries("Second"));
		series.add(new XYSeries("Third"));
		series.add(new XYSeries("Fourth"));

		// fill the datasets
		for (FeatureVector fv: nn.getDataset()) {
			series.get(fv.getLabel()).add(fv.get(0), fv.get(1));
		}

		// add them to the collection
		for (XYSeries serie: series)
			collection.addSeries(serie);

		// plot the data
		chart.getXYPlot().setDataset(collection);
		frame.repaint();
		
		nearestNeigbour = nn;
	}

	/**
	 * Handles a mouse click event. Will add a point to the scatterplot.
	 */
	@Override
	public void chartMouseClicked(ChartMouseEvent e) {
		if (nearestNeigbour == null)
			return;
		
        int i = e.getTrigger().getX();
        int j = e.getTrigger().getY();
        
        Point2D point2d = frame.getChartPanel().translateScreenToJava2D(new Point(i, j));
        XYPlot xyplot = (XYPlot)frame.getChartPanel().getChart().getPlot();
        ChartRenderingInfo chartrenderinginfo = frame.getChartPanel().getChartRenderingInfo();
        java.awt.geom.Rectangle2D rectangle2d = chartrenderinginfo.getPlotInfo().getDataArea();
        
        // get the chart x, y
        double x = xyplot.getDomainAxis().java2DToValue(point2d.getX(), rectangle2d, xyplot.getDomainAxisEdge());
        double y = xyplot.getRangeAxis().java2DToValue(point2d.getY(), rectangle2d, xyplot.getRangeAxisEdge());
        
        // construct a feature list
        List<Double> features = new ArrayList<Double>();
        features.add(x);
        features.add(y);
        
        // add the feature to the scatterplot
        collection.getSeries(nearestNeigbour.predict(features, k)).add(new XYDataItem(x, y));
	}

	@Override
	public void chartMouseMoved(ChartMouseEvent e) { }
}
