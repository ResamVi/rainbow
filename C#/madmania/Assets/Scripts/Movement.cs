using UnityEngine;
using System.Collections;

public class Movement : MonoBehaviour {

	[Range(0.0f, 1.0f)]
	public float speed;
	public float maxVelocity;

	Vector3 velocity;

	void Start() {
	
		velocity = new Vector3 (0, 0, 0);
	
	}

	void Update () {

		float h = Input.GetAxis ("Horizontal");
		float v = Input.GetAxis ("Vertical");

		velocity.x += h;
		velocity.y += v;

		velocity.x = Mathf.Clamp (velocity.x, -maxVelocity, maxVelocity);
		velocity.y = Mathf.Clamp (velocity.y, -maxVelocity, maxVelocity);

		transform.Translate (velocity * speed);

		if (Mathf.Abs (transform.position.x) > 10.0f || Mathf.Abs (transform.position.y) > 10.0f) {
		
			Application.LoadLevel(3);
		}

	}

	void OnCollisionEnter2D(Collision2D coll) {

		foreach (ContactPoint2D contact in coll.contacts) {
		
			velocity = Vector3.Reflect(velocity, contact.normal);
		
		}
	}
}
