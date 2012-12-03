package main;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Projectile {
	public static final double VELOCITY = 20.0;
	private boolean hasLaunched;
	private ProjectileShape shape;
	
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
	
	public boolean isLaunched() {
		return this.hasLaunched;
	}
	
	public void resetProjectile() {
		hasLaunched = false;
		shape.reset();
	}
	
	public void launch() {
		hasLaunched = true;
	}
}