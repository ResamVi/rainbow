using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class Splash : MonoBehaviour {

	public Image fadeImage;
	public float fadePause = 1f;
	public float fadeSpeed = 0.05f;
	
	public SpriteRenderer render;

	void Start() {
		StartCoroutine ("Fade");
	}
	
	IEnumerator Fade() {
	
		for (float f = 0f; f <= 1.5f; f+=fadeSpeed) {
			Color c = render.color;
			c.a = f;
			render.color = c;
			yield return null;
		}

		yield return new WaitForSeconds(fadePause);
		
		fadeImage.enabled = true;
		for (float f = 0f; f < 1.5f; f+=fadeSpeed/5f) {
			Color c = fadeImage.color; 
			c.a = f;
			fadeImage.color = c;
			yield return null;
		}
		
		yield return new WaitForSeconds(fadePause);
		
		Application.LoadLevel(0);
	}
	
}
