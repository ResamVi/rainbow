using UnityEngine;
using System.Collections;

public class MenuSound : MonoBehaviour {
	
	void Start () {
		GetComponent<AudioSource> ().PlayDelayed (9.3f);
	}
}
