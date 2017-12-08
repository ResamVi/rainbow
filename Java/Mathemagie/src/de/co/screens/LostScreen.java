package de.co.armadillo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.co.armadillo.engine.AssetLoader;
import de.co.armadillo.engine.GameState;
import de.co.armadillo.engine.GameWindow;
import de.co.armadillo.engine.GameWorld;

public class LostScreen extends SelectionScreen implements Screen{

	private GameWorld world;
	
	public LostScreen(final GameWindow window, GameWorld world, long id) {
		
		// Call constructor
		super();
		
		// Get instance of world
		this.world = world;
		
		// Restart button
		TextButton restart = new TextButton("Restart", AssetLoader.skin);
		restart.setSize(145, 50);
		restart.setPosition(155, 350);
		restart.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				AssetLoader.menuMusic.stop();
				if(!GameState.musicMute) AssetLoader.click.play();
				window.setScreen(new GameScreen(window));
			}
		});
		stage.addActor(restart);
		
		// Menu button
		TextButton menu = new TextButton("Menu", AssetLoader.skin);
		menu.setSize(145, 50);
		menu.setPosition(370, 350);
		menu.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!GameState.musicMute) AssetLoader.click.play();
				window.setScreen(new MenuScreen(window));
			}
		});
		stage.addActor(menu);
		
		// Lower volume
		AssetLoader.menuMusic.setVolume(id, 0.33f);
	}
	
	@Override
	public void render(float delta) {	
		
		// Clear screen
		Gdx.gl.glClearColor(0/255.0f, 0/255.0f, 0/255.0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// Draw background
		batch.begin();
		batch.draw(AssetLoader.bg, world.getBackground().getX(), world.getBackground().getY());
		batch.draw(AssetLoader.bg, world.getBackground().getX(), world.getBackground().getY()-840);
		
		// Draw message, score and stage
		AssetLoader.bigFont.draw(batch, "Game Over", 230, 300);
		AssetLoader.highFont.draw(batch, "Score: " + GameState.lastScore, 230, 350);
		AssetLoader.highFont.draw(batch, "Stage: " + GameState.lastStage, 230, 375);
		batch.end();
		
		// Draw stage
		stage.act(delta);
		stage.draw();
		
		Gdx.input.setInputProcessor(stage);
		
	}
}
