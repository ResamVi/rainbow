using UnityEngine;

// Requires RigidBody2D for movement
[RequireComponent(typeof(Rigidbody2D))]

public class Movement : MonoBehaviour
{
    // Constants
    public float MAX_SPEED;

	void FixedUpdate ()
    {
        // Get Input
        float horizontal = Input.GetAxisRaw("Move Horizontal");
        float vertical = Input.GetAxisRaw("Move Vertical");
        //Debug.Log("HORIZONTAL: " + horizontal + "\nVERTICAL: " + vertical);

        // Apply Force
        GetComponent<Rigidbody2D>().AddForce(new Vector2(horizontal * MAX_SPEED, vertical * MAX_SPEED));
    }
}
