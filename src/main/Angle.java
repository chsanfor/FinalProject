package main;

public class Angle {
	private double degrees;
	
	public Angle(double d) {
		if(d <= 180) {
			degrees = d;
		}
		else {
			degrees = 0;
		}
	}
	
	public void setDegrees(double angle) {
		this.degrees = angle;
	}

	public double getDegrees() {
		return degrees;
	}
}
