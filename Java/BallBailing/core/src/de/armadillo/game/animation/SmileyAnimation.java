package de.armadillo.game.animation;

import aurelienribon.tweenengine.TweenAccessor;

public class SmileyAnimation implements TweenAccessor<Smiley>{

	public static final int POSITION_XY = 1;
	
	@Override
	public int getValues(Smiley target, int tweenType, float[] returnValues) {
		switch(tweenType) {
			case POSITION_XY:
				returnValues[0] = target.getX();
				returnValues[1] = target.getY();
				return 2;
			default:
				assert false;
				return -1;
		}
	}

	@Override
	public void setValues(Smiley target, int tweenType, float[] newValues) {
		switch(tweenType) {
			case POSITION_XY:
				target.setX(newValues[0]);
				target.setY(newValues[1]);
				break;
			default:
				assert false;
		}
	}
}