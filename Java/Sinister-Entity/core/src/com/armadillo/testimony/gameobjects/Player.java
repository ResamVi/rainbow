package com.armadillo.testimony.gameobjects;

import java.util.Timer;
import java.util.TimerTask;

import com.armadillo.testimony.engine.BulletSpawn;
import com.armadillo.testimony.engine.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

// Represents a Player GameObject
public class Player extends Actor
{
	// Rotation attribute
	private Vector2 rotation;
	
	// Direction this GameObject should move to
	private boolean right, left, up, down;
	
	// Used by GameRenderer for hit splash
	private boolean isHit;
	private boolean dead;
	
	// 10=full 0=dead
	private int health;
	private int mana;
	
	// 0=leftmost 4=rightmost
	private int ability;
	
	private Timer manaRegen;
	
	// Initialize
	public Player(float x, float y, World bodyStore) 
	{
		super(x, y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT, bodyStore);
		rotation = new Vector2(0, 0);
		health = 10;
		mana = 5;
		ability = 0;
		manaRegen = new Timer();
		manaRegen.schedule(new TimerTask(){
			@Override
			public void run() {
				System.out.println("RUN");
				if(mana < 10)
					mana++;
			}}, 0, 1000);
	}
	
	// Main loop of this GameObject
	public void update()
	{
		// Shoots relative to object's coordinates
		spawn.update();
		move();
		aim();
		checkHealth();
	}

	// Shoot function used by PlayerInput
	public void shoot(float targetX, float targetY)
	{
		loseMana(1);
		if(mana >= 0)
		{
			Vector2 direction = new Vector2(targetX-Constants.WINDOWS_WIDTH/2, Constants.WINDOWS_HEIGHT/2-targetY);
			float x = getX() + rotation.rotate(180).limit(1).x*0.5f;
			float y = getY() + rotation.rotate(180).limit(1).y*0.5f;
			spawn.spawnBullet(x, y, direction);
		}
	}
	
	// Move in specific direction
	private void move()
	{
		// Get Input		
		float xVel = 0;
		float yVel = 0;
		
		if(left) xVel = -Constants.PLAYER_VELOCITY;
		if(right) xVel = Constants.PLAYER_VELOCITY;
		if(up) yVel = Constants.PLAYER_VELOCITY;
		if(down) yVel = -Constants.PLAYER_VELOCITY;
		
		body.setLinearVelocity(xVel, yVel);
	}
	
	// Aim in specific direction
	private void aim()
	{
		float xMiddle = Constants.WINDOWS_WIDTH/2;
		float yMiddle = Constants.WINDOWS_HEIGHT/2;
		rotation = new Vector2(xMiddle-Gdx.input.getX(), yMiddle-Gdx.input.getY());
	}
	
	// Update direction booleans (Interface for PlayerInput class)
	public void switchDirection(int keycode)
	{
		switch(keycode)
		{
			case Input.Keys.A: left = !left; break;
			case Input.Keys.D: right = !right; break;
			case Input.Keys.S: down = !down; break;
			case Input.Keys.W: up = !up; break;
			default: break;
		}
	}
	
	// Used by PlayerInput
	public void switchAbility(int direction)
	{
		if(direction > 0 && ability < 3) ability++;
		else if(direction < 0 && ability > 0) ability--;
	}
	
	// Used by: GameRenderer
	public Vector2 getRotation() {
		return rotation;
	}
	
	// Used by GameRenderer: Gets BulletList
	public BulletSpawn getBulletSpawn() {
		return spawn;
	}

	// Used by: GameRenderer
	public int getHealth() {
		return health;
	}
	
	// Used by CollisionBehaviour
	public void loseHealth()
	{
		if(health > 0)
			health--;
		
		isHit = true;
		
		new Timer().schedule(new TimerTask(){
			@Override
			public void run() {
				isHit = false;
			}}, 100);
	}
	
	public void loseMana(int amount)
	{
		if(mana-amount >= 0)
			mana-=amount;
		else
			System.out.println("Insufficient mana");
	}

	// Used by: GameRenderer
	public float getAbility()
	{
		switch(ability)
		{
			case 0: return 422;
			case 1: return 486;
			case 2: return 547;
			case 3: return 608;
			default: return 0;
		}
	}

	// Used by: GameRenderer
	public boolean isHit() {
		return isHit;
	}
	
	// Respawn when health is 0
	private void checkHealth()
	{
		if(health == 0)
		{
			body.setTransform(Constants.PLAYER_START_X/Constants.PIXELS_TO_METERS, 
							  Constants.PLAYER_START_Y/Constants.PIXELS_TO_METERS, 0);
			dead = true;
			new Timer().schedule(new TimerTask(){
				@Override
				public void run() {
					dead = false;
					health = 10;
				}}, 1000);
		}
	}

	public boolean isDead() {
		return dead;
	}

	public int getMana() {
		return mana;
	}
	
}
