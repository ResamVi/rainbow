using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class GameManagement : MonoBehaviour {

	public Image fadeImage;
	public float fadeSpeed;

	void Start() {
		StartCoroutine ("FadeIn");
	}


	IEnumerator FadeIn() {
		fadeImage.enabled = true;
		for (float f = 1f; f > -.5f; f-=fadeSpeed) {
			Color c = fadeImage.color; 
			c.a = f;
			fadeImage.color = c;
			GetComponentsInChildren<AudioSource> ()[0].volume = 1f-f;
			yield return null;
		}
	}
}
