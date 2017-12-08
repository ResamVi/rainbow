using UnityEngine;
using System.Collections;

public class Restart : MonoBehaviour {

	void FixedUpdate () {
	
		if (Input.GetKeyDown (KeyCode.F)) {
			Application.LoadLevel(2);
			
		}

	}
}
