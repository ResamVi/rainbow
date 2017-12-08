using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class ScoreController : MonoBehaviour {

	private int score;
	public int total;
	private bool gameOver;
	public Text text;

	void Start () {
		score = 0;
		total = 0;
		gameOver = false;
	}

	void Update () {
		if(!gameOver)
			text.text = score + " von " + total + " richtig.";
	}

	public void UpdateScore(bool correct) {
		if (correct)
			score++;

		if (total == 33) {
			gameOver = true;
			Object.DontDestroyOnLoad(gameObject);
			Application.LoadLevel (4);
		}

		total++;
	}
}
