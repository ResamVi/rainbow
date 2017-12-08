package de.armadillo.game.entities;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.armadillo.game.properties.Collidable;
import de.armadillo.game.properties.Movable;

public abstract class Entity implements Movable, Collidable {

	protected float radius;
	protected Circle hitboxCircle;
	protected Rectangle hitboxRectangle;
	protected Vector2 position;
	protected Vector2 velocity;
	protected Vector2 acceleration;
	
	Entity(float x, float y, int radius) {	
		this.radius = radius;
		
		hitboxCircle = new Circle(x, y, radius);
		hitboxRectangle = new Rectangle(x-radius*2, y-radius*2 , radius*2, radius*2);
		
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 0);
	}
	
	public void updateHitbox() {
		hitboxCircle.x = position.x+radius;
		hitboxCircle.y = position.y+radius;
		
		hitboxRectangle.x = position.x;
		hitboxRectangle.y = position.y;
	}
	
	public boolean collides(Circle circle) {
		
		float newX = hitboxCircle.x + acceleration.x;
		float newY = hitboxCircle.y + acceleration.y;
		
		return new Circle(newX, newY, radius).overlaps(circle);
	}
	
	public boolean collides(Rectangle rectangle) {
		
		float newX = hitboxRectangle.x + acceleration.x;
		float newY = hitboxRectangle.y + acceleration.y;
		
		return new Rectangle(newX, newY, radius*2, radius*2).overlaps(rectangle);
		
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public float getRadius() {
		return radius;
	}
	
	public Circle getHitboxCircle() {
		return hitboxCircle;
	}

	public Rectangle getHitboxRectangle() {
		return hitboxRectangle;
	}	
}