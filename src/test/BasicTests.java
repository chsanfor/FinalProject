package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import main.Angle;
import main.Background.Planet;
import main.GameGUI;
import main.Projectile;
import main.ProjectileShape;

import org.junit.Test;

public class BasicTests {
	
	@Test
	public void testLaunch() {
		Projectile projectile = new Projectile(new ProjectileShape());
		projectile.launch();
		assertTrue(projectile.isLaunched());
		
	}
	
	@Test
	public void testGravity() {
		GameGUI gameGUI = new GameGUI(Planet.EARTH);
		gameGUI.getGravity();
		assertEquals(9.81, gameGUI.getGravity(), .2);
	}
	
	@Test
	public void testAngle() {
		Angle angle = new Angle(200);
		assertTrue(angle.getDegrees() <= 180);
	}
	
	@Test
	public void testShapeMoves() {
		Projectile projectile = new Projectile(new ProjectileShape());
		ProjectileShape projectileShape = projectile.getShape();
		projectile.launch();
		assertNotSame(0, projectileShape.getX());
		assertNotSame(0, projectileShape.getY());
	}
	
	@Test
	public void testImageChanges() {
		GameGUI gameGUI = new GameGUI(Planet.EARTH);
		GameGUI gameGUI2 = new GameGUI(Planet.MARS);
		assertNotSame(gameGUI.getGravity(), gameGUI2.getGravity());
	}
}
