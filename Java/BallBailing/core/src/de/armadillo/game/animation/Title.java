package de.armadillo.game.animation;

import de.armadillo.game.engine.FileHandler;

public class Title {

	private float x = FileHandler.screenWidth/2 - FileHandler.title.getWidth()/2;
	private float y = FileHandler.screenHeight/2 + 200;
    private float scale = 1;
    
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
	public float getScale() {
		return scale;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}
}