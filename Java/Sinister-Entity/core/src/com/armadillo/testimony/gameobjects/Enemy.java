package com.armadillo.testimony.gameobjects;

import com.armadillo.testimony.engine.Constants;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

// Represents an Enemy GameObject
public class Enemy extends Actor
{
	// Time measuring
	private long elapsedTime;
	private long startTime;
	
	// Initialize
	public Enemy(float x, float y, World world) 
	{
		// GameObject Constructor
		super(x, y, Constants.ENEMY_WIDTH, Constants.ENEMY_HEIGHT, world);
		
		startTime = System.nanoTime();
        body.setLinearDamping(0.9f);		
	}
	
	// Main loop of this GameObject
	public void update(Player player)
	{
		// Shoots relative to object's coordinates
		spawn.update();
		move();
		elapsedTime = System.nanoTime() - startTime;
		
		if(elapsedTime/10000000 + MathUtils.random(400)> Constants.SHOOTING_FREQUENCY) {
			shoot(player.getX(), player.getY());
			startTime = System.nanoTime();
		}
	}

	// Shoot function used by enemy itself
	public void shoot(float targetX, float targetY)
	{
		Vector2 direction = new Vector2(targetX-getX(), targetY-getY());
		spawn.spawnBullet(getX()-Constants.ENEMY_WIDTH/2/Constants.PIXELS_TO_METERS, getY()-Constants.ENEMY_HEIGHT/2/Constants.PIXELS_TO_METERS, direction);
	}
	
	// Move in specific direction
	public void move() {
		
		
	}
}
