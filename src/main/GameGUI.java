package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameGUI extends JFrame {
	
	private double gravity;
	
	public GameGUI() {
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 700);
	}
	
	public double getGravity() {
		return gravity;
	}

	public void drawBackground(Background b) {
		if(b.getName().equalsIgnoreCase("Earth")) {
			gravity = 9.81;
		}
	}
	
	public static void main (String args[]) {
		GameGUI gui = new GameGUI();
		gui.setVisible(true);
	}
}
