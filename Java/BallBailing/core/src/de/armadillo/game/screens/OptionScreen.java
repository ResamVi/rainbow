package de.armadillo.game.screens;

import com.badlogic.gdx.Screen;

import de.armadillo.game.engine.Root;

public class OptionScreen implements Screen {

	private Root root;
	
	public OptionScreen(Root root) {
		this.root = root;
	}
	
	@Override
	public void render(float delta) {
		root.setScreen(new MenuScreen(root));
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