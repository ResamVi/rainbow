package de.co.armadillo.entities;

import com.badlogic.gdx.math.Vector2;

public class Target {

	private Vector2 start;
	private Vector2 end;
	private float angle;
	
	public Target() {
		start = new Vector2(335, 725); // Position of our cannon
		end = new Vector2(335, 725);
		angle = 0;
	}

	public void update(Enemy enemy) {
		
		// Update end point to enemy movement
		end.x = enemy.getCircle().x;
		end.y = enemy.getCircle().y;
		
		// spin
		angle+=2;
	}
	
	public float getAngle() {
		return end.cpy().sub(start).angle()+90;
	}
	public Vector2 getStart() {
		return start;
	}
	
	public Vector2 getEnd() {
		return end;
	}
	
	public float getSpin() {
		return angle;
	}
}
