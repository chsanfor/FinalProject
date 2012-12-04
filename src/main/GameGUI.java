package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import main.Background.Planet;

@SuppressWarnings("serial")
public class GameGUI extends JFrame {

	public static final int FRAME_HEIGHT = 700;
	public static final int FRAME_WIDTH = 900;
	public static final int STATIC_TARGETS = 3;
	public static final int MOVING_TARGETS = 3;
	public static final int ALLOWED_SHOTS = 5;
	private static final String GAME_NAME = "Exploding Brown Meteors from Uranus";
	private Background background;
	private ImagePanel imagePanel;
	private JMenuItem earthMenuItem, marsMenuItem, moonMenuItem;
	private ArrayList<Target> targets;
	private ControlGUIButton controlGUI;
	private int pointsScored;
	private ProjectileShape shape;
	private Projectile projectile;
	private long lastMoveTime;
	private int shotCount;

	/**
	 * @param planet, default planet for GameGUI
	 */
	public GameGUI(Planet planet) {
		super(GAME_NAME);
		background = new Background(planet);
		imagePanel = new ImagePanel(background.getImage());
		controlGUI = new ControlGUIButton();
		earthMenuItem = new JMenuItem(Planet.EARTH.getName());
		marsMenuItem = new JMenuItem(Planet.MARS.getName());
		moonMenuItem = new JMenuItem(Planet.MOON.getName());

		earthMenuItem.addActionListener(new PlanetItemListener());
		marsMenuItem.addActionListener(new PlanetItemListener());
		moonMenuItem.addActionListener(new PlanetItemListener());

		setContentPane(imagePanel);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false); // Added to top user from expanding window and seeing actual background size.

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		menuBar.add(createPlanetSelectMenu());
		menuBar.add(createHelpMenu());
		
		pointsScored = 0;
		targets = new ArrayList<Target>();
		Random rand = new Random();
		// Adding targets that aren't too close to missile launch site
		for(int i = 0; i < STATIC_TARGETS; i++) {
			targets.add(new Target(rand.nextInt(FRAME_WIDTH-Target.TARGETSIZE), 
					rand.nextInt(FRAME_HEIGHT-5*Target.TARGETSIZE), false));
		}
		for(int i = 0; i < MOVING_TARGETS; i++) {
			targets.add(new Target(rand.nextInt(FRAME_WIDTH-Target.TARGETSIZE), 
					rand.nextInt(FRAME_HEIGHT-5*Target.TARGETSIZE), true));
		}
		// Targets move every 500 ms
		Timer targetTimer = new Timer(250, new TimerListener());
		targetTimer.start();
		add(controlGUI, BorderLayout.SOUTH);
		
		Timer projectileTimer = new Timer(50, new ProjectileTimer());
		projectileTimer.start();
		
		shape = new ProjectileShape();
		projectile = new Projectile(shape);
		shotCount = ALLOWED_SHOTS;
		controlGUI.setShotsRemaining(shotCount);
		
