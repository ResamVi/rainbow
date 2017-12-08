package de.armadillo.game.engine;

import com.badlogic.gdx.Game;

import de.armadillo.game.screens.SplashScreen;

public class Root extends Game {
	
	@Override
	public void create () {
		FileHandler.load();
		setScreen(new SplashScreen(this));
	}
	
	@Override
	public void dispose() {
		FileHandler.dispose();
		super.dispose();
	}
}