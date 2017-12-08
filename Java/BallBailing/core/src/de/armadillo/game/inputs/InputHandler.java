package de.armadillo.game.inputs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import de.armadillo.game.engine.GameWorld;
import de.armadillo.game.engine.Root;
import de.armadillo.game.entities.Player;
import de.armadillo.game.screens.GameScreen;

public class InputHandler implements InputProcessor {

	Player player;
	Root root;
	
	public InputHandler(Root root, GameWorld world) {
		player = world.getPlayer();
		this.root = root; 
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.LEFT) {
			player.moveLeft();
		} else if (keycode == Input.Keys.RIGHT) {
			player.moveRight();
		} else if (keycode == Input.Keys.UP) {
			player.moveUp();
		} else if (keycode == Input.Keys.DOWN) {
			player.moveDown();
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT) {
			player.stopX();
		} else if (keycode == Input.Keys.UP || keycode == Input.Keys.DOWN) {
			player.stopY();
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		
		if(character == 'r')
			root.setScreen(new GameScreen(root));
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {return false;}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {return false;}

	@Override
	public boolean scrolled(int amount) {return false;}
}