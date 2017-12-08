using UnityEngine;
using System.Collections;

public class Cloud : MonoBehaviour {
	

	void OnTriggerEnter2D(Collider2D other) {
		if (other.gameObject.tag == "Player")		
			other.gameObject.GetComponent<PlayerMovement>().hasDoubleJump = true;
	}

	void OnTriggerExit2D(Collider2D other) {
		if(other.tag == "Player")
			other.gameObject.GetComponent<PlayerMovement>().hasDoubleJump = false;
	}
}
