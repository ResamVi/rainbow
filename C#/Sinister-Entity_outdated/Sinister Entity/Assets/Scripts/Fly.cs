using UnityEngine;

// Requires RigidBody2D for movement
[RequireComponent(typeof(Rigidbody2D))]

public class Fly : MonoBehaviour
{
    // Velocity of projectile
    public float speed;

    // Mouse click position
    private Vector3 direction;

    void Start ()
    {
        // Get Direction to Mouse click
        direction = (Camera.main.ScreenToWorldPoint(Input.mousePosition) - GetComponent<Transform>().position);

        // Add Force
        GetComponent<Rigidbody2D>().AddForce(direction * speed);
    }

    void LateUpdate()
    {
        // Keep Velocity constant
        GetComponent<Rigidbody2D>().velocity = speed * GetComponent<Rigidbody2D>().velocity.normalized;
    }
}