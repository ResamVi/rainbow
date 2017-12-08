package com.armadillo.testimony.engine;

import java.util.Timer;
import java.util.TimerTask;

import com.armadillo.testimony.gameobjects.Enemy;
import com.armadillo.testimony.gameobjects.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;

// Holds all GameObjects
public class GameWorld
{
	// Player GameObject: Controlled by PlayerInput
	private Player player;
	
	// Enemy GameObject: Controlled by AI
	private Enemy enemy;
	
	// TiledMap
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
		
	// Timer in HUD
	private long seconds;
	private int minutes;
	
	// Camera
	private OrthographicCamera cam;
	
	// Physics engine / Stores physics bodies
	private World  world;
	
	// Initialize all GameObjects
	public GameWorld()
	{
		world = new World(new Vector2(0, 0f), true);
		player = new Player(Constants.PLAYER_START_X, Constants.PLAYER_START_Y, world);
		enemy = new Enemy(Constants.ENEMY_START_X, Constants.ENEMY_START_Y, world);
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Constants.WINDOWS_WIDTH, Constants.WINDOWS_HEIGHT);
		cam.position.set(player.getX(), player.getY(), 0);

		tiledMap = new TmxMapLoader().load("map/levelone.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		MapBodyBuilder.buildShapes(tiledMap, 21f, world); 
        
        world.setContactListener(new CollisionBehaviour(this));
		
        // Clock
        new Timer().schedule(new TimerTask(){
			@Override
			public void run() {
				if(seconds++ == 59) {
					seconds=0;
					minutes++;
				}
			}
        }, 0, 1000);
	}
	
	// Update every GameObject
	public void update()
	{
		// Updates player's coordinates
		player.update();
		
		// Updates enemy's coordinates
		enemy.update(player);
		
		// Update coordinates of Cam
		Vector3 position = cam.position;
		position.x += (player.getX()  * Constants.PIXELS_TO_METERS - position.x) * Constants.CAMERA_LERP * Gdx.graphics.getDeltaTime();
		position.y += (player.getY()  * Constants.PIXELS_TO_METERS - position.y) * Constants.CAMERA_LERP * Gdx.graphics.getDeltaTime();
		
		cam.position.set(position.x, position.y, 0);
		cam.update();
		
		// Update physics engine
		world.step(1/60f, 6, 2);
	}
	
	// Used by GameRenderer: Updates clock HUD
	public String getTime() {
		if(seconds < 10)
			return minutes + ":0" + seconds;
		else
			return minutes + ":" + seconds;
	}
	
	// Used by GameRenderer: Gets width/height attributes
	public Player getPlayer() {
		return player;
	}

	// Used by GameRenderer: Gets width/height attributes
	public Enemy getEnemy() {
		return enemy;
	}
	
	// Used by GameRenderer: For setting position
	public OrthographicCamera getCamera() {
		return cam;
	}
	
	// Used by GameRenderer: The map
	public TiledMapRenderer getTileMapRenderer() {
		return tiledMapRenderer;
	}
	
	// Used by GameRenderer: Access to all physics bodies and thus positions
	public World getBodies() {
		return world;
	}
}
