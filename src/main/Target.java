package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Target extends JPanel {
	
	public static final int TARGETSIZE = 50;
	private int xTopLeft, yTopLeft;
	private Image image;
	private int pointValue;
	private int targetDistance;

	public Target(int x, int y) {
		try {
			image = ImageIO.read(new File("src/main/resources/polygon.png"));
		} catch (IOException e) {
			System.out.println("Projectile image cannot be located.");
		}
		xTopLeft = x;
		yTopLeft = y;
		// TODO add formula to determine point value
		targetDistance = 45;
		
		setSize(900, 700);
		setVisible(true);
		
		/*// Get the images and put them into ImageIcons.
		targetIcon = createImageIcon("/main/resources/polygon.png");
		// Create a label with border for displaying the pictures.
		pictureLabel = new JLabel();
		pictureLabel.setHorizontalAlignment(JLabel.CENTER);
		pictureLabel.setVerticalAlignment(JLabel.CENTER);
		pictureLabel.setVerticalTextPosition(JLabel.CENTER);
		pictureLabel.setHorizontalTextPosition(JLabel.CENTER);
		pictureLabel.setIcon(targetIcon);

		add(pictureLabel);	     */
	}

/*	// From Cyndi Rader's "Switchable" code
	protected static ImageIcon createImageIcon(String path) {

		java.net.URL imageURL = Target.class.getResource(path);

		if (imageURL == null) {
			System.err.println("Resource not found: " + path);
			return null;
		} else {
			return new ImageIcon(imageURL);
		}
	}
	*/
	
	public void paintComponent(Graphics g) {
		System.out.println("ENTERED DRAW!");
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		g2.drawImage(image, xTopLeft, yTopLeft, TARGETSIZE, TARGETSIZE, Color.PINK, null);
		// TODO Draw point value on target
	}
}
