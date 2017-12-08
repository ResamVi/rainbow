using UnityEngine;
using System.Collections;

public class CameraManagement : MonoBehaviour {

	public Transform target;
	public float smoothing = 5f;

	Vector3 offset;

	void Start() {

		offset = transform.position - target.position;

	}

	void Update () {
		
		transform.position = Vector3.Lerp (transform.position, target.position + offset, Time.deltaTime * smoothing);


	}
}
