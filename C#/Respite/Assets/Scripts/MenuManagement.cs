using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class MenuManagement : MonoBehaviour {

	public Image fadeImage;
	public float fadeSpeed;
	public float fadePause;

	public void LoadGame() {
		StartCoroutine("FadeOut");
	}

	public void Exit() {
		Application.Quit ();
	}

	IEnumerator FadeOut() {
		fadeImage.enabled = true;
		for (float f = 0f; f < 1.5f; f+=fadeSpeed) {
			Color c = fadeImage.color; 
			c.a = f;
			fadeImage.color = c;
			GetComponent<AudioSource> ().volume = 1.5f-f;
			yield return null;
		}

		yield return new WaitForSeconds(fadePause);

		Application.LoadLevel (1);
	}
}
