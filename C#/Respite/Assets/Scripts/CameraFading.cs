using UnityEngine;
using System.Collections;

public class CameraFading : MonoBehaviour {

	public float speed = 0.001f;
	float time = 0;

	void Update () {
	
		if (time < 1) {

			time += Time.deltaTime * speed;
			speed -= 0.00005f;
			float x = Mathf.Lerp (5, 0, time);
			float y = Mathf.Lerp (10, 0, time);
			Debug.Log (x + ", " + y);
			transform.position = new Vector3 (x, y, time);
		}
	}
}
