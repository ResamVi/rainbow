package de.armadillo.game.animation;

import de.armadillo.game.engine.FileHandler;
import de.armadillo.game.screens.MenuScreen;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenCallback;

import java.util.Random;

public class SmileyLoop implements TweenCallback{

	private MenuScreen obj;
	private Random r;
	
	public SmileyLoop(MenuScreen obj) {
		this.obj = obj;
		r = new Random();
	}
	
	@Override
	public void onEvent(int arg0, BaseTween<?> arg1) {
		int direction = r.nextInt(4);
		float xStart, yStart, xEnd, yEnd;
		
		switch(direction) {
			
			// Left
			case 0:
				xStart = -100;
				yStart = r.nextInt(FileHandler.screenHeight);
				
				xEnd = FileHandler.screenWidth;
				yEnd = r.nextInt(FileHandler.screenHeight);
				break;
			
			// Right
			case 1:
				xStart = FileHandler.screenWidth;
				yStart = r.nextInt(FileHandler.screenHeight);
				
				xEnd = -100;
				yEnd = r.nextInt(FileHandler.screenHeight);
				break;
			
			// Top
			case 2:
				xStart = r.nextInt(FileHandler.screenWidth);
				yStart = FileHandler.screenHeight;
				
				xEnd = r.nextInt(FileHandler.screenWidth);
				yEnd = -100;
				
				break;
				
			// Bottom
			case 3:
				xStart = r.nextInt(FileHandler.screenWidth);
				yStart = -100;
						
				xEnd = r.nextInt(FileHandler.screenWidth);
				yEnd = FileHandler.screenHeight;
				break;
			
			default:
				xStart = yStart = xEnd = yEnd = 0;
		}	
		obj.loop(xStart, yStart, xEnd, yEnd);
	}
}