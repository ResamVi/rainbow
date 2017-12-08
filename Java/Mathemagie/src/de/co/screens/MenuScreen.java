package de.co.armadillo.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.co.armadillo.engine.AssetLoader;
import de.co.armadillo.engine.GameWindow;

public class MenuScreen extends SelectionScreen implements Screen{
	
	public MenuScreen(final GameWindow window) {
		
		// Call constructor
		super();
		
		// Play Button
		TextButton play = new TextButton("Play", AssetLoader.skin);
		play.setSize(275, 50);
		play.setPosition(223, 550);
		play.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				AssetLoader.click.play();
				window.setScreen(new DifficultyScreen(window));
			}
		});
		stage.addActor(play);
		
		// Option Button
		TextButton option = new TextButton("Options", AssetLoader.skin);
		option.setSize(275, 50);
		option.setPosition(223, 475);
		option.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				AssetLoader.click.play();
				window.setScreen(new OptionScreen(window));
			}
		});
		stage.addActor(option);
		
		// Credits Button
		TextButton credits = new TextButton("Credits", AssetLoader.skin);
		credits.setSize(275, 50);
		credits.setPosition(223, 400);
		credits.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				AssetLoader.click.play();
				window.setScreen(new CreditsScreen(window));
			}
		});
		stage.addActor(credits);

		// Buttons		
		TextButton quit = new TextButton("Quit", AssetLoader.skin);
		quit.setSize(275, 50);
		quit.setPosition(223, 325);
		quit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				AssetLoader.click.play();
				System.exit(0);
			}
		});
		stage.addActor(quit);
	}
	
	@Override
	public void render(float delta) {
		
		// Do the same
		super.render(delta);
		batch.end();
		
	}
}
