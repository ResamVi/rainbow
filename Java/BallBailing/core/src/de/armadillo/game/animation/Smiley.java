package de.armadillo.game.animation;

import java.util.Random;

public class Smiley {

	Random r = new Random();
	
	private float x = -100;
	private float y = r.nextInt(880) - 100;
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
}
