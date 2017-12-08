using UnityEngine;
using System.Collections;

public class Splash : MonoBehaviour {

	public float fadePause = 1f;
	public float fadeSpeed = 0.05f;
    public int LEVEL_INDEX;

	public SpriteRenderer render;

	void Start()
    {
		StartCoroutine ("Fade");
	}

	IEnumerator Fade()
    {
        // Fade In
        for (float f = 0f; f <= 1.5f; f+=fadeSpeed)
        {
			Color c = render.color;
			c.a = f;
			render.color = c;
			yield return null;
		}

        // Pause
        yield return new WaitForSeconds (fadePause);

        // Fade Out
        for (float f = 1f; f >= -0.5f; f-=fadeSpeed)
        {
			Color c = render.color;
			c.a = f;
			render.color = c;
			yield return null;
			
		}

        // Pause
		yield return new WaitForSeconds (fadePause);

        // Load Level
        Application.LoadLevel(LEVEL_INDEX);
    }
}
