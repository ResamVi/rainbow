package de.armadillo.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.armadillo.game.engine.Root;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 960;
		config.height = 680;
		config.resizable = false;
		config.title = "Be The Ball & Bail The Enemy - (c) ResamVi";
		new LwjglApplication(new Root(), config);
	}
}
