package main;

import java.awt.Image;


public class Background {
	
	private String name;
	private Image image;
	
	public Background() {
		name = new String();
	}
	
	public Background(String name) {
		super();
		this.name = name;
	}


	public Image getImage() {
		return image;
	}
}
