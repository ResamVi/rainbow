package com.armadillo.testimony.engine;

public class Constants {

	// Window Attributes
	public static final int WINDOWS_WIDTH = 1080;
	public static final int WINDOWS_HEIGHT = 720;
	public static final String TITLE = "Sinister Entity - (c) Julien Midedji";
	
	// Player Attributes	
	public static final int PLAYER_VELOCITY = 20;
	
	public static final float PLAYER_START_X = 750;
	public static final float PLAYER_START_Y = 700;
	
	public static final int PLAYER_WIDTH = AssetLoader.playerKing.getRegionWidth();//50;
	public static final int PLAYER_HEIGHT = AssetLoader.playerKing.getRegionHeight();//50;
	
	// Bullet Attributes
	public static final float BULLET_RADIUS = 5 / 100f;
	public static final float BULLET_SPEED = 5f;
	public static final int BULLET_DURATION = 1;
	
	// Splash Attributes
	public static final float SPLASH_PAUSE = 1.5f;
	public static final float SPLASH_END_PAUSE = 0.75f;

	// Enemy Attributes
	public static final float ENEMY_START_X = 2400;
	public static final float ENEMY_START_Y = 2400;
	public static final float SHOOTING_FREQUENCY = 400;
	
	public static final int ENEMY_WIDTH = AssetLoader.enemyPawn.getRegionWidth();
	public static final int ENEMY_HEIGHT = AssetLoader.enemyPawn.getRegionHeight();
	
	// Game Settings
	public static final float CAMERA_LERP = 5f;
	public static final float PIXELS_TO_METERS = 100f;
}
