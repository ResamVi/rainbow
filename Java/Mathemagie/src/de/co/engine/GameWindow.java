package de.co.armadillo.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.co.armadillo.screens.PictureScreen;

public class GameWindow extends Game{

	public SpriteBatch batch;
	public ShapeRenderer shape;
	public OrthographicCamera cam;
	
	@Override
	public void create() {
		
		// Create camera
		cam = new OrthographicCamera();
		cam.setToOrtho(true);
		
		// Set batcher, necessary for drawing sprites
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		
		// Set shape renderer
		shape = new ShapeRenderer();
		shape.setProjectionMatrix(cam.combined);
		
		AssetLoader.load();
		setScreen(new PictureScreen(this));
		
	}
	
	@Override
	public void dispose() {
		AssetLoader.dispose();
	}
	
}
