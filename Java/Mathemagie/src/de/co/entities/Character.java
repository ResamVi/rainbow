package de.co.armadillo.entities;

import com.badlogic.gdx.math.Rectangle;

public class Character {

	private Rectangle rect;
	private Rectangle cannon;
	
	private Target target;
	
	public Character(float x, float y, Target target) {
		
		this.target = target;
		
		rect = new Rectangle(x, y, 50, 50);
		cannon = new Rectangle(x+20, y-20, 10, 20);
	}
	
	public void destroy() {
		rect.setPosition(900, 900);
		cannon.setPosition(900, 900);
	}
	
	public float getRotation() {
		return target.getAngle();
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public Rectangle getCannon() {
		return cannon;
	}
}
