package com.armadillo.testimony.screens;

import com.armadillo.testimony.engine.AssetLoader;
import com.armadillo.testimony.engine.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen
{
	private SpriteBatch batch;
	private float alpha;
	
	// So that fadeOut after fadeIn can be triggered
	private boolean pause;
	
	// Time measuring variables
	private long startTime;
	private long elapsedTime;
	
	// Ability to change screen
	private Testimony change;
	
	SplashScreen(Testimony change)
	{
		batch = new SpriteBatch();
		alpha = 0;
		pause = true;
		startTime = System.nanoTime();
		this.change = change;
	}
	
	@Override
	public void render(float delta)
	{
		// Clear screen
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Draw splash
		batch.begin();	
		batch.setColor(1f, 1f, 1f, alpha);
		batch.draw(AssetLoader.splash, 0, 0);
		batch.end();
		
		// Fade In
		if(pause && alpha < 0.99f) fadeIn();
		
		// Pause
		else if(pause && alpha > 0.99f) fadePause();
		
		// Fade Out
		else if(!pause && alpha > 0.01f) fadeOut();
		
		// Pause & Change screen afterwards
		else {
			elapsedTime = System.nanoTime() - startTime;
			if(elapsedTime / 1000000000 > Constants.SPLASH_END_PAUSE) 
				change.setScreen(new MenuScreen(change));
		}
	}
	
	private void fadeIn()
	{
		alpha+=0.02f;
		startTime = System.nanoTime();
	}
	
	private void fadePause()
	{
		elapsedTime = System.nanoTime() - startTime;
		if(elapsedTime / 1000000000 > Constants.SPLASH_PAUSE) pause = false;
	}

	private void fadeOut()
	{
		alpha-=0.013f;
		startTime = System.nanoTime();
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
