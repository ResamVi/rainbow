using UnityEngine;
using System.Collections;

public class SoundManagement : MonoBehaviour {

	public float fadeSpeed;

	AudioSource[] audioList;

	void Start () {
		audioList = GetComponentsInChildren<AudioSource> ();
	}

	void Update () {

		for (int i = 0; i < 5; i++) {
			if (GetComponentsInChildren<SoundCollision> () [i].trigger)
				ChangeTune(i);
		}
	}

	void ChangeTune(int index) {

		// Switching
		if (index != 0) {
			StartCoroutine ("FadeIn", index);
			StartCoroutine ("FadeOut", index - 1);
		} else {
			StartCoroutine ("FadeIn", index);
		}

		// Do not stack
		if (!audioList [index].isPlaying)
			audioList [index].Play ();
	}

	IEnumerator FadeIn(int index) {
		for (float f = 0f; f < 1.5f; f+=fadeSpeed) {
			audioList[index].volume = f;
			//Debug.Log ("(FADEIN)Index: " + index + ", Value: " + f);
			yield return null;
		}
	}

	IEnumerator FadeOut(int index) {
		for (float f = 1.5f; f > -0.5f; f-=fadeSpeed) {
			audioList[index].volume = f;
			//Debug.Log ("(FADEOUT)Index: " + index + ", Value: " + f);
			yield return null;
		}
		audioList [index].Stop ();
	}
}
