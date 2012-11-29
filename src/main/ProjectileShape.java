package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

//KEEP IN MIND THE GAME SCREEN IS 900x700

//TODO Not sure if this will work in GameGUI since it's a JPanel.
//Any ideas to get most of the logic to work and just make it
//a regular class? (Worried it might 'replace' background)
public class ProjectileShape extends JPanel {
	private int x;
	private int y;
	private static final int DIAMETER = 100;
    private int width = DIAMETER / 2;
    private int height = width;
    private int radius = 4 * DIAMETER / 5;
	private int n;

	public ProjectileShape() {
		x = 0;
		y = 0;
		this.setPreferredSize(new Dimension(DIAMETER, DIAMETER));
		n = DIAMETER;
	}
	
	//TODO May need finishing if we want to play with n
	public ProjectileShape(int n) {
		x = 0;
		y = 0;
		this.setPreferredSize(new Dimension(DIAMETER, DIAMETER));
		this.n = n;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRadius() {
		return width;
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);	
		Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setColor(Color.blue);
        width = getWidth() / 2;
        height = getHeight() / 2;
        int m = Math.min(width, height);
        radius = 4 * m / 5;
        int r2 = Math.abs(m - radius) / 2;
        graphics2d.fillOval(width - radius, height - radius, 2 * radius, 2 * radius);
	}
	
	//Only for testing purposes; comment out after!
//	 private static void create() {
//	        JFrame f = new JFrame();
//	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	        //f.add(new ProjectileShape(9));
//	        f.add(new ProjectileShape());
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
