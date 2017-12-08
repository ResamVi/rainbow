package de.co.armadillo.entities;

import java.util.Random;

public abstract class Equation {

	public String question;
	public double answer;
	public Random r;
	public boolean center;
	public int a, b;
	
	public Equation() {
		
		center = false;
		r = new Random();
		question = setQuestion();
	}
	
	abstract public String setQuestion();
	
	public String getQuestion() {
		return question;
	}
	
	public double getAnswer() {
		return answer;
	}
	
	public boolean checkAnswer(Integer integer) {
		if(integer == this.answer)
			return true;
		else
			return false;
	}
	
	public boolean getCenter() {
		return center;
	}
}
