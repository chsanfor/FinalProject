package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Target {
	
	public static final int TARGETSIZE = 50;
	private int xTopLeft, yTopLeft;
	private Image image;
	private long pointValue, targetDistance;

	public Target(int x, int y) {
		try {
			image = ImageIO.read(new File("src/main/resources/polygon.png"));
		} catch (IOException e) {
			System.out.println("Projectile image cannot be located.");
		}
		xTopLeft = x;
		yTopLeft = y;
		// targetDistance is basic line length formula: L = Sqrt[ (x-x0)^2 + (y-y0)^2
		targetDistance = Math.round(Math.sqrt(Math.pow(xTopLeft - GameGUI.FRAME_WIDTH/2,2) + 
				Math.pow(yTopLeft - GameGUI.FRAME_HEIGHT,2)));
		// Creating "nice" point values, ending in zero
		pointValue = targetDistance/10 * 10;
	}
	
	// Draws target and point value
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		g2.drawImage(image, xTopLeft, yTopLeft, TARGETSIZE, TARGETSIZE, Color.PINK, null);
		g2.drawString(String.valueOf(pointValue), xTopLeft + TARGETSIZE/3, yTopLeft + TARGETSIZE/2);
	}
}
