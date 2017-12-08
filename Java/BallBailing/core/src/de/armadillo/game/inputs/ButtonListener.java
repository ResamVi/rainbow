package de.armadillo.game.inputs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.armadillo.game.engine.FileHandler;
import de.armadillo.game.engine.Root;
import de.armadillo.game.screens.GameScreen;
import de.armadillo.game.screens.OptionScreen;

public class ButtonListener extends ClickListener {

	private String name;
	private Root root;

	public ButtonListener(Root root, TextButton button) {
		name = button.getLabel().getText().toString();
		this.root = root;
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		if (event.getType() == InputEvent.Type.touchUp) {
			
			FileHandler.music.stop();
			
			if (name.equals("Start")) {
				root.setScreen(new GameScreen(root));
			} else if (name.equals("Option")) {
				root.setScreen(new OptionScreen(root));
			} else if (name.equals("Quit")) {
				Gdx.app.exit();
			}
		}
	}
}