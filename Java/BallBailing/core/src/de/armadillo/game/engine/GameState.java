package de.armadillo.game.engine;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.MapObjects;

public class GameState {

	public static boolean won;
	public static float time;
	public static float distance;
	
	public static int level = 1;
	public static int enemyAmount;
	public static MapObjects collisionObjects;
	public static Music music;
	
	public static float playerY;
	public static float highestY;
		
	public static void update(float newY, float delta) {
		if(highestY >= newY)
			time -= delta;
		else {
			time = 10;
			highestY = newY;
		}
		playerY = newY;
		distance = 1200-playerY;
	}
	/**
	 * @return true if level winning/losing conditions are met
	 */
	public static boolean checkState() {
		
		// Win condition
		if(distance < 0) {
			won = true;
			level++;
			music.stop();
			reset();
			return true;
		}
		
		// Lose condition
		if(time < 0) {
			won = false;
			music.stop();
			reset();
			return true;
		}
			
		return false;
	}
	
	public static void initializeLevel() {
		switch(level) {
			case 1:
				enemyAmount = 5;
				collisionObjects = FileHandler.levelOne.getLayers().get("collision").getObjects();
				music = FileHandler.musicOne;
				break;
				
			case 2:
				enemyAmount = 10;
				collisionObjects = FileHandler.levelOne.getLayers().get("collision").getObjects();
				music = FileHandler.musicOne;
				break;
				
			case 3:
				enemyAmount = 15;
				collisionObjects = FileHandler.levelOne.getLayers().get("collision").getObjects();
				music = FileHandler.musicFour;
				break;
				
			case 4:
				enemyAmount = 25;
				collisionObjects = FileHandler.levelOne.getLayers().get("collision").getObjects();
				music = FileHandler.musicFour;
				break;
			
			default:
				break;
		}
	}
	
	public static void reset() {
		time = 10;
		highestY = 0;
	}
}