package de.co.armadillo.entities;

public class MediumEquation extends Equation{

	public MediumEquation() {
		super();
	}
	
	@Override
	public String setQuestion() {
		
		switch(r.nextInt(3)) {
	
			case 0:
				
				// Standard
				EasyEquation easy = new EasyEquation();
				String question = easy.setQuestion();
				answer = easy.getAnswer();
				return question;
			
			case 1:
				
				// Center text
				center = true;
				
				// Powers
				a = 1 + r.nextInt(20);
				if( a <= 5 && r.nextBoolean()) {
					b = 3;
					answer = Math.pow(a, b);
					return (int)a + "³";
				}else{
					b = 2;
					answer = Math.pow(a, b);
					return (int)a + "²";
				}
			
			case 2:

				// Center text
				center = true;
				
				// Squareroots
				do {
					a = r.nextInt(100);
				}while(Math.sqrt(a) % 2 != 0);
				
				answer = Math.sqrt(a);
				return '\u221A' + String.valueOf((int)a);
			
			default:
				return "error";
		}
	}

}
