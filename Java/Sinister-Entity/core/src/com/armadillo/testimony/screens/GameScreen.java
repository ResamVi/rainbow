package com.armadillo.testimony.screens;

import com.armadillo.testimony.engine.GameRenderer;
import com.armadillo.testimony.engine.GameWorld;
import com.armadillo.testimony.engine.PlayerInput;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

// Main Game
public class GameScreen implements Screen 
{	
	// Responsible for updates to GameObjects
	private GameWorld world;
	
	// Responsible for rendering GameObjects
	private GameRenderer renderer;
	
	// Responsible for handling inputs
	private PlayerInput input;
	
	// Initializing fields
	GameScreen(Testimony change)
	{
		world = new GameWorld();
		renderer = new GameRenderer(world);
		input = new PlayerInput(world.getPlayer());
		Gdx.input.setInputProcessor(input);
	}
	
	// Game Loop
	@Override
	public void render(float delta) 
	{
		world.update();
		renderer.render();
	}
	
	// Avoid memory leaks
	@Override
	public void dispose()
	{
		renderer.dispose();
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
}
