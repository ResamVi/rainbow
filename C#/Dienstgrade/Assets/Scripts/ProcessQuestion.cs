using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class ProcessQuestion : MonoBehaviour {

	public SpriteRenderer render;
	public ScoreController score;
	public HUDController hud;

	public Object correct;
	public Object wrong;

	private QuestionPicture currentQuestion;
	private QuestionText currentTQuestion;
	private int[] questionList;
	private int index;
	private bool difficult;
	private bool isPicture;

	void Start () {
		index = 0;
		questionList = shuffle ();

		if (GameObject.Find ("H_Difficulty") != null)
			difficult = true;
		else
			difficult = false;

		createQuestion();
		shuffle ();
	}

	void Update () {
		if(isPicture) render.sprite = currentQuestion.GetSprite ();
	}

	public void TestAnswer(string input) {
		if (input.ToLower().Replace(" ", "" ) == currentQuestion.GetAnswer()) {
			score.UpdateScore (true);
			Instantiate (correct, new Vector2 (0, 0), Quaternion.identity);
			createQuestion();
		}else {
			score.UpdateScore(false);
			Instantiate (wrong, new Vector2(0,0), Quaternion.identity);
		}
		
	}

	// Yup. Im sorry
	public void TestAnswer2(int index) {
	
	}

	private int[] shuffle() {
		int[] array = new int[33];

		for(int i = 0; i < array.Length; i++) {
			array[i] = i;
		}

		int m = array.Length;

		while(m > 0) {
			int i = Random.Range(0, m--);
			int t = array[m];
			array[m] = array[i];
			array[i] = t;
		}

		return array;
	}

	private void createQuestion() {
		if (difficult) { // !
			isPicture = true;
			currentQuestion = new QuestionPicture (questionList [++index]);

		}else {
			if(Mathf.Round(Random.value) == 0) {
				isPicture = true;
				hud.switchToPicture();
				currentQuestion = new QuestionPicture (questionList [++index]);

			}else{
				isPicture = false;
				hud.switchToText();
				currentTQuestion = new QuestionText(questionList[++index]);
			}
		}
	}	
}
