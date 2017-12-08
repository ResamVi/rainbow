package com.armadillo.testimony.gameobjects;

import com.armadillo.testimony.engine.BulletSpawn;
import com.armadillo.testimony.engine.Constants;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Actor
{
	// Spawn GameObject: Responsible for shooting bullets
	protected BulletSpawn spawn;
	
	// Physics body
	protected Body body;
	
	Actor(float x, float y, float width, float height, World bodyStore)
	{
		spawn = new BulletSpawn(bodyStore);
		
		 // BODY DEFINITION
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        def.position.set((x + width/2) / Constants.PIXELS_TO_METERS, (y + height/2) / Constants.PIXELS_TO_METERS);
        body = bodyStore.createBody(def);
        
		// SHAPE
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / Constants.PIXELS_TO_METERS/2, height / Constants.PIXELS_TO_METERS/2); // Make method parameter
        
        // FIXTURE
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.density = 0.1f; // Make method parameter
        fixture.restitution = 0.5f; // Make method parameter
        
        body.createFixture(fixture);
	}
	
	// --- Getters (Interface for GameRenderer class)
	public float getX() {
		return body.getPosition().x;
	}
	
	public float getY() {
		return body.getPosition().y;
	}
	
	public Body getBody() {
		return body;
	}
	
	// Used by GameRenderer: Gets BulletList
	public BulletSpawn getBulletSpawn() {
		return spawn;
	}
}
