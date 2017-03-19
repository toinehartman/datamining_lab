import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DigitFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * @param title Title of the frame.
	 */
	public DigitFrame(String title) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle(title);
	}
	
	/**
	 * Constructor.
	 * @param title Title of the frame.
	 * @param image The image that is to be shown.
	 * @param width Width of the image.
	 * @param height Height of the image.
	 */
	public DigitFrame(String title, List<Double> image, int width, int height) {
		this(title);
		showImage(image, width, height);
	}

	/**
	 * Constructs an Image based on pixel values.
	 * @param pixels The pixels.
	 * @param width Width of the image.
	 * @param height Height of the image.
	 * @return
	 */
	private static Image getImageFromArray(int[] pixels, int width, int height) {
		BufferedImage image =
				new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = (WritableRaster) image.getData();
		raster.setPixels(0, 0, width, height, pixels);
		image.setData(raster);
		return image;
	}

	/**
	 * Shows an image, based on a list of pixel values.
	 * @param image The pixel values.
	 * @param width The width of the image.
	 * @param height The height of the image.
	 */
	public void showImage(List<Double> image, int width, int height) {
		JLabel jl = new JLabel();
		
		double min = Double.MAX_VALUE;
		double max = -Double.MAX_VALUE;
		for (Double d: image) {
			min = Math.min(d, min);
			max = Math.max(d, max);
		}
		
		int[] arrayimage = new int[width*height];
		for (int i = 0; i < width*height; i++) {
			arrayimage[i] = new Double(((image.get(i) - min) / (max - min)) * 255.0).intValue();
		}
		
		ImageIcon ii = new ImageIcon(getImageFromArray(arrayimage, width, height).getScaledInstance(300, 300, Image.SCALE_DEFAULT));
		jl.setIcon(ii);
		add(jl);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
