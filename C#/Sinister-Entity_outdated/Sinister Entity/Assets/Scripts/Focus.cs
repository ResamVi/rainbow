using UnityEngine;
using System.Collections;

public class Focus : MonoBehaviour
{
    // Target to focus
    private Transform target;

    // Follow target when true
    private bool isStatic;

    // Camera-to-Target offset
    private Vector3 OFFSET;

    void Awake() {
        target = GameObject.FindGameObjectWithTag("Player").transform;
        isStatic = false;
        OFFSET = transform.position - target.position;
    }
    void Update ()
    {
        // Toggle static Camera
        if (Input.GetButtonUp("Toggle Static Camera"))
            isStatic = !isStatic;

        // Center Camera on Target smoothly
        if (isStatic || Input.GetButton("Focus"))
            transform.position = Vector3.Lerp(transform.position, target.position + OFFSET, Time.deltaTime * 5f);
	}
}
