package com.armadillo.testimony.desktop;

import com.armadillo.testimony.engine.Constants;
import com.armadillo.testimony.screens.Testimony;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

// Desktop Application
public class DesktopLauncher
{
	public static void main (String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.WINDOWS_WIDTH;
		config.height = Constants.WINDOWS_HEIGHT;
		config.title = Constants.TITLE;
		config.addIcon("menu/icon.png", FileType.Internal);
		config.resizable = false;
		new LwjglApplication(new Testimony(), config);
	}
}
