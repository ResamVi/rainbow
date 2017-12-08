using UnityEngine;
using System.Collections;

public class SoundCollision : MonoBehaviour {

	public bool trigger = false;

	void OnTriggerEnter2D(Collider2D other) {
		if (other.gameObject.tag == "Player")
			trigger = true;
		else
			trigger = false;
	}
}
