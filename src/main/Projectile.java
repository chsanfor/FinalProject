package main;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Projectile {
	private final double VELOCITY = 20;
	private boolean hasLaunched;
	private ProjectileShape shape;
	private int xLocation;
	private int yLocation;
	
	public Projectile(ProjectileShape shape) {
		hasLaunched = false;
		this.shape = shape;
	}
	
	public ProjectileShape getShape() {
		return shape;
	}

	public void setShape(ProjectileShape shape) {
		this.shape = shape;
	}
	
	public boolean isHasLaunched() {
		return hasLaunched;
	}

	public void calculateLocation() {
		//TODO Needs finishing; should location be a field of x's and y's
		//or a single x and y (i.e. the center of the projectile)?
		//Right now it is just the center of the projectile.
		xLocation = (shape.getX() - shape.getRadius());
		yLocation = (shape.getY() - shape.getRadius());
	}
	
	public void launch() {
		hasLaunched = true;
	}
	
	public void resetProjectile() {
		//TODO IMPLEMENT
	}

	public void draw(Graphics g) {
		// TODO Doesn't work yet
		// System.out.println("Calling projectile draw");
		Graphics2D g2 = (Graphics2D) g;
		shape.draw(g2);
	}
	
//	public void update(Graphics g) {
//		System.out.println("Still working");
//		Graphics2D g2 = (Graphics2D) g;
//		shape.draw(g2);
//	}
}