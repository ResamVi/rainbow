using UnityEngine;
using System.Collections;

public class QuestionText {
	
	private Sprite[] list;
	private Sprite[] answers;
	private string question;

	public QuestionText(int index) {
		list = Resources.LoadAll <Sprite>("Sprites");
		question = "Welcher ist der " + QuestionDatabase.getAnswer (index);
		answers = GenerateQuestion (index);

	}

	private Sprite[] GenerateQuestion(int index) {

		return list;

	}

	public Sprite GetSprites() {
		return answers[0];
	}

	public string GetQuestions() {
		return question;	
	}

	public string GetAnswer() {
		return "Error";
	}
}
