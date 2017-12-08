using UnityEngine;
using System.Collections;

public class Flashing : MonoBehaviour {

	//public float interval;
	public float changeRate;

	private float nextChange;

	void Update () {

		if (Time.time > nextChange)
		{
			nextChange = Time.time + changeRate;
			ChangeColor();
		}
	}

	void ChangeColor() {

		float red = Random.Range(0.0f, 1.0f);
		float green = Random.Range (0.0f, 1.0f);
		float blue = Random.Range (0.0f, 1.0f);

		//Debug.Log (red + " " + green + " " + blue);
		Color color = new Color(red, green, blue);
		GetComponent<Renderer> ().material.color = color;

	}
}
