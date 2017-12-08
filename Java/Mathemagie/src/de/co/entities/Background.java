package de.co.armadillo.entities;

import com.badlogic.gdx.math.Vector2;

public class Background {

	Vector2 position;
	Vector2 velocity;
	
	public Background(float x, float y, float v) {
		position = new Vector2(x, y);
		velocity = new Vector2(0, v);
		
	}
	
	public void update(float delta) {
		position.add(velocity.cpy().scl(delta));
		if(position.y >= 840)
			position.y = 0;
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
}
