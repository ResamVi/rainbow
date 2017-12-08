package de.co.armadillo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

import de.co.armadillo.engine.AssetLoader;
import de.co.armadillo.engine.GameWindow;

public class NameScreen extends SelectionScreen implements Screen {

	private GameWindow window;
	
	private float fade;
	private float runTime;
	private float lastTime;
	
	public NameScreen(GameWindow window) {

		// Instance of window
		this.window = window;
		
		// Variable for fade in/ and out
		fade = 0;
		
		// Timekeeping variable
		runTime = 0;
		lastTime = 0;
	}
	
	@Override
	public void render(float delta) {
		
		// Keep time
		runTime += delta;
		
		// Fade in, as long as its not fully faded, and stop when fade out occures (at 4secs)
		if(runTime <= 2 && fade <= 0.95 && runTime - lastTime >= 0.015) {
			fade+= 0.01;
			lastTime = runTime;
		}
		
		// Fade out
		if(runTime >= 2 && fade >= 0.0001 && runTime - lastTime >= 0.015) {
			fade-= 0.01;
			lastTime = runTime;
		}
		
		// Change screen
		if(runTime >= 5) {

			// Play music
			AssetLoader.menuMusic.loop();
			window.setScreen(new MenuScreen(window));
		}
			
		// Clear screen
		Gdx.gl.glClearColor(0/255.0f, 0/255.0f, 0/255.0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// Draw picture
		window.batch.begin();
		window.batch.setColor(1f, 1f, 1f, fade);
		window.batch.draw(AssetLoader.credits, 57, 840/2-57);
		window.batch.end();
		
	}
}
