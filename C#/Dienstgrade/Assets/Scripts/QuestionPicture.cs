using UnityEngine;
using System.Collections;

public class QuestionPicture {

	private Sprite[] list;
	private Sprite question;
	private string answer;

	public QuestionPicture(int index) {
		list = Resources.LoadAll <Sprite>("Sprites");
		question = list [index];
		answer = QuestionDatabase.getAnswer (index);
	}

	public Sprite GetSprite() {
		return question;
	}

	public string GetAnswer() {
		return answer;
	}
}
