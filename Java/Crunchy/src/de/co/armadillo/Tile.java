package de.co.armadillo;

import java.awt.Rectangle;
import java.util.Random;

public class Tile {

	private int xPos, yPos;
	
	Random r = new Random();
	
	public Tile(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public void setX(int x) {
		xPos = x;
	}
	
	public void setY(int y) {
		yPos = y;
	}
}
