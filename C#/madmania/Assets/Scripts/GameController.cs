using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using UnityEngine.UI;

public class GameController : MonoBehaviour {

	private AudioClip clip1;

	public Text statusText;

	private int level;
	private int cookieAmount;
	private int cookieTotal;
	private int LIMIT = 5;
	private int newLevel = 5;

	private Movement movement;
	private Flashing flashing;
	private CameraShake shaking;
	private Rotation rotation;

	private string[] phrases = { "NOT BAD", "OKAY GOOD", "HOLY SHIT", "OH SWEET JESUS", "THIS IS MADNESS", "MOM GET THE CAMERA", "OHHHHH, OHHHHH OHHHHHH", "I CANT TAKE IT ANYMORE", "I WANT ALL YOUR BABIES", "I THINK I'LL ORGASM", "WHEN WILL YOU STOP FOR FUCKS SAKE", "ARE YOU INSANE FOR CHRIST SAKE"};

	void Start() {

		movement = GameObject.FindGameObjectWithTag("Player").GetComponent<Movement> ();
		flashing = GameObject.FindGameObjectWithTag("Flashing").GetComponent<Flashing> ();
		shaking = GameObject.FindGameObjectWithTag("MainCamera").GetComponent<CameraShake> ();
		rotation = GameObject.FindGameObjectWithTag("Rotation").GetComponent<Rotation> ();

		statusText.GetComponent<Text> ().text = "";

		level = 0;
		cookieTotal = 0;
		cookieAmount = 0;
		clip1 = GetComponents<AudioSource> ()[0].clip;

	}
	
	public void PlaySound() {

		GetComponent<AudioSource> ().PlayOneShot (clip1);

	}

	public void changeAmount(int amount) {

		cookieAmount+=amount;
	
	}

	public bool reachedLimit() {

		if (cookieAmount >= LIMIT) {
			return false;
		} else {
			return true;
		}

	}

	public void incrementLevel() {

		cookieTotal++;
		GetComponents<AudioSource> ()[0].pitch += 0.05f;
		GetComponents<AudioSource> ()[1].pitch += 0.005f;
		flashing.changeRate -= 0.1f;
		movement.speed += 0.002f;
		shaking.shakeAmount += 0.02f;
		rotation.speed -= 5;

		if (cookieTotal % newLevel == 0) {
		
			ShowText ();
			level++;

			if(level > 3) {
				newLevel+=3;
			}

		} 
	}

	private void ShowText() {

		statusText.GetComponent<Text> ().text = phrases [level];

	}
}

