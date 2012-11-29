package main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Background {
	
	public enum Planet {EARTH, MARS};
	private String name;
	private BufferedImage image;
	
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
}
