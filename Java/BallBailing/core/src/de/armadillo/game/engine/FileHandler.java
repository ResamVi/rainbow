package de.armadillo.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class FileHandler {

	// Menu
	public static Skin 				menuSkin;
	public static TextureAtlas 		pack;
	public static TextButtonStyle 	style;
	public static Texture			title;
	public static int 				screenWidth = Gdx.app.getGraphics().getWidth();
	public static int 				screenHeight = Gdx.app.getGraphics().getHeight();
	public static float 			buttonWidth;
	public static float 			buttonHeight;
	public static Music				music;
	public static Texture 			smiley;
	
	// Splash Screen
	public static Texture splash;
	
	// Player;
	public static Texture player;
	
	// Environment
	public static Texture enemy;
	
	// HUD
	public static BitmapFont hudFont;
	
	// Level 1
	public static TiledMap levelOne;
	public static Music musicOne;
	public static Music musicFour;
	
	// End Screen
	public static Sound success;
	public static Sound failure;
	public static BitmapFont titleFont;
	
	public static void load() {
		
		// Menu
		menuSkin = new Skin();
		
		pack = new TextureAtlas(Gdx.files.internal("buttonPack.pack"));
		menuSkin.addRegions(pack);
		
		style = new TextButtonStyle();
		style.font = new BitmapFont(Gdx.files.internal("font.fnt"));
		style.up = menuSkin.getDrawable("buttonUp");
		style.down = menuSkin.getDrawable("buttonDown");
		
		title = new Texture(Gdx.files.internal("title.png"));
		
		buttonWidth = menuSkin.getDrawable("buttonUp").getMinWidth();
		buttonHeight = menuSkin.getDrawable("buttonUp").getMinHeight();
		
		music = Gdx.audio.newMusic(Gdx.files.internal("menu.mp3"));
		
		smiley = new Texture(Gdx.files.internal("smiley.png"));
		
		// Splash Screen
		splash = new Texture(Gdx.files.internal("splash.png"));
		
		// Player
		player = new Texture(Gdx.files.internal("player.png"));
		
		// Environment
		enemy = new Texture(Gdx.files.internal("enemy.png"));
		
		// HUD
		hudFont = new BitmapFont(Gdx.files.internal("hud.fnt"));
		hudFont.setScale(0.15f);
		
		// Level 1
		levelOne = new TmxMapLoader().load("default.tmx");
		musicOne = Gdx.audio.newMusic(Gdx.files.internal("begin.mp3"));
		musicOne.setVolume(0.1f);
		
		// Level 4
		musicFour = Gdx.audio.newMusic(Gdx.files.internal("progress.mp3"));
		
		// End Screen
		titleFont = new BitmapFont(Gdx.files.internal("hud.fnt"));
		success = Gdx.audio.newSound(Gdx.files.internal("success.mp3"));
		failure = Gdx.audio.newSound(Gdx.files.internal("failure.mp3"));
	}
	
	public static void dispose() {
		menuSkin.dispose();
		pack.dispose();
		smiley.dispose();
		enemy.dispose();
		levelOne.dispose();
		music.dispose();
		musicOne.dispose();
		splash.dispose();
	}
}