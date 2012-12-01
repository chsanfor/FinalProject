package main;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Projectile {
	private boolean hasLaunched;
	private ProjectileShape shape;
	private int xLocation;
	private int yLocation;
	
	//Custom constructor
	public Projectile(ProjectileShape shape) {
		hasLaunched = false;
		this.shape = shape;
	}
	
	//Returns the Projectile's ProjectileShape
	public ProjectileShape getShape() {
		return shape;
	}

	//Set the Projectile's ProjectileShape (not sure if this will see use)
	public void setShape(ProjectileShape shape) {
		this.shape = shape;
	}
	
	//Set hasLaunched
	public boolean isHasLaunched() {
		return hasLaunched;
	}

	//Finds the center x and y of the circle
	public void calculateLocation() {
		//TODO Needs finishing; should location be a field of x's and y's
		//or a single x and y (i.e. the center of the projectile)?
		//Right now it is just the center of the projectile.
		xLocation = (shape.getX() - shape.getRadius());
		yLocation = (shape.getY() - shape.getRadius());
	}
	
	//Launches the projectile (unless a GameGUI function is devised for this)
	public void launch(Angle angle, double gravity) {
		hasLaunched = true;
//		shape.setX(shape.getX() + dx);
//		shape.setY(shape.getY() + dy);
		//TODO Trajectory logic (involving degrees and gravity) goes here (unless there is a separate flight function)
	}

	//Calls ProjectileShape draw(), which draws the projectile
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		shape.draw(g2);
	}
	
	public void update(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		shape.draw(g2);
	}
	
}