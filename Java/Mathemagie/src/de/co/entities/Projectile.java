package de.co.armadillo.entities;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Projectile {

	private Vector2 position;
	private Vector2 velocity;
	private Circle circle;
	private Target target;
	
	
	public Projectile(Target target) {
		position = new Vector2(335, 725);
		velocity = new Vector2(0, 0);
		circle = new Circle(position.x, position.y, 3);
		this.target = target; 
	}
	
	public void update(float delta) {
		position.add(velocity.cpy().scl(delta));
		circle.setPosition(position.x, position.y);
	}
	
	public boolean checkCollision(Circle enemy) {
		if(circle.overlaps(enemy))
			return true;
		else
			return false;
	}
	
	public void reset() {
		position.set(335, 725);
		velocity.set(0, 0);
	}
	
	public void shoot(Enemy enemy) {
		velocity.x = 355*2;
		velocity.y = 725*2;
		velocity.setAngle(target.getAngle()-90);
	}
	
	public Circle getCircle() {
		return circle;
	}
}
