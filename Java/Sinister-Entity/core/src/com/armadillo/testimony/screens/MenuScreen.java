package com.armadillo.testimony.screens;

import com.armadillo.testimony.engine.AssetLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen implements Screen
{
	// Stage holds all buttons
	private Stage stage;
	private SpriteBatch batch;
	
	public MenuScreen(final Testimony change)
	{
		// Play Music
		AssetLoader.backgroundMusic.play(0.25f);
		
		batch = new SpriteBatch();
		
		// Create Stage
		stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        // Button Play
        TextButton playButton = new TextButton("Start", AssetLoader.skin);
        playButton.setPosition(Gdx.graphics.getWidth()/2+100, Gdx.graphics.getHeight()/2+40);
        stage.addActor(playButton);
        playButton.addListener(new ClickListener(){
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
        		
        		// Change cursor
        		Gdx.graphics.setCursor(Gdx.graphics.newCursor(AssetLoader.mousePointer, 0, 0));
        		AssetLoader.mousePointer.dispose();
        		
        		change.setScreen(new GameScreen(change));
        	}
        });
        
        // Button Credits
        TextButton creditsButton = new TextButton("Credits", AssetLoader.skin);
        creditsButton.setPosition(Gdx.graphics.getWidth()/2+130, Gdx.graphics.getHeight()/2-50);
        stage.addActor(creditsButton);
        creditsButton.addListener(new ClickListener(){
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
        		System.out.println("CREDITS");
        	}
        });
        
        // Button Quit
        TextButton quitButton = new TextButton("Quit", AssetLoader.skin);
        quitButton.setPosition(Gdx.graphics.getWidth()/2+100, Gdx.graphics.getHeight()/2-140);
        stage.addActor(quitButton);
        quitButton.addListener(new ClickListener(){
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
        		Gdx.app.exit();
        	}
        });
	}
	
	@Override
	public void render(float delta)
	{
		// Clear screen
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
 
        // Draw title
        batch.begin();
        
        batch.draw(AssetLoader.background, -400, 0);
        AssetLoader.titleFont.draw(batch, "Sinister Entitiy", 50, 450);
        
        batch.end();
        
        // Draw Stage
        stage.act();
        stage.draw();
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
