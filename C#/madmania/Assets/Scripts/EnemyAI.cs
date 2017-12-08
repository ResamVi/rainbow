using UnityEngine;
using System.Collections;

public class EnemyAI : MonoBehaviour {

	[Range(0f,10f)]
	public float speed;
	private GameObject[] cookies;

	void Update () {
	
		GameObject cookie = FindClosestCookie ();

		Vector3 path = cookie.transform.position - transform.position;
		transform.Translate (path.normalized * speed);


	}

	GameObject FindClosestCookie() {

		cookies = GameObject.FindGameObjectsWithTag("Collectible");

		GameObject closest = null;
		float distance = Mathf.Infinity;
		Vector3 position = transform.position;

		foreach (GameObject go in cookies) {
			Vector3 diff = go.transform.position - position;
			float curDistance = diff.sqrMagnitude;
			if (curDistance < distance) {
				closest = go;
				distance = curDistance;
			}
		}
		return closest;
	}
}
