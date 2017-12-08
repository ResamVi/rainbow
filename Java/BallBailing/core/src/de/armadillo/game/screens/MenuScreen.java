package de.armadillo.game.screens;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Back;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import de.armadillo.game.animation.Smiley;
import de.armadillo.game.animation.SmileyLoop;
import de.armadillo.game.animation.Title;
import de.armadillo.game.animation.TitleAnimation;
import de.armadillo.game.animation.SmileyAnimation;
import de.armadillo.game.engine.FileHandler;
import de.armadillo.game.engine.Root;
import de.armadillo.game.inputs.ButtonListener;

public class MenuScreen implements Screen {

	private Root root;
	private Stage stage;
	
	private TweenManager manager;
	
	private Title title;
	private Smiley smiley;
	
	MenuScreen(final Root root) {
		this.root = root;
		
		// Setup music
		FileHandler.music.setLooping(true);
		FileHandler.music.play();
		
		// Setup Tween Engine
		manager = new TweenManager();
		title = new Title();
		smiley = new Smiley();
		
		Tween.registerAccessor(Title.class, new TitleAnimation());
		Tween.registerAccessor(Smiley.class, new SmileyAnimation());
		
		// Setup stage
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		initializeStage();
		
		// Setup title animations
		Tween.to(title, TitleAnimation.SCALE, 2.5f)
			.target(1.1f)
			.ease(Back.INOUT)
			.repeatYoyo(100, 0)
			.start(manager);
		
		Tween.to(title, TitleAnimation.POSITION_XY, 2.5f)
			.target(155.5f, 534.1f)
			.ease(Back.INOUT)
			.repeatYoyo(100, 0)
			.start(manager);	
		
		// Setup smiley animation
		Tween.to(smiley, SmileyAnimation.POSITION_XY, 3f).target(1080, 200)
		.setCallback(new SmileyLoop(this))
		.start(manager);
	}
	
	public void loop(float xStart, float yStart, float xEnd, float yEnd) {
		Timeline.createSequence()
			.push(Tween.set(smiley, SmileyAnimation.POSITION_XY).target(xStart, yStart))
			.push(Tween.to(smiley, SmileyAnimation.POSITION_XY, 3f).target(xEnd ,yEnd).ease(TweenEquations.easeNone).setCallback(new SmileyLoop(this)))
			.start(manager);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(177.0f, 207.0f, 241.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Title Animation
		stage.getActors().get(3).setScale(title.getScale());
		stage.getActors().get(3).setPosition(title.getX(), title.getY());
		
		// Smiley Animation
		stage.getActors().get(4).setPosition(smiley.getX(), smiley.getY());
		manager.update(delta);
		
		stage.draw();
	}

	public void initializeStage() {
		float xCenter = FileHandler.screenWidth/2 - FileHandler.buttonWidth/2;
		float yCenter = FileHandler.screenHeight/2 - FileHandler.buttonHeight/2;
		
		TextButton startButton = new TextButton("Start", FileHandler.style);
		startButton.setPosition(xCenter, yCenter + FileHandler.buttonHeight);
		startButton.addListener(new ButtonListener(root, startButton));
		stage.addActor(startButton);
		
		TextButton optionButton = new TextButton("Option", FileHandler.style);
		optionButton.setPosition(xCenter, yCenter - 10);
		optionButton.addListener(new ButtonListener(root, optionButton));
		stage.addActor(optionButton);
		
		TextButton quitButton = new TextButton("Quit", FileHandler.style);
		quitButton.setPosition(xCenter, yCenter - FileHandler.buttonHeight-20);
		quitButton.addListener(new ButtonListener(root, quitButton));
		stage.addActor(quitButton);
		
		Image titleImage = new Image(FileHandler.title);
		titleImage.setPosition(title.getX(), title.getY());
		stage.addActor(titleImage);
		
		Image smileyImage = new Image(FileHandler.smiley);
		smileyImage.setPosition(smiley.getX(), smiley.getY());
		stage.addActor(smileyImage);
	}
	
	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() {}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}
}