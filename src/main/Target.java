package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Target {

	public static final int TARGETSIZE = 50;
	public static final int MOVINGDISTANCE = 30;
	private int xTopLeft, yTopLeft;
	private static Image targetImage, explosionImage;
	private long pointValue, targetDistance;
	private boolean canMove;

	public Target(int x, int y, boolean canMove) {
		try {
			targetImage = ImageIO.read(new File("src/main/resources/polygon.png"));
			explosionImage = ImageIO.read(new File("src/main/resources/explosion.png"));
		} catch (IOException e) {
			System.out.println("Projectile image cannot be located.");
		}
		this.canMove = canMove;
		xTopLeft = x;
		yTopLeft = y;
		calcPointValue(x,y);
	}

	// Calculate the Point Value for each target
	public void calcPointValue(int x, int y) {
		// targetDistance is basic line length formula: L = Sqrt[(x-x0)^2 + (y-y0)^2]
		targetDistance = Math.round(Math.sqrt(Math.pow(x - GameGUI.FRAME_WIDTH/2,2) + 
				Math.pow(y - GameGUI.FRAME_HEIGHT,2)));
		// Creating "nice" point values, ending in zero
		pointValue = targetDistance/10 * 10;
	}

	// Draws target and point value
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(targetImage, xTopLeft, yTopLeft, TARGETSIZE, TARGETSIZE, null);
		g2.drawString(String.valueOf(pointValue), 
				xTopLeft + TARGETSIZE/3, yTopLeft + TARGETSIZE/2);
	}

	// If target can move, this updates position and point value
	public void update(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		xTopLeft = xTopLeft - MOVINGDISTANCE;
		if(xTopLeft < 0) {
			xTopLeft = GameGUI.FRAME_WIDTH;
		}
		calcPointValue(xTopLeft, yTopLeft);
		g2.drawImage(targetImage, xTopLeft, yTopLeft, TARGETSIZE, TARGETSIZE, null);
		g2.drawString(String.valueOf(pointValue), 
				xTopLeft + TARGETSIZE/3, yTopLeft + TARGETSIZE/2);
	}

	public boolean getCanMove() {
		return canMove;
	}
}
