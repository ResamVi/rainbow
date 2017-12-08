package de.co.armadillo.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetLoader {

	public static TextureRegion cross;
	public static Texture bg;
	
	public static TextureRegion credits;
	public static TextureRegion armadillo;
	
	public static BitmapFont font;
	public static BitmapFont highFont;
	public static BitmapFont bigFont;
	public static BitmapFont mathFont;
	public static BitmapFont mathFont2;
	
	public static TextureRegion health;
	public static TextureRegion title;
	public static TextureRegion pi;
	public static TextureRegion binomial;
	public static TextureRegion complex;
	public static TextureRegion squareroot;
	
	public static Sound menuMusic;
	public static Sound shoot;
	public static Sound blob;
	public static Sound right;
	public static Sound click;
	
	public static Skin skin;
	
	public static void load() {
		
		// Remove enforcement
		Texture.setEnforcePotImages(false);
		
		// Cross
		cross = new TextureRegion(new Texture(Gdx.files.internal("data/game/cross.png")));
		
		// Background
		bg = new Texture("data/game/background.png");
	
		// Armadillo picture
		armadillo = new TextureRegion(new Texture(Gdx.files.internal("data/menu/armadillo.png")));
		armadillo.flip(false, true);
		
		// Health
		health = new TextureRegion(new Texture(Gdx.files.internal("data/game/health.png")));
		health.flip(false, true);
		
		// Name
		credits = new TextureRegion(new Texture(Gdx.files.internal("data/menu/credits.png")));
		credits.flip(false, true);
		
		// Skin
		skin = new Skin(Gdx.files.internal("data/misc/uiskin.json"));
		
		// Title
		title = new TextureRegion(new Texture(Gdx.files.internal("data/menu/title.png")));
		title.flip(false, true);
		
		// Mathmatical signs
		pi = new TextureRegion(new Texture(Gdx.files.internal("data/menu/pi.png")));
		squareroot = new TextureRegion(new Texture(Gdx.files.internal("data/menu/squareroot.png")));
		binomial = new TextureRegion(new Texture(Gdx.files.internal("data/menu/a2abb.png")));
		complex = new TextureRegion(new Texture(Gdx.files.internal("data/menu/abi.png")));
		
		pi.flip(false, true);
		binomial.flip(false, true);
		complex.flip(false, true);
		squareroot.flip(false, true);
		
		// Sounds
		menuMusic = Gdx.audio.newSound(Gdx.files.internal("data/sound/menumusic.wav"));
		loadSound();
		
		// Font
		font = new BitmapFont();
		font.setScale(1, -1);
		highFont = new BitmapFont(Gdx.files.internal("data/font/font.fnt"), new TextureRegion(new Texture(Gdx.files.internal("data/font/font.png"))), false);
		highFont.setScale(1, -1);
		bigFont = new BitmapFont(Gdx.files.internal("data/font/bigfont.fnt"), new TextureRegion(new Texture(Gdx.files.internal("data/font/bigfont.png"))), false);
		bigFont.setScale(1, -1);
		mathFont = new BitmapFont(Gdx.files.internal("data/font/math.fnt"), new TextureRegion(new Texture(Gdx.files.internal("data/font/math.png"))), false);
		mathFont.setScale(.75f, -.75f);
		mathFont2 = new BitmapFont(Gdx.files.internal("data/font/math2.fnt"), new TextureRegion(new Texture(Gdx.files.internal("data/font/math2.png"))), false);
		mathFont2.setScale(.75f, -.75f);
	}
	
	public static void loadSound() {
		shoot = Gdx.audio.newSound(Gdx.files.internal("data/sound/shoot.wav"));
		blob = Gdx.audio.newSound(Gdx.files.internal("data/sound/blob.wav"));
		right = Gdx.audio.newSound(Gdx.files.internal("data/sound/right.wav"));
		click = Gdx.audio.newSound(Gdx.files.internal("data/sound/click.wav"));
	}
	
	public static void disposeSound() {
		shoot.dispose();
		blob.dispose();
		right.dispose();
		click.dispose();
	}
	
	public static void dispose() {
		font.dispose();
		highFont.dispose();
		bigFont.dispose();
		bg.dispose();
		menuMusic.dispose();
		
	}
}
