package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

//KEEP IN MIND THE GAME SCREEN IS 900x700

public class ProjectileShape {
	private final int initX, initY;
	private int x;
	private int y;
	public static final int DIAMETER = 50;
	private int width;
	private int height;
	private int radius;
	
	//Default constructor
	public ProjectileShape() {
		width = DIAMETER / 2;
		height = width;
		radius = 4 * DIAMETER / 5;
		initX = x = GameGUI.FRAME_WIDTH/2 - radius;
		initY = y = GameGUI.FRAME_HEIGHT - 80 - (radius*2);
	}

	//Special constructor
	public ProjectileShape(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		initX = x;
		initY = y;
	}

	//Returns the x location of the circle
	public int getX() {
		return x;
	}

	//Sets the x location of the circle
	public void setX(int x) {
		this.x = x;
	}

	//Returns the y location of the circle
	public int getY() {
		return y;
	}

	//Sets the y location of the circle
	public void setY(int y) {
		this.y = y;
	}

	//Returns radius of circle
	public int getRadius() {
		return radius;
	}

	//Returns width of circle
	public int getWidth() {
		return width;
	}

	//Sets width of circle
	public void setWidth(int width) {
		this.width = width;
	}

	//Returns height of circle
	public int getHeight() {
		return height;
	}

	//Sets height of circle
	public void setHeight(int height) {
		this.height = height;
	}

	//Draws the projectile
	public void draw(Graphics g) {	
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.setColor(Color.BLUE);
		int m = Math.min(width, height);
		radius = DIAMETER/2;
		graphics2d.fillOval(x, y, 2 * radius, 2 * radius);
	}

	public void reset() {
		x = initX;
		y = initY;
	}
	
	//   Only for testing purposes; comment out after!

	//	public void paintComponent(Graphics g) {
	//	super.paintComponent(g);	
	//	Graphics2D graphics2d = (Graphics2D) g;
	//	graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	//	graphics2d.setColor(Color.blue);
	//	width = getWidth() / 2;
	//	height = getHeight() / 2;
	//	int m = Math.min(width, height);
	//	radius = 4 * m / 5;
	//	graphics2d.fillOval(x, y, 2 * radius, 2 * radius);
	//	//System.out.println("Still drawing");
	//	System.out.println(getX() + " " + getY());
	//	
	//}

	//	 private static void create() {
	//	        JFrame f = new JFrame();
	//	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	        //f.add(new ProjectileShape(9));
	//	        ProjectileShape shape = new ProjectileShape();
	//	        //f.add(shape);
	//	        f.pack();
	//	        f.setVisible(true);
	//	    }
	//	 
	//	   public static void main(String[] args) {
	//	        EventQueue.invokeLater(new Runnable() {
	//
	//	            @Override
	//	            public void run() {
	//	                create();
	//	            }
	//	        });
	//	    }
}
