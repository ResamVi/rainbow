package de.armadillo.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import de.armadillo.game.engine.GameRenderer;
import de.armadillo.game.engine.GameState;
import de.armadillo.game.engine.GameWorld;
import de.armadillo.game.engine.Root;
import de.armadillo.game.inputs.InputHandler;

public class GameScreen implements Screen {

	private Root root;
	private GameWorld world;
	private GameRenderer renderer;
	
	public GameScreen(Root root) {
		this.root = root;
		
		world = new GameWorld();
		renderer = new GameRenderer(world);
		
		Gdx.input.setInputProcessor(new InputHandler(root, world));
	}
	
	@Override
	public void render(float delta) {
		world.update(delta);
		renderer.render();
		if(GameState.checkState())
			root.setScreen(new EndScreen(root));
		System.out.println(GameState.level);
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

	@Override
	public void dispose() {}
}