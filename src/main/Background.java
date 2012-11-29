package main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Background {
	
	// TODO - Van - Add name string.. Ex. EARTH(9.81, "Earth").
	// Use this for drop down population
	public enum Planet {
		EARTH(9.81), MARS(3.71);
		private double gravity;
		Planet(double gravity) {
			this.gravity = gravity;
		}
		public double getGravity() {
			return gravity;
		}
	};
	
	private String name;
	private BufferedImage image;
	private Planet planet;
	
	public Background() {
		name = new String();
	}
	
	public Background(Planet planet) {
		super();
		setPlanet(planet);
	}

	public Image getImage() {
		return image;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPlanet(Planet planet) {
		this.planet = planet;
		if (planet == Planet.EARTH) {
			name = "Earth";
			try {
				image = ImageIO.read(new File("src/main/resources/earth_view_from_moon.jpg"));
			} catch (IOException e) {
				System.out.println("Earth image not found.");
			}
		} else if (planet == Planet.MARS) {
			name = "Mars";
			try {
				image = ImageIO.read(new File("src/main/resources/mars_view.jpg"));
			} catch (IOException e) {
				System.out.println("Mars image not found.");
			}
		}
	}

	public Planet getPlanet() {
		return planet;
	}
}
