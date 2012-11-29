package main;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import main.Background.Planet;

@SuppressWarnings("serial")
public class GameGUI extends JFrame {

	// TODO - Van - Abstract Planet into own enum class with gravity as a var.
	public static final int FRAME_HEIGHT = 700;
	public static final int FRAME_WIDTH = 900;
	private static final String GAME_NAME = "GAME NAME"; // FIXME	
	private double gravity;
	private Background background;
	private ImagePanel imagePanel;
	private JMenuItem earthMenuItem, marsMenuItem;

	public GameGUI() {
		super(GAME_NAME);
		background = new Background(Planet.MARS);
		imagePanel = new ImagePanel(background.getImage());
		earthMenuItem = new JMenuItem(Planet.EARTH.toString());
		marsMenuItem = new JMenuItem(Planet.MARS.toString());

		gravity = 9.81; // FIXME - Van - Make this dynamic based on 

		earthMenuItem.addActionListener(new PlanetItemListener());
		marsMenuItem.addActionListener(new PlanetItemListener());

		setContentPane(imagePanel);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		menuBar.add(createPlanetSelectMenu());

		// Andrew added code
		add(new Target(300, 300), BorderLayout.CENTER);
		add(new Target(500, 400), BorderLayout.CENTER);
		add(new Target(700, 150), BorderLayout.CENTER);
		// JPanel picPanel = new JPanel();
		// picPanel.add(target);
		// add(picPanel, BorderLayout.CENTER);a
	}

	private class ImagePanel extends JComponent {

		private Image image;

		public ImagePanel(Image image) {
			this.image = image;
		}

		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, null);
		}

		public void setImage(Image image) {
			this.image = image;
		}
	}

	public JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(createFileExit());
		return menu;
	}

	private JMenuItem createFileExit() {
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}

	private JMenu createPlanetSelectMenu() {
		JMenu menu = new JMenu("Change Planet");
		menu.add(earthMenuItem);
		menu.add(marsMenuItem);
		return menu;
	}

	private class PlanetItemListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == earthMenuItem) {
				background.setPlanet(Planet.EARTH);
			} else if (e.getSource() == marsMenuItem) {
				background.setPlanet(Planet.MARS);
			}
			updateBackground();
		}
	}

	public void updateBackground() {
		imagePanel.setImage(background.getImage());
		repaint();
	}

	public double getGravity() {
		return gravity;
	}

	public static void main (String args[]) {
		GameGUI gui = new GameGUI();
		gui.setVisible(true);
	}
}
