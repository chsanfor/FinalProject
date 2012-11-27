package test;

import static org.junit.Assert.assertEquals;

import java.awt.Image;

import main.*;
import main.Background.Planet;

import org.junit.Assert;
import org.junit.Test;

public class BasicTests {
	
	@Test
	public void testLaunch() {
		Projectile projectile = new Projectile();
		projectile.launch(0, 0);
		Assert.assertTrue(projectile.isHasLaunched());
		
	}
	
	@Test
	public void testGravity() {
		GameGUI gameGUI = new GameGUI();
		Background Earth = new Background(Planet.EARTH);
		gameGUI.drawBackground(Earth);
		Assert.assertEquals(9.81, gameGUI.getGravity(), .2);
	}
	
	@Test
	public void testAngle() {
		Angle angle = new Angle(200);
		Assert.assertTrue(angle.getAngle() <= 180);
	}
	
	@Test
	public void testShapeMoves() {
		Projectile projectile = new Projectile();
		ProjectileShape projectileShape = projectile.getShape();
		projectile.launch(5, 5);
		Assert.assertNotSame(0, projectileShape.getX());
		Assert.assertNotSame(0, projectileShape.getY());
	}
	
	@Test
	public void testImageChanges() {
		Background b = new Background(Planet.EARTH);
		GameGUI gameGUI = new GameGUI();
		Image backgroundImage = b.getImage();
		gameGUI.drawBackground(b);
		b.setPlanet(Planet.MARS);
		Assert.assertNotSame(backgroundImage, b.getImage());
		assertEquals("Mars", b.getName());
	}
}
