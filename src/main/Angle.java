package main;

public class Angle {
	private double angle;
	
	public Angle(double d) {
		if(d <= 180) {
			angle = d;
		}
		else {
			angle = 0;
		}
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getAngle() {
		return angle;
	}
}
