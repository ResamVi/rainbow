package de.armadillo.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;

import de.armadillo.game.entities.Enemy;
import de.armadillo.game.entities.Player;

public class GameWorld {

	private Player player;
	private Enemy[] enemy;
	private OrthographicCamera cam;
	private GameState state;
	
	private MapObjects collisionObjects;
	private Music music;
	
	private final int SPACING = 100;
	
	public GameWorld() {
		
		// Player
		player = new Player(100,70);
		
		// State 
		GameState.initializeLevel();
		
		// Enemy LEVELSPECIFIC
		enemy = new Enemy[GameState.enemyAmount];
		for(int i = 0; i < enemy.length; i++)
			enemy[i] = new Enemy(100+SPACING*i,(float) (250+Math.random()*100), player);
		
		// Map LEVELSPECIFIC
		collisionObjects = GameState.collisionObjects;
		
		// Music LEVELSPECIFIC
		GameState.music.play();
		
		// Camera
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	}
	
	public void update(float delta) {
		
		// Player
		player.update(delta);
		
		// Enemies
		for(int i = 0; i < enemy.length; i++)
			enemy[i].update(delta);
		
		// HUD
		GameState.update(player.getPosition().y, delta);
		
		// Camera
		centerCamera();
		
		checkCollision();
	}
	
	public void checkCollision() {
		
		// Player to Enemy
		for(int i = 0; i < enemy.length; i++) {
			if(player.collides(enemy[i].getHitboxCircle())) {
				player.onCollision();
				enemy[i].onCollision();
			}
		}		
		
		// Enemy to Enemy
		for(int i = 0; i < enemy.length; i++) {
			for(int u = 0; u < enemy.length; u++) {
				if(i != u && enemy[i].collides(enemy[u].getHitboxCircle())) {
					enemy[i].onCollision();
					enemy[u].onCollision();
				}
			}
		}
		
		// Player/Enemy to Environment
		for(RectangleMapObject r : collisionObjects.getByType(RectangleMapObject.class)) {
			Rectangle rectangle = r.getRectangle();
			
			// Player
			if(player.collides(rectangle))
				player.onCollision();
			
			// Enemy
			for(int i = 0; i < enemy.length; i++) {
				if(enemy[i].collides(rectangle))	
					enemy[i].onCollision();
			}
		}
	}
	
	public void centerCamera() {
		cam.position.set(player.getPosition().x, player.getPosition().y, 0);
		cam.update();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Enemy[] getEnemy() {
		return enemy;
	}
	
	public OrthographicCamera getCamera() {
		return cam;
	}
	
	public GameState getState() {
		return state;
	}
}