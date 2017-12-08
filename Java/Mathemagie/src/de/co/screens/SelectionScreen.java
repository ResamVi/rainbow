package de.co.armadillo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.co.armadillo.engine.AssetLoader;

public abstract class SelectionScreen implements Screen{

	protected SpriteBatch batch;
	protected OrthographicCamera cam;
	protected Stage stage;
	
	public SelectionScreen() {
		
		// Camera
		cam = new OrthographicCamera();
		cam.setToOrtho(true);
		
		// Responsible for drawing
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		
		// Make stage
		stage = new Stage();
		
	}
	
	@Override
	public void render(float delta) {
		
		// Clear screen
		Gdx.gl.glClearColor(0/255.0f, 0/255.0f, 0/255.0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// Add stage
		stage.act(delta);
		stage.draw();
		
		// Set Listener
		Gdx.input.setInputProcessor(stage);
		
		// Draw background
		batch.begin();
		batch.draw(AssetLoader.title, 136, 100, 0, 0, 299, 57, 1.5f, 1.5f, 0);
		batch.draw(AssetLoader.pi, 100, 200, 0, 0, 168, 160, 0.25f, 0.25f, -10);
		batch.draw(AssetLoader.binomial, 400, 570, 0, 0, 83, 20, 1, 1, 10);
		batch.draw(AssetLoader.complex, 500, 20, 0, 0, 35, 18, 1, 1, -5);
		batch.draw(AssetLoader.squareroot, 100, 700, 0, 0, 120, 94, 1, 1, 5);
		
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
