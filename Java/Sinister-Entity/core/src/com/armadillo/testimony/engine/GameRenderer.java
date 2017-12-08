package com.armadillo.testimony.engine;

import com.armadillo.testimony.gameobjects.Bullet;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class GameRenderer 
{
	// Pencil for shapes
	private ShapeRenderer shape;
	
	// Displaying on screen
	private SpriteBatch batch;
	
	// Displaying on screen - hud
	private SpriteBatch hud;
	
	// Holds GameObjects
	private GameWorld world;
	
	// For physics debugging
	private Matrix4 debugMatrix;
	private Box2DDebugRenderer debugRenderer;

	// Initialize objects
	public GameRenderer(GameWorld world)
	{	
		// Make the renderer draw with initialized camera
		shape = new ShapeRenderer();
		shape.setProjectionMatrix(world.getCamera().combined);
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(world.getCamera().combined);
		
		// Hud not relative to camera
		hud = new SpriteBatch();
		
		// Enables physics debugging mode 
		debugRenderer = new Box2DDebugRenderer();
		
		// Save world object in local variable
		this.world = world;
	}
	
	// Render all GameObjects
	public void render()
	{
		// Clear screen
		Gdx.gl.glClearColor(0, 191/255f, 255/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Map
		world.getTileMapRenderer().setView(world.getCamera());
		world.getTileMapRenderer().render();
		
		batch.setProjectionMatrix(world.getCamera().combined);
		batch.begin();
		
		// Player
		batch.draw(AssetLoader.playerKing, world.getPlayer().getX()  * Constants.PIXELS_TO_METERS-Constants.PLAYER_WIDTH/2, world.getPlayer().getY() * Constants.PIXELS_TO_METERS - Constants.PLAYER_HEIGHT/2, 
				Constants.PLAYER_WIDTH/2.0f, Constants.PLAYER_HEIGHT/2.0f, 
				Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT, 
				1f, 1f, 0);
		
		// Enemy
		batch.draw(AssetLoader.enemyPawn, world.getEnemy().getX()  * Constants.PIXELS_TO_METERS-Constants.ENEMY_WIDTH/2, world.getEnemy().getY() * Constants.PIXELS_TO_METERS - Constants.ENEMY_HEIGHT/2,
				Constants.ENEMY_WIDTH/2, Constants.ENEMY_HEIGHT/2,
				Constants.ENEMY_WIDTH, Constants.ENEMY_HEIGHT,
				1f, 1f, 0f);
		
		// Arrow
		batch.draw(AssetLoader.arrow, world.getPlayer().getX()  * Constants.PIXELS_TO_METERS-AssetLoader.arrow.getRegionWidth()/2, world.getPlayer().getY() * Constants.PIXELS_TO_METERS - AssetLoader.arrow.getRegionHeight()/2,
				AssetLoader.arrow.getRegionWidth()/2, AssetLoader.arrow.getRegionHeight()/2, 
				AssetLoader.arrow.getRegionWidth(), AssetLoader.arrow.getRegionHeight(), 
				2f, 2f, -world.getPlayer().getRotation().angle());
		
		// Bullets
		for(Bullet bullet: world.getPlayer().getBulletSpawn().getBulletList()) {
			if(bullet.getBody().isActive())
				batch.draw(AssetLoader.bullet, bullet.getX()+bullet.getStartX()-7.5f, bullet.getY()+bullet.getStartY()-7.5f);
		}
		for(Bullet bullet: world.getEnemy().getBulletSpawn().getBulletList()) {
			if(bullet.getBody().isActive())
				batch.draw(AssetLoader.bullet, bullet.getX()+bullet.getStartX(), bullet.getY()+bullet.getStartY());
		}
		
		batch.end();
		
		// Physics Debugging
		debugMatrix = batch.getProjectionMatrix().cpy().scale(Constants.PIXELS_TO_METERS, Constants.PIXELS_TO_METERS, 0);
		debugRenderer.render(world.getBodies(), debugMatrix);

		// Graphics Debugging
		//debugModeOn();
		
		// Drawing hud
		hud.begin();
		hud.draw(AssetLoader.statusbar, 10, Constants.WINDOWS_HEIGHT*0.83f);
		hud.draw(AssetLoader.healthbars[world.getPlayer().getHealth()], 10, Constants.WINDOWS_HEIGHT*0.83f);
		hud.draw(AssetLoader.manabars[world.getPlayer().getMana()], 10, Constants.WINDOWS_HEIGHT*0.83f);
		hud.draw(AssetLoader.abilitybar, Constants.WINDOWS_WIDTH*0.5f-124, 10);
		hud.draw(AssetLoader.selectionbox, world.getPlayer().getAbility(), 28);
		
		// Splash hit
		if(world.getPlayer().isHit())
			hud.draw(AssetLoader.hitSplash, 0, 0);
		//System.out.println(world.getPlayer().isDead());
		// Respawn Text
		if(world.getPlayer().isDead()) {
			AssetLoader.timeFontShadow.draw(hud, "RESPAWNING", Constants.WINDOWS_WIDTH/2-80, Constants.WINDOWS_HEIGHT/2+50);
			AssetLoader.timeFont.draw(hud, "RESPAWNING", Constants.WINDOWS_WIDTH/2-80, Constants.WINDOWS_HEIGHT/2+50);
		}
		
		AssetLoader.timeFontShadow.draw(hud, world.getTime(), Constants.WINDOWS_WIDTH/2-50, Constants.WINDOWS_HEIGHT*0.95f);
		AssetLoader.timeFont.draw(hud, world.getTime(), Constants.WINDOWS_WIDTH/2-50, Constants.WINDOWS_HEIGHT*0.95f);
		
		hud.end();
	}
	
	void debugModeOn()
	{
		shape.begin(ShapeType.Line);
		shape.setProjectionMatrix(world.getCamera().combined);
		
		// Color Black
		shape.setColor(1, 0, 0, 1);
		float x = world.getPlayer().getX() * Constants.PIXELS_TO_METERS + world.getPlayer().getRotation().rotate(180).limit(1).x*50;
		float y = world.getPlayer().getY() * Constants.PIXELS_TO_METERS + world.getPlayer().getRotation().rotate(180).limit(1).y*50;
		System.out.println(x + " : " + y);
		shape.circle(x, y, 3);
		shape.end();
		
	}
	
	public void dispose()
	{
		batch.dispose();
		shape.dispose();
	}
}
