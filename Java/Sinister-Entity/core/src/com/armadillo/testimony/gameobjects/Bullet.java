package com.armadillo.testimony.gameobjects;

import com.armadillo.testimony.engine.BulletSpawn;
import com.armadillo.testimony.engine.Constants;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Bullet
{
	// Identification index
	private final int index;
	
	// Rotation attribute
	private Vector2 direction;

	// Time measuring variables
	private long startTime;
	private long elapsedTime;
	
	// Get ability to delete itself
	private BulletSpawn spawn;
	
	// Start position
	private float startX;
	private float startY;
	
	// Physics entity
	private Body body;
	
	// Ready for deletion
	private boolean delete;
	
	//(x, y) for position, width and height are object specific
	public Bullet(int index, BulletSpawn spawn, float x, float y, World bodyStore) 
	{
		this.index = index;
		delete = false;
		startX = x;
		startY = y;
		startTime = System.nanoTime();
		this.spawn = spawn;
		
		 // BODY DEFINITION
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        def.position.set((x + Constants.BULLET_RADIUS/2) / Constants.PIXELS_TO_METERS, (y + Constants.BULLET_RADIUS/2) / Constants.PIXELS_TO_METERS);
        body = bodyStore.createBody(def);
        
		// SHAPE
        CircleShape shape = new CircleShape();
        shape.setRadius(Constants.BULLET_RADIUS);
        shape.setPosition(new Vector2(x, y));
        
        // FIXTURE
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.density = 0.1f; // Make method parameter
        fixture.restitution = 0.5f; // Make method parameter
        
        body.createFixture(fixture);
	}
	
	// --- Updaters
	// Move in specific direction
	public void update()
	{		
        body.setLinearVelocity(direction.x * Constants.BULLET_SPEED, direction.y * Constants.BULLET_SPEED);
        
        if(delete) {
        	body.setActive(false);
        	delete = false;
        }
        
        
		// Measure time: Delete after BULLET_DURATION
		elapsedTime = System.nanoTime() - startTime;
		if(elapsedTime / 1000000000 > Constants.BULLET_DURATION)
			spawn.deleteBullet(0);
	}
	
	// Start motion when mouse is clicked	
	public void setInMotion(Vector2 direction) {
		this.direction = direction.limit(1);
	}
	
	// --- Getters (Interface for GameRenderer class)
	public float getX() {
		return body.getPosition().x * Constants.PIXELS_TO_METERS;
	}
	
	public float getY() {
		return body.getPosition().y * Constants.PIXELS_TO_METERS;
	}
	
	public float getStartX() {
		return startX * Constants.PIXELS_TO_METERS;
	}
	
	public float getStartY() {
		return startY * Constants.PIXELS_TO_METERS;
	}
	
	public Vector2 getRotation() {
		return direction;
	}
	
	public Body getBody() {
		return body;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setReadyForDeletion() {
		delete = true;
	}
}
