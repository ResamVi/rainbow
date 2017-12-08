package de.co.armadillo;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main extends Applet implements Runnable, KeyListener {
	
	private static final long serialVersionUID = 1L;

	// Resolution
	final int WIDTH = 1020;
	final int HEIGHT = WIDTH / 4 * 3;
	
	// holds current time
	long time = System.currentTimeMillis();
	
	// Music
	AudioClip clip;
	
	// Objects
	Character character = new Character();
	Random r = new Random();
	
	// Images & Graphics
	Image image, player, cookie;
	Graphics second;
	
	@Override
	public void run() {
		
		// Game loop
		while(true) {
			
			update(getGraphics());
			character.update();
			
			repaint();
			
			// 30 FPS
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(System.currentTimeMillis() - time >= character.getInterval()) {
				setBackground(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
				time += character.getInterval();
			}
		}
	}
	
	// General settings
	@Override
	public void init() {
		
		// Window settings
		setSize(WIDTH,HEIGHT);
		setFocusable(true);
		setBackground(new Color(255,165,0));
		addKeyListener(this);
		
		// load graphics
		player = getImage(getCodeBase(), "de/co/resources/player.png");
		cookie = getImage(getCodeBase(), "de/co/resources/cookie.png");
		
		// load audio
		clip = getAudioClip(getCodeBase(), "de/co/resources/ChocolateRain.wav");
		clip.play();
		
		Frame frame = (Frame) getParent().getParent();
		frame.setTitle("Crunchy - developed by Julien");
	}
	
	// Double buffer
	public void update(Graphics g) {
		
		// Create graphic offscreen
		if(image == null) {
			image = createImage(getWidth(), getHeight());
			second = image.getGraphics();
		}
		
		// Clear area
		second.setColor(getBackground());
		second.fillRect(0,0,getWidth(),getHeight());
		second.setColor(getForeground());
		
		// redraw
		paint(second);
		
		// apply offscreen pic to window
		g.drawImage(image,0,0,this);
		
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(player,character.getX(),character.getY(), this);
		//g.drawRect(character.getX(), character.getY(), 190, 138);
		
		g.drawImage(cookie, (int)character.rect2.getX(), (int)character.rect2.getY(), this);
		//g.drawRect((int)character.rect2.getX(), (int)character.rect2.getY(), 90, 90);
		
		g.drawString("SCORE: " + character.getCount(), 20, 20);
	}
	
	// Start game loop
	@Override
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()) {
			
			case KeyEvent.VK_DOWN:
				character.moveDown();
				break;
			case KeyEvent.VK_UP:
				character.moveUp();
				break;
			case KeyEvent.VK_LEFT:
				character.moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				character.moveRight();
				break;
			case KeyEvent.VK_SPACE:
				System.out.println("SPEED");
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) { }
	
	@Override
	public void stop() { }
	
	@Override
	public void destroy() { }
	
	@Override
	public void keyTyped(KeyEvent arg0) { }
}