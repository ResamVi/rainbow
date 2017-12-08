package com.armadillo.testimony.client;

import com.armadillo.testimony.engine.Constants;
import com.armadillo.testimony.screens.Testimony;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

// Website Application
public class HtmlLauncher extends GwtApplication 
{
    @Override
    public GwtApplicationConfiguration getConfig ()
    {
    	return new GwtApplicationConfiguration(Constants.WINDOWS_WIDTH, Constants.WINDOWS_HEIGHT);
    }

    @Override
    public ApplicationListener getApplicationListener ()
    {
    	return new Testimony();
    }
}