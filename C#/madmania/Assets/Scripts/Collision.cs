using UnityEngine;
using System.Collections;

public class Collision : MonoBehaviour {

	public float xBorder;
	public float yBorder;
	private GameController controller;

	void Start() {

		controller = GameObject.FindWithTag ("GameController").GetComponent<GameController> ();
	
	}

	void OnTriggerEnter2D(Collider2D other) {

		if (controller.reachedLimit ()) {

			controller.changeAmount (1);

			float x = Random.Range (-xBorder, xBorder);
			float y = Random.Range (-yBorder, yBorder);

			Instantiate (gameObject, new Vector3 (Random.Range (-xBorder, xBorder), Random.Range (-yBorder, yBorder)), Quaternion.identity);
			Instantiate (gameObject, new Vector3 (x, y), Quaternion.identity);

		} else {

			controller.changeAmount(-1);
			
		}

		if (other.tag == "Player") {

			controller.PlaySound ();
			controller.incrementLevel();
		
		}

		Destroy (gameObject);

	}
}
