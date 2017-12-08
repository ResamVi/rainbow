package com.armadillo.testimony.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class AssetLoader
{
	// Splash Screen
	public static Texture splash;
	
	// Menu Screen
	public static BitmapFont titleFont;
	public static Texture background;
	public static Sound backgroundMusic;
	public static Skin skin;
	
	// Game Screen
	public static Pixmap mousePointer;
	public static TextureRegion arrow;
	public static TextureRegion playerKing;
	public static TextureRegion playerQueen;
	public static TextureRegion enemyPawn;
	public static TextureRegion bullet;
	
	// HUD
	public static TextureRegion statusbar;
	public static TextureRegion[] healthbars;
	public static TextureRegion[] manabars;
	public static TextureRegion abilitybar;
	public static TextureRegion selectionbox;
	public static TextureRegion hitSplash;
	public static BitmapFont timeFont;
	public static BitmapFont timeFontShadow;
	
	public static void load()
	{
		// Splash Screen
		splash = new Texture(Gdx.files.internal("menu/splash.png"));
		
		// --- MENU ---
		// Menu music
		backgroundMusic = Gdx.audio.newSound(Gdx.files.internal("menu/backgroundMusic.mp3"));
		
		// Menu background
		background = new Texture(Gdx.files.internal("menu/background.png"));
		
		// Create font for title
		titleFont = new BitmapFont(Gdx.files.internal("menu/title.fnt"));
		titleFont.setColor(Color.YELLOW);
		
		// Create skin for button style
		skin = new Skin();
		skin.add("default", new BitmapFont(Gdx.files.internal("menu/buttonFont.fnt")));
		skin.addRegions(new TextureAtlas(Gdx.files.internal("menu/buttonSkin.pack")));
		
		// Create a button style
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("button-up");
		textButtonStyle.down = skin.newDrawable("button-up");
		textButtonStyle.checked = skin.newDrawable("button-up");
		textButtonStyle.over = skin.newDrawable("button-down");
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
		
		// --- GAME ---
		// Player Textures
		arrow = new TextureRegion(new Texture(Gdx.files.internal("player/arrow.png")));
		playerKing = new TextureRegion(new Texture(Gdx.files.internal("player/king.png")));
		
		// Player alt Texture
		playerQueen = new TextureRegion(new Texture(Gdx.files.internal("player/queen.png")));
		
		// Enemy Textures
		enemyPawn = new TextureRegion(new Texture(Gdx.files.internal("entities/pawn.png")));
		
		// Bullets
		bullet = new TextureRegion(new Texture(Gdx.files.internal("player/bullet1.png")));
		
		// --- HUD ---
		// Status bars
		statusbar = new TextureRegion(new Texture(Gdx.files.internal("hud/statusbar.png")));
		selectionbox = new TextureRegion(new Texture(Gdx.files.internal("hud/selectionbox.png")));
		
		// Health bars
		healthbars = new TextureRegion[11];
		for(int i = 0; i < healthbars.length; i++)
		{
			healthbars[i] = new TextureRegion(new Texture(Gdx.files.internal("health/health" + i + ".png")));
		}
		
		manabars = new TextureRegion[11];
		for(int i = 0; i < manabars.length; i++)
		{
			manabars[i] = new TextureRegion(new Texture(Gdx.files.internal("mana/mana" + i + ".png")));
		}
		
		// Set mouse cursor
		mousePointer = new Pixmap(Gdx.files.internal("hud/aim.png"));
		
		abilitybar = new TextureRegion(new Texture(Gdx.files.internal("hud/abilitybar.png")));
		
		// Hit splash
		hitSplash = new TextureRegion(new Texture(Gdx.files.internal("hud/hitsplash.png")));
		
		// Time font
		timeFont = new BitmapFont(Gdx.files.internal("hud/timeFont.fnt"));
		timeFont.getData().setScale(0.5f);
		timeFontShadow = new BitmapFont(Gdx.files.internal("hud/timeFontShadow.fnt"));
		timeFontShadow.getData().setScale(0.5f);
	}
	
	public static void dispose()
	{
		arrow.getTexture().dispose();
		playerKing.getTexture().dispose();
		playerQueen.getTexture().dispose();
		enemyPawn.getTexture().dispose();
		bullet.getTexture().dispose();
		
		statusbar.getTexture().dispose();
		abilitybar.getTexture().dispose();
		selectionbox.getTexture().dispose();
		hitSplash.getTexture().dispose();
		timeFont.dispose();
		timeFontShadow.dispose();
		
		splash.dispose();
		titleFont.dispose();
		background.dispose();
		backgroundMusic.dispose();
		skin.dispose();
		mousePointer.dispose();
	}
}
