package de.co.armadillo.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.co.armadillo.engine.AssetLoader;
import de.co.armadillo.engine.GameState;
import de.co.armadillo.engine.GameWindow;

public class DifficultyScreen extends SelectionScreen implements Screen{
	
	public DifficultyScreen(final GameWindow window) {
		
		// Easy Button
		TextButton play = new TextButton("Rookie", AssetLoader.skin);
		play.setSize(275, 50);
		play.setPosition(223, 550);
		play.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				AssetLoader.click.play();
				AssetLoader.menuMusic.stop();
				GameState.difficulty = 0;
				window.setScreen(new GameScreen(window));
			}
		});
		stage.addActor(play);
		
		// Medium Button
		TextButton option = new TextButton("Mathter", AssetLoader.skin);
		option.setSize(275, 50);
		option.setPosition(223, 450);
		option.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				AssetLoader.click.play();
				AssetLoader.menuMusic.stop();
				GameState.difficulty = 1;
				window.setScreen(new GameScreen(window));
			}
		});
		stage.addActor(option);
		
		// Hard Button
		TextButton credits = new TextButton("Impossibru", AssetLoader.skin);
		credits.setSize(275, 50);
		credits.setPosition(223, 350);
		credits.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				AssetLoader.click.play();
				AssetLoader.menuMusic.stop();
				GameState.difficulty = 2;
				window.setScreen(new GameScreen(window));
			}
		});
		stage.addActor(credits);
	}
	
	@Override
	public void render(float delta) {
		
		// Do same as in default
		super.render(delta);	
		batch.end();
		
	}
}
