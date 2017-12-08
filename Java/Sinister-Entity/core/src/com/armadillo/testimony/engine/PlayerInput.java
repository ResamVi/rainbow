package com.armadillo.testimony.engine;

import com.armadillo.testimony.gameobjects.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class PlayerInput implements InputProcessor
{
	// Get Access to direction variables
	Player player;
	
	// Get Player from GameScreen class
	public PlayerInput(Player player)
	{
		this.player = player;
	}
	
	@Override
	public boolean keyDown(int keycode)
	{
		// Moving
		player.switchDirection(keycode);
		
		// Changing abilities
		if(Input.Keys.Q == keycode)
			player.switchAbility(-1);
		if(Input.Keys.E == keycode)
			player.switchAbility(1);
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		// Stopping
		player.switchDirection(keycode);
		return true;
	}

	@Override
	public boolean keyTyped(char character) {return false;}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		player.shoot(Gdx.input.getX(), Gdx.input.getY());
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {return false;}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {return false;}

	@Override
	public boolean scrolled(int amount) {return false;}

}
