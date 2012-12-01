package main;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;

import main.Background.Planet;

@SuppressWarnings("serial")
public class GameGUI extends JFrame {

	public static final int FRAME_HEIGHT = 700;
	public static final int FRAME_WIDTH = 900;
	public static final int STATIC_TARGETS = 3;
	public static final int MOVING_TARGETS = 3;
	private static final String GAME_NAME = "GAME NAME"; // FIXME We need a name
	private Background background;
	private ImagePanel imagePanel;
	private JMenuItem earthMenuItem, marsMenuItem;
	private ArrayList<Target> targets;
	private ControlGUI controlGUI;
	private int pointsScored = 0;
	private ProjectileShape shape;
	private Projectile projectile;


	/**
	 * @param planet, default planet for GameGUI
	 */
	public GameGUI(Planet planet) {
		super(GAME_NAME);
		background = new Background(planet);
		imagePanel = new ImagePanel(background.getImage());
		controlGUI = new ControlGUI();
		earthMenuItem = new JMenuItem(Planet.EARTH.getName());
		marsMenuItem = new JMenuItem(Planet.MARS.getName());

		earthMenuItem.addActionListener(new PlanetItemListener());
		marsMenuItem.addActionListener(new PlanetItemListener());

		setContentPane(imagePanel);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false); // Added to top user from expanding window and seeing actual background size.

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		menuBar.add(createPlanetSelectMenu());
		
		targets = new ArrayList<Target>();
		Random rand = new Random();
		// Adding targets that aren't too close to missile launch site
		for(int i = 0; i < STATIC_TARGETS; i++) {
			targets.add(new Target(rand.nextInt(FRAME_WIDTH-Target.TARGETSIZE), 
					rand.nextInt(FRAME_HEIGHT-4*Target.TARGETSIZE), false));
		}
		for(int i = 0; i < MOVING_TARGETS; i++) {
			targets.add(new Target(rand.nextInt(FRAME_WIDTH-Target.TARGETSIZE), 
					rand.nextInt(FRAME_HEIGHT-4*Target.TARGETSIZE), true));
		}
		// Targets move every 500 ms
		Timer targetTimer = new Timer(500, new TimerListener());
		targetTimer.start();
		add(controlGUI, BorderLayout.SOUTH);
		
		shape = new ProjectileShape();
		projectile = new Projectile(shape);
		
		updateBackground();
		launchProjectile();
	}

	public class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for(Target t: targets) {
				if(t.getCanMove()) {
					t.update();
				}
			}
			repaint();
		}
	}

	private class ImagePanel extends JComponent {

		private Image image;

		public ImagePanel(Image image) {
			this.image = image;
		}

		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, null);
			for(Target t: targets) {
				if(t.isHit(projectile))
					t.drawExplosion(g);
				else
					t.draw(g);
			}
			// This is separate for loop to ensure all targets are drawn. Need to break to
			// prevent for loop logic errors.
			for(Target t: targets) {
				if(t.isHit(projectile)) {
					pointsScored += t.getPointValue();
					targets.remove(t);
					break;	
				}
			}
			projectile.draw(g);
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
	
	public void launchProjectile() {
		int dx, dy;
		dx = dy = 0;
		projectile.launch(dx, dy);
	}

	public void updateBackground() {
		imagePanel.setImage(background.getImage());
		controlGUI.setGravity(getGravity());
		repaint();
	}

	public double getGravity() {
		return background.getPlanet().getGravity();
	}

	public static void main (String args[]) {
		GameGUI gui = new GameGUI(Planet.EARTH);
		gui.setVisible(true);
	}
}
