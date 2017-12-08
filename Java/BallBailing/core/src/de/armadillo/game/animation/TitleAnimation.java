package de.armadillo.game.animation;

import aurelienribon.tweenengine.TweenAccessor;

public class TitleAnimation implements TweenAccessor<Title>{

	public static final int SCALE = 1;
	public static final int POSITION_XY = 2;
	
	@Override
	public int getValues(Title target, int tweenType, float[] returnValues) {
		switch(tweenType) {
			case SCALE:
				returnValues[0] = target.getScale();
				return 1;
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
	public void setValues(Title target, int tweenType, float[] newValues) {
		switch(tweenType) {
			case SCALE:
				target.setScale(newValues[0]);
				break;
			case POSITION_XY:
				target.setX(newValues[0]);
				target.setY(newValues[1]);
				break;
			default:
				assert false;
		}
	}
}