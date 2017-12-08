package de.armadillo.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.armadillo.game.engine.FileHandler;
import de.armadillo.game.engine.Root;

public class SplashScreen implements Screen{

	private Root root;
	private float totalTime;
	private float opacity;
	private SpriteBatch batch;
	
	public SplashScreen(Root root) {
		this.root = root;
		totalTime = 0;
		opacity = 0;
		batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		totalTime += delta;
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Fade in and then fade out
		if(totalTime < 3 && opacity < 0.99)
			opacity += 0.02;
		else if(totalTime > 3 && opacity > 0.01)
			opacity -= 0.02;
		else if(opacity < 0.01)
			root.setScreen(new MenuScreen(root));
		
		batch.begin();
		
		batch.setColor(1, 1, 1, opacity);
		batch.draw(FileHandler.splash, FileHandler.screenWidth/2-FileHandler.splash.getWidth()/2, FileHandler.screenHeight/2-FileHandler.splash.getHeight()/2);
		
		batch.end();
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