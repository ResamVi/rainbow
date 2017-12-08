package de.co.armadillo.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.co.armadillo.engine.AssetLoader;
import de.co.armadillo.engine.GameState;
import de.co.armadillo.engine.GameWindow;

public class OptionScreen extends SelectionScreen implements Screen{
	
	public OptionScreen(final GameWindow window) {
		
		// Call constructor
		super();
		
		// Check states to set textfields appropriately
		String label;
		if(GameState.musicMute) label = "  OFF";
		else label = "  ON";
		
		// Textfield for sound
		final TextField tfSound = new TextField(label, AssetLoader.skin);
		tfSound.setDisabled(true);
		tfSound.setPosition(430, 550);
		tfSound.setSize(55, 50);
		tfSound.setMaxLength(9);
		stage.addActor(tfSound);
		
		if(GameState.soundMute) label = "  OFF";
		else label = "  ON";
		
		// Textfield for sfx
		final TextField tfSFX = new TextField(label, AssetLoader.skin);
		tfSFX.setDisabled(true);
		tfSFX.setPosition(430, 475);
		tfSFX.setSize(55, 50);
		tfSFX.setMaxLength(9);
		stage.addActor(tfSFX);
		
		// Textfield for fullscreen
		final TextField tfFullscreen = new TextField("  OFF", AssetLoader.skin);
		tfFullscreen.setDisabled(true);
		tfFullscreen.setPosition(430, 400);
		tfFullscreen.setSize(55, 50);
		tfFullscreen.setMaxLength(9);
		stage.addActor(tfFullscreen);
		
		// Button for muting music
		TextButton muteMusic = new TextButton("Music", AssetLoader.skin);
		muteMusic.setSize(175, 50);
		muteMusic.setPosition(230, 550);
		muteMusic.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				AssetLoader.click.play();
				
				// Mute
				if(!GameState.musicMute) {
					AssetLoader.menuMusic.stop();
					GameState.musicMute = true;
					tfSound.setText("  OFF");

				// Unmute
				}else{
					AssetLoader.menuMusic.stop();
					AssetLoader.menuMusic.loop();
					GameState.musicMute = false;
					tfSound.setText("  ON");
				}
			}
		});
		stage.addActor(muteMusic);
		
		// Button for muting sound
		TextButton muteSound = new TextButton("SFX", AssetLoader.skin);
		muteSound.setSize(175, 50);
		muteSound.setPosition(230, 475);
		muteSound.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				AssetLoader.click.play();
				
				// Mute
				if(!GameState.soundMute) {
					GameState.soundMute = true;
					tfSFX.setText("  OFF");
					AssetLoader.disposeSound();
				
				// Unmute
				}else{
					GameState.soundMute = false;
					tfSFX.setText("  ON");
					AssetLoader.loadSound();
				}
			}
		});
		stage.addActor(muteSound);

		// Button for toggling fullscreen
		TextButton fullscreen = new TextButton("Fullscreen", AssetLoader.skin);
		fullscreen.setSize(175, 50);
		fullscreen.setPosition(230, 400);
		/*fullscreen.addListener(new ClickListener() {
		*	@Override
		*	public void clicked(InputEvent event, float x, float y) {
		*		AssetLoader.click.play();
		*		if(!GameState.fullscreen) {
		*			Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width, Gdx.graphics.getDesktopDisplayMode().height, true);
		*			GameState.fullscreen = true;
		*			tfFullscreen.setText("  ON");
		*		}else{
		*			Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width, Gdx.graphics.getDesktopDisplayMode().height, false);
		*			GameState.fullscreen = false;
		*			tfFullscreen.setText("  OFF");
		*		}
		*		}
		});*/
		stage.addActor(fullscreen);

		// Back button		
		TextButton back = new TextButton("Back", AssetLoader.skin);
		back.setSize(325, 50);
		back.setPosition(200, 325);
		back.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				AssetLoader.click.play();
				window.setScreen(new MenuScreen(window));
			}
		});
		stage.addActor(back);
	}
	
	@Override
	public void render(float delta) {
		
		super.render(delta);
		
	}
}
