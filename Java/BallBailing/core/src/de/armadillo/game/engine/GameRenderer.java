package de.armadillo.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20; 
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import de.armadillo.game.entities.Enemy;
import de.armadillo.game.entities.Player;

public class GameRenderer {

	private SpriteBatch batch;
	private ShapeRenderer shape;
	private TiledMapRenderer mapRenderer;
	private GameWorld world;
	private GameState state;
	
	OrthographicCamera cam;
	
	private Player player;
	private Enemy[] enemy;
	
	public GameRenderer(GameWorld world) {
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		mapRenderer = new OrthogonalTiledMapRenderer(FileHandler.levelOne);
		this.world = world;
		
		initAssets();
	}
	
	public void render() {
		Gdx.gl.glClearColor(0/255f, 67/255f, 55/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// World
		mapRenderer.setView(world.getCamera());
		mapRenderer.render();
		
		// Players	
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		
		batch.draw(FileHandler.player, player.getPosition().x, player.getPosition().y);
		
		for(int i = 0; i < enemy.length; i++)
			batch.draw(FileHandler.enemy, enemy[i].getPosition().x, enemy[i].getPosition().y);
		
		// HUD
		FileHandler.hudFont.draw(batch, "\'R\' to restart", 100, -25);
		
		FileHandler.hudFont.draw(batch, "Time left: " + GameState.time, player.getPosition().x-220, player.getPosition().y-140);
		FileHandler.hudFont.draw(batch, "Distance left: " + GameState.distance, player.getPosition().x-225, player.getPosition().y-150);
		batch.end();
		
		// Hitboxes
		shape.setProjectionMatrix(cam.combined);
		shape.setAutoShapeType(true);
		shape.begin(ShapeType.Line);
		
		shape.setColor(new Color(1,0,0,1));
		
		// Player
		shape.circle(player.getHitboxCircle().x, player.getHitboxCircle().y, player.getRadius());
		shape.rect(player.getHitboxRectangle().x, player.getHitboxRectangle().y, player.getHitboxRectangle().width, player.getHitboxRectangle().height);
		
		// Enemy
		for(int i = 0; i < enemy.length; i++) {
			shape.circle(enemy[i].getHitboxCircle().x, enemy[i].getHitboxCircle().y, enemy[i].getRadius());
			shape.rect(enemy[i].getHitboxRectangle().x, enemy[i].getHitboxRectangle().y, enemy[i].getHitboxRectangle().width, enemy[i].getHitboxRectangle().height);
		}
			
		shape.set(ShapeType.Filled);
		
		shape.end();
	}
	
	private void initAssets() {
		player = world.getPlayer();
		enemy = world.getEnemy();
		cam = world.getCamera();
		state = world.getState();
	}
}