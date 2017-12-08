package de.armadillo.game.entities;

import com.badlogic.gdx.math.Vector2;

import de.armadillo.game.engine.FileHandler;

public class Enemy extends Entity {
	
	Player player;
	
	private final float MAX_VELOCITY = 5f;
	private final float REBOUND_INTENSITY = 0.5f;
	
	public Enemy(float x, float y, Player player) {
		super(x, y, FileHandler.enemy.getWidth()/2);
		this.player = player; 
	}
	
	@Override
	public void update(float delta) {
		seekPlayer(player.getPosition());
		acceleration.add(velocity.cpy().scl(delta));
		acceleration.limit(MAX_VELOCITY);
		position.add(acceleration);
		
		updateHitbox();
	}
	
	public void seekPlayer(Vector2 playerPos) {
		velocity.x = -(position.x - playerPos.x);
		velocity.y = -(position.y - playerPos.y);
		velocity.nor();
	}
	
	public void onCollision() {
		
		// Prevents being stuck in an entity
		position.x += velocity.cpy().rotate(180).x;
		position.y += velocity.cpy().rotate(180).y;
		
		acceleration.rotate(180);
		acceleration.scl(REBOUND_INTENSITY);
	}
}