package de.co.armadillo.entities;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import de.co.armadillo.engine.GameState;

public class Enemy {

	private Vector2 position;
	private Vector2 velocity;
	private Circle circle;
	private Equation equation;
	
	private boolean isHit = false;
	
	public Enemy(float x, float y, float v) {
		position = new Vector2(x, y);
		velocity = new Vector2(0, v);
		circle = new Circle(x, y, 40);
		
		switch(GameState.difficulty) {
			case 0: 
				equation = new EasyEquation();
				equation.getQuestion();
				break;
			case 1: 
				equation = new MediumEquation();
				equation.getQuestion();
				break;
			case 2:
				//equation = new HardEquation();
				equation.getQuestion();
				break;
		}
	}
	
	public void update(float delta) {
		
		// Move enemy
		position.add(velocity.cpy().scl(delta));
		circle.setPosition(position.x, position.y);
	}
	
	public void destroy() {
		velocity.y = 0;
		position.x = 900;
		position.y = 0;
	}
	
	public void gotHit() {
		isHit = true;
	}
	
	// Getters
	public boolean isHit() {
		return isHit;
	}
	
	public Circle getCircle() {
		return circle;
	}
	
	public Equation getEquation() {
		return equation;
	}
}
