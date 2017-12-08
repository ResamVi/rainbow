package com.armadillo.testimony.screens;

import com.armadillo.testimony.engine.AssetLoader;
import com.badlogic.gdx.Game;

// Manager of every Screen
public class Testimony extends Game
{	
	@Override
	public void create ()
	{		
		// Load all resources
		AssetLoader.load();
		
		// Set first Screen
		setScreen(new GameScreen(this));
	}
	
	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}