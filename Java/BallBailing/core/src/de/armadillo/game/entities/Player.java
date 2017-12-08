package de.armadillo.game.entities;

import de.armadillo.game.engine.FileHandler;

public class Player extends Entity {

	private final float REBOUND_INTENSITY = 1.9f;
	private final float ACCELERATION_SPEED = 5f;
	private final float MAX_VELOCITY = 2f;
	
	public Player(float x, float y) {
		super(x, y, FileHandler.player.getWidth() / 2);
	}

	@Override
	public void update(float delta) {
		acceleration.add(velocity.cpy().scl(delta));
		acceleration.limit(MAX_VELOCITY);
		position.add(acceleration);

		updateHitbox();
	}

	public void onCollision() {

		// Prevents being stuck in an entity
		position.x += acceleration.cpy().rotate(180).x;
		position.y += acceleration.cpy().rotate(180).y;

		acceleration.rotate(180);
		acceleration.scl(REBOUND_INTENSITY);
	}
	
	public void moveLeft() {
		velocity.x = -ACCELERATION_SPEED;
	}

	public void moveRight() {
		velocity.x = ACCELERATION_SPEED;
	}

	public void moveUp() {
		velocity.y = ACCELERATION_SPEED;
	}

	public void moveDown() {
		velocity.y = -ACCELERATION_SPEED;
	}

	public void stopX() {
		velocity.x = 0;
	}

	public void stopY() {
		velocity.y = 0;
	}
}