package de.co.armadillo.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.co.armadillo.engine.AssetLoader;
import de.co.armadillo.engine.GameWindow;

public class CreditsScreen extends SelectionScreen implements Screen{
	
	public CreditsScreen(final GameWindow window) {
		
		// Call constructor
		super();

		// Buttons		
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
		
		// Add labels
		AssetLoader.highFont.setColor(255/255.0f, 165/255.0f, 0/255.0f, 1f);
		AssetLoader.highFont.draw(batch, "Designed and", 223, 240);
		AssetLoader.highFont.draw(batch, "Programmed by", 223, 260);
		AssetLoader.highFont.draw(batch, "Music by", 223, 320);
		AssetLoader.highFont.draw(batch, "For", 223, 380);
		
		AssetLoader.highFont.setColor(255/255.0f, 255/255.0f, 255/255.0f, 1f);
		AssetLoader.highFont.draw(batch, "Julien Midedji", 385, 280);
		AssetLoader.highFont.draw(batch, "Jake Chudnow", 380, 340);
		AssetLoader.highFont.draw(batch, "Armadillo Production", 325, 400);
		batch.end();

	}
}
