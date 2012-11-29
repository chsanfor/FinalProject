package main;

import java.awt.Component;

public class Projectile {
	private boolean hasLaunched;
	private ProjectileShape shape;
	private int xLocation;
	private int yLocation;
	
	public Projectile() {
		hasLaunched = false;
		shape = new ProjectileShape();
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
		xLocation = (shape.getX() - (shape.getRadius()));
		yLocation = (shape.getY() - (shape.getRadius()));
	}
	
	public void launch(int dx, int dy) {
		hasLaunched = true;
		shape.setX(shape.getX() + dx);
		shape.setY(shape.getY() + dy);
	}
}
