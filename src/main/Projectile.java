package main;

public class Projectile {
	private boolean hasLaunched;
	private ProjectileShape shape;
	
	public ProjectileShape getShape() {
		return shape;
	}

	public void setShape(ProjectileShape shape) {
		this.shape = shape;
	}

	public Projectile() {
		hasLaunched = false;
		shape = new ProjectileShape();
	}
	
	public boolean isHasLaunched() {
		return hasLaunched;
	}

	public void calculateLocation() {
		
	}
	
	public void launch(int dx, int dy) {
		hasLaunched = true;
		shape.setX(shape.getX() + dx);
		shape.setY(shape.getY() + dy);
	}
}
