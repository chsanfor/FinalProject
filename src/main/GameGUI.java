package main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameGUI extends JFrame {
	
	private double gravity;
	
	public GameGUI() {
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 700);
		
		
		// Andrew added code
		add(new Target(300, 300), BorderLayout.CENTER);
		add(new Target(500, 400), BorderLayout.CENTER);
		add(new Target(700, 150), BorderLayout.CENTER);
		// JPanel picPanel = new JPanel();
		// picPanel.add(target);
		// add(picPanel, BorderLayout.CENTER);
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
