package main;

public class GameGUI {
	
	private double gravity;
	
	public double getGravity() {
		return gravity;
	}

	public void drawBackground(Background b) {
		if(b.getName().equalsIgnoreCase("Earth")) {
			gravity = 9.81;
		}
	}
}
