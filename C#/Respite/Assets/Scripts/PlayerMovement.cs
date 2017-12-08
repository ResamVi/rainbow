using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class PlayerMovement : MonoBehaviour {

	public float walkSpeed = 2f;
	public float crouchLength = 5f;
	public float maxHeight = 100f;
	public float slowdownTime = 0.5f;

	public Slider slider;

	float jumpHeight;
	float refVel;

	bool isGrounded;
	bool isCrouching;
	bool wakingUp;
	bool landing;
	bool facingLeft = false;
	public bool hasDoubleJump = true;

	Vector2 movement;

	Animator anim;

	void Start() {
		anim = GetComponent<Animator> ();
		slider.maxValue = maxHeight;
	}

	void Update () {

		// Lock inputs in the beginning animation
		wakingUp = anim.GetCurrentAnimatorStateInfo (0).IsName ("RiseUp");
		//landing = anim.GetCurrentAnimatorStateInfo (0).IsName ("Landing");

		if (!wakingUp)
			Act();

		Animate ();
		transform.Translate (movement);
	}

	void Act()  {
		Debug.Log (hasDoubleJump);
		Jump (GetComponent<Rigidbody2D> ().velocity.y);
		Walk (isCrouching);
		Crouch ();
	}

	// Movement on y-Axis
	void Jump(float yVelocity) {

		// Check if not already jumping
		if (Mathf.Abs (yVelocity) < 0.01f)
			isGrounded = true;

		// Check inputs
		if ((Input.GetKeyUp (KeyCode.Space) && isGrounded)) {
			GetComponent<Rigidbody2D> ().AddForce (new Vector2 (0f, jumpHeight), ForceMode2D.Impulse);
			anim.SetTrigger ("Jump");
			isGrounded = false;	
		} else if ((Input.GetKeyUp (KeyCode.Space) && hasDoubleJump)) {
			GetComponent<Rigidbody2D> ().velocity = new Vector3(0f,0f,0f);
			GetComponent<Rigidbody2D> ().AddForce (new Vector2 (0f, jumpHeight), ForceMode2D.Impulse);
			anim.SetTrigger ("Jump");
			isGrounded = false;	
			hasDoubleJump = false;
		} else if (isGrounded)
			hasDoubleJump = true;
	}

	// Movement on x-Axis
	void Walk(bool occupied) {
		if (!occupied)
			movement.x = Input.GetAxis ("Horizontal") * walkSpeed * Time.deltaTime;
	}

	// Charge up jump
	void Crouch() {

		// Check if spacebar is held
		if ((Input.GetKey (KeyCode.Space) && isGrounded) || (Input.GetKey (KeyCode.Space) && hasDoubleJump)) { 
			jumpHeight += crouchLength;
			movement.x = Mathf.SmoothDamp (movement.x, 0, ref refVel, slowdownTime);
			isCrouching = true;
		} else {
			jumpHeight -= crouchLength;
			isCrouching = false;
		}

		// Limit height
		jumpHeight = Mathf.Clamp (jumpHeight, 0f, maxHeight);

		// Apply
		slider.value = jumpHeight;
	}

	// Set Variables in AnimatorController
	void Animate() {

		// Crouching
		if (Input.GetKeyDown (KeyCode.Space))
			anim.SetTrigger("Crouch");

		// Jumping
		//if (Input.GetKeyUp (KeyCode.Space))
			//anim.SetTrigger("Jump");

		// Turning directions
		if (movement.x < 0 && !facingLeft)
			Flip ();
		else if (movement.x > 0 && facingLeft)
			Flip ();

		// Movement
		anim.SetFloat ("Speed", Mathf.Abs(movement.x));

		// Jumping
		anim.SetBool ("isGrounded", isGrounded);

		// On Ground
		anim.SetBool ("isGrounded", isGrounded);
	}

	// Flip sprite when walking the other direction
	void Flip() {
		facingLeft = !facingLeft;
		Vector3 s = transform.localScale;
		s.x *= -1;
		transform.localScale = s;
	}
}
