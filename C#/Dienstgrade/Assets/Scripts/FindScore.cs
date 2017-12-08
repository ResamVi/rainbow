using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class FindScore : MonoBehaviour {
	
	void Start () {
		GetComponent<Text>().text = "In 33 Fragen waren " + (GameObject.Find ("H_ScoreController").GetComponent<ScoreController>().total-33).ToString() + " Fehler.";
	}
}
