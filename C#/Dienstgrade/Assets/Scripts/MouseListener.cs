using UnityEngine;
using System.Collections;

public class MouseListener : MonoBehaviour {

	void OnMouseDown() {

		string name = GetComponent<SpriteRenderer> ().sprite.name;
		if(name.Length == 6)
			Debug.Log (int.Parse(name.ToCharArray()[5] + ""));
		else
			Debug.Log (int.Parse (name.ToCharArray()[5] + name.ToCharArray()[6] + ""));
	}
}
