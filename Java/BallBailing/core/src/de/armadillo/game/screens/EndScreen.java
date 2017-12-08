package de.armadillo.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.armadillo.game.engine.FileHandler;
import de.armadillo.game.engine.GameState;
import de.armadillo.game.engine.Root;

public class EndScreen implements Screen {

	Root root;
	SpriteBatch batch;
	
	public EndScreen(Root root) {
		this.root = root;
		batch = new SpriteBatch();
		
		if(GameState.won)
			FileHandler.success.play();
		else
			FileHandler.failure.play();
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0/255f, 67/255f, 55/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		if(GameState.won)
			FileHandler.titleFont.draw(batch, "SUCCESS!", FileHandler.screenWidth/2-120, FileHandler.screenHeight/2+150);
		else
			FileHandler.titleFont.draw(batch, "FAILURE.", FileHandler.screenWidth/2-120, FileHandler.screenHeight/2+150);
		
		FileHandler.titleFont.draw(batch, "Press Space to continue" , 50, FileHandler.screenHeight/2);
		
		batch.end();
		
		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			root.setScreen(new GameScreen(root));
		}
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