using UnityEngine;
using System.Collections;

public class Flash : MonoBehaviour {
	
	public float fadePause = 1f;
	public float fadeSpeed = 0.05f;
	
	private bool done = false;
	
	public SpriteRenderer render;
	
	void Start() {
		StartCoroutine ("Fade");
	}
	
	void FixedUpdate () {
		
		if (done)
			Destroy (this.gameObject);
		
	}
	
	IEnumerator Fade() {
		
		for (float f = 0f; f <= 1.5f; f+=fadeSpeed) {
			
			Color c = render.color;
			c.a = f;
			render.color = c;
			yield return null;
			
		}
		
		yield return new WaitForSeconds (fadePause);
		
		for (float f = 1f; f >= -0.5f; f-=fadeSpeed) {
			
			Color c = render.color;
			c.a = f;
			render.color = c;
			yield return null;
			
		}
		
		yield return new WaitForSeconds (fadePause);
		
		done = true;
	}
}
