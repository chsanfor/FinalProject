package test;

import java.awt.Image;

import main.*;
import org.junit.Assert;
import org.junit.Test;

public class BasicTests {
	
	@Test
	public void testLaunch() {
		Projectile projectile = new Projectile();
		projectile.launch();
		Assert.assertTrue(projectile.isHasLaunched());
		
	}
	
	@Test
	public void testGravity() {
		GameGUI gameGUI = new GameGUI();
		Background Earth = new Background();
		gameGUI.drawBackground(Earth);
		Assert.assertEquals(gameGUI.getGravity(), 9.81);
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
		projectile.launch();
		Assert.assertNotSame(0, projectileShape.getX());
		Assert.assertNotSame(0, projectileShape.getY());
	}
	
	@Test
	public void testImageChanges() {
		Background b = new Background("Earth");
		GameGUI gameGUI = new GameGUI();
		Image backgroundImage = b.getImage();
		gameGUI.drawBackground(b);
		Assert.assertNotSame(backgroundImage, b.getImage());
	}
}