		updateBackground();
	}
	
	public class ProjectileTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (projectile.isLaunched()) {
				moveProjectile();
				repaint();
			}
		}
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
					controlGUI.setPointsScored(pointsScored);
					targets.remove(t);
					break;	
				}
			}
			projectile.draw(g);
			if(!projectile.isLaunched()) {
				drawTrajectory(g);
			}
		}

		public void setImage(Image image) {
			this.image = image;
		}
	}
	
	private class ControlGUIButton extends ControlGUI {

		private JButton fireButton;

		public ControlGUIButton() {
			super();

			fireButton = new JButton("Fire");
			fireButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!projectile.isLaunched())
						launchProjectile();
				}
			});
			add(fireButton);
		}
	}
	
	public JMenu createHelpMenu() {
		JMenu menu = new JMenu("Help");
		menu.add(createHelpDialog());
		return menu;
	}
	
	public JMenuItem createHelpDialog() {
		JMenuItem item = new JMenuItem("Show Help");
		class MenuItemListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				showHelpDialog();
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}
	
	public void showHelpDialog() {
		JOptionPane.showMessageDialog(this, "How to Play:\n"
				+ "You have " + ALLOWED_SHOTS + " energy balls to destroy the "
				+ (STATIC_TARGETS + MOVING_TARGETS) + " targets."
				+ "\nSet the angle using the keyboard arrows (UP & DOWN) or with the mouse."
				+ "\nThe energy ball can be moved during flight by changing the angle.", "Help", JOptionPane.INFORMATION_MESSAGE);
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
		menu.add(moonMenuItem);
		return menu;
	}

	private class PlanetItemListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == earthMenuItem) {
				background.setPlanet(Planet.EARTH);
			} else if (e.getSource() == marsMenuItem) {
				background.setPlanet(Planet.MARS);
			} else if (e.getSource() == moonMenuItem) {
				background.setPlanet(Planet.MOON);
			}
			updateBackground();
		}
	}
	
	public void launchProjectile() {
		lastMoveTime = 0;
		if(shotCount > 0) {
			projectile.launch();
			--shotCount;
			controlGUI.setShotsRemaining(shotCount);
		}
	}
	
	private void moveProjectile() {
		if (checkProjectileLocation()) {
			long timeElapsed = lastMoveTime = lastMoveTime + 50;
			shape.setX(shape.getX() + (int) ((int) (Projectile.VELOCITY * Math.cos(controlGUI.getAngle().getDegrees()*Math.PI/180))*timeElapsed/1000));
			shape.setY(shape.getY() - (int) ((int) Projectile.VELOCITY * Math.sin(controlGUI.getAngle().getDegrees()*Math.PI/180)*timeElapsed/1000 - 0.5*getGravity()*Math.pow(timeElapsed/1000, 2)));
		} else {
			projectile.resetProjectile();
			if (targets.size() == 0 || shotCount == 0) {
				endGameSplashScreen();
			}
		}
	}
	
	private boolean checkProjectileLocation() {
		if (shape.getX() >= 0 && shape.getY() >= (0 - shape.getRadius()*2)
				&& shape.getX() <= FRAME_WIDTH && shape.getY() <= FRAME_HEIGHT) {
			return true;
		} else {
			return false;
		}
	}

	public void updateBackground() {
		imagePanel.setImage(background.getImage());
		controlGUI.setGravity(getGravity());
		repaint();
	}

	public double getGravity() {
		return background.getPlanet().getGravity();
	}
	
	public void endGameSplashScreen() {
		JOptionPane.showMessageDialog(this, "Your Score: " + pointsScored);
		System.exit(0);
	}

	public void drawTrajectory(Graphics g) {
		Graphics2D graphics2d = (Graphics2D) g;
		int x = shape.getX() + shape.getRadius();
		int y = shape.getY() + shape.getRadius();
		double slope = -Math.tan(controlGUI.getAngle().getDegrees() * Math.PI/180);
		for(int i = 0; i < 5; ++i) {
			double t = (double) i / 2;
			x += (int) (Projectile.VELOCITY * Math.cos(controlGUI.getAngle().getDegrees()*Math.PI/180) * t);
			y += (int) (-Projectile.VELOCITY * Math.sin(controlGUI.getAngle().getDegrees()*Math.PI/180) * t + .5*getGravity()*Math.pow(t, 2));
//			slope = (-Projectile.VELOCITY * Math.sin(controlGUI.getAngle().getDegrees()*Math.PI/180) + getGravity()*t) / 
//					(Projectile.VELOCITY * Math.cos(controlGUI.getAngle().getDegrees()*Math.PI/180));
			double dx = 10 * 1 / Math.sqrt(1 + Math.pow(slope, 2));
			if(x < FRAME_WIDTH && y < FRAME_HEIGHT - 80) {
				graphics2d.setColor(Color.YELLOW);
				graphics2d.drawLine(x, y, x + (int) dx, y + (int)dx*(int)slope);
			}
			else {
				break;
			}
		}
	}
	public void startGameSplashScreen() {
		JOptionPane.showMessageDialog(this, "Defend the planet from falling meteors using your energy ball."
				+ "\nSee the Help menu for instructions on how to play."
				+ "\nChoose a firing angle and press the fire button to begin.",
				"Welcome to " + GAME_NAME, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main (String args[]) {
		GameGUI gui = new GameGUI(Planet.MOON);
		gui.setVisible(true);
		gui.startGameSplashScreen();
	}
}