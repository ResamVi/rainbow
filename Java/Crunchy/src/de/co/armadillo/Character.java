package de.co.armadillo;

import java.awt.Rectangle;
import java.util.Random;

public class Character {

	private int movespeed = 12, count = 0, interval = 1000;
	private int posX = 100, posY = 200, speedX = 0, speedY = 0;
	
	Rectangle rect1 = new Rectangle();
	Rectangle rect2 = new Rectangle();
	
	Random r = new Random();
	
	Tile cookies = new Tile(500,500);
	
	public void update() {
		
		// Check for horizontal maximum
		if(posX + speedX >= 1020-190) { // hard coded
			posX = 1020-190;
		}else{
			posX += speedX;
		}
		
		// Check for vertical maximum
		if(posY + speedY >= 765-138) { // hard coded
			posY = 765-138;
		}else{
			posY += speedY;
		}
		
		// Check if lower than 0 (x-wise)
		if( posX + speedX <= 0)
			posX = 0;
		
		if( posY + speedY <= 0)
			posY = 0;
		
		rect1.setRect((int)getX(), (int)getY(), 190.0, 138.0);
		rect2.setRect(cookies.getX(), cookies.getY(), 90, 90);
		checkCollision(rect1, rect2);
	}
	
	// Collision detection
	public void checkCollision(Rectangle r1, Rectangle r2) {
		if(r2.intersects(r1)) {
			interval -= (10);
			movespeed += 1;
			count++;
			cookies.setX(r.nextInt(1020-90));
			cookies.setY(r.nextInt(765-90));
		}
	}
	
	// Moving actions
	public void moveLeft() {
		speedX = -movespeed;
	}
	
	public void moveRight() {
		speedX = movespeed;
	}
	
	public void moveUp() {
		speedY = -movespeed;
	}
	
	public void moveDown() {
		speedY = movespeed;
	}
	
	// Getters
	public int getX() {
		return posX;
	}
	
	public int getY() {
		return posY;
	}
	
	public int getSpeedX() {
		return posY;
	}
	
	public int getSpeedY() {
		return posY;
	}
	
	public int getCount() {
		return count;
	}
	
	public int getInterval() {
		return interval;
	}
}
