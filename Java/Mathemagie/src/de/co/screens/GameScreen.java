package de.co.armadillo.screens;

import com.badlogic.gdx.Screen;

import de.co.armadillo.engine.*;

public class GameScreen implements Screen{

	private GameWorld world;
	private GameRenderer renderer;
	private GameState state;
	private GameWindow window;
	private long id;
	
	public GameScreen(GameWindow window) {
		
		// Get object to change screen if necessary
		this.window = window;
		
		// Initialize fundament
		world = new GameWorld(state);
		renderer = new GameRenderer(world);
		
		// Play music
		if(!GameState.musicMute) id = AssetLoader.menuMusic.loop();
	}
	
	// The main loop of the game - its heart, can you hear it beating?
	@Override
	public void render(float delta) {
		world.update(delta);
		renderer.render(delta);
		
		// Reset window and show lost screen
		if(GameState.hitpoints == 0) {
			world.resetLevel();
			GameState.hitpoints = 3;
			
			GameState.lastScore = GameState.score;
			GameState.score = 0;
			
			GameState.lastStage = GameState.stage;
			GameState.stage = 1;
			window.setScreen(new LostScreen(window, world, id));
		}
	}

	// Window methods
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
