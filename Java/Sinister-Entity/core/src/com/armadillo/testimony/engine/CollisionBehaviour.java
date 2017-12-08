package com.armadillo.testimony.engine;

import com.armadillo.testimony.gameobjects.Bullet;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class CollisionBehaviour implements ContactListener
{
	GameWorld world;
	
	CollisionBehaviour(GameWorld world)
	{
		this.world = world;
	}
	
	@Override
	public void beginContact(Contact contact)
	{
		// Player meets Enemy or vice versa
		if((contact.getFixtureA().getBody() == world.getEnemy().getBody() && contact.getFixtureB().getBody() == world.getPlayer().getBody())
           || (contact.getFixtureA().getBody() == world.getPlayer().getBody() && contact.getFixtureB().getBody() == world.getEnemy().getBody()))
		{
			float floatX = MathUtils.random(-5, 5);
			float floatY = MathUtils.random(-5, 5);
            world.getEnemy().getBody().applyForceToCenter(floatX, floatY, true);
			world.getPlayer().getBody().applyForceToCenter(-floatX, -floatY, true);
		}
		
		// Player meets bullet
		for(Bullet bullet : world.getEnemy().getBulletSpawn().getBulletList()) {
			if((contact.getFixtureA().getBody() == bullet.getBody() && contact.getFixtureB().getBody() == world.getPlayer().getBody())
			           || (contact.getFixtureA().getBody() == world.getPlayer().getBody() && contact.getFixtureB().getBody() == bullet.getBody()))
			{
				bullet.setReadyForDeletion();
				float floatX = MathUtils.random(-5, 5);
				float floatY = MathUtils.random(-5, 5);
				contact.getFixtureA().getBody().applyForceToCenter(floatX, floatY, true);
				world.getPlayer().loseHealth();
			}
		}
		
		// Enemy meets bullet
		for(Bullet bullet : world.getPlayer().getBulletSpawn().getBulletList()) {
			if((contact.getFixtureA().getBody() == bullet.getBody() && contact.getFixtureB().getBody() == world.getEnemy().getBody())
			           || (contact.getFixtureA().getBody() == world.getEnemy().getBody() && contact.getFixtureB().getBody() == bullet.getBody()))
			{
				bullet.setReadyForDeletion();
				float floatX = MathUtils.random(-5, 5);
				float floatY = MathUtils.random(-5, 5);
				contact.getFixtureA().getBody().applyForceToCenter(floatX, floatY, true);
				contact.getFixtureB().getBody().applyForceToCenter(-floatX, -floatY, true);
			}
		}
	}

	@Override
	public void endContact(Contact contact) {}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {}
}
