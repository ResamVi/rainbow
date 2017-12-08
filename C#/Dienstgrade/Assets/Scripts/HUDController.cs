using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class HUDController : MonoBehaviour {

	public InputField field;
	public SpriteRenderer[] sprites;
	public Text question;
	public SpriteRenderer text;

	public void switchToPicture() {
		field.gameObject.SetActive(true);

		for (int i = 0; i < sprites.Length; i++) {
			sprites[i].enabled = false;
		}

		text.enabled = true;
		question.gameObject.SetActive (false);

	}
	
	public void switchToText() {
		field.gameObject.SetActive(false);

		for (int i = 0; i < sprites.Length; i++) {
			sprites[i].enabled = true;
		}

		text.enabled = false;
		question.gameObject.SetActive (true);
	}

}
