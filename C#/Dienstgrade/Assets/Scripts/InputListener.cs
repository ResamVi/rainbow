using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class InputListener : MonoBehaviour {

	public ProcessQuestion process;
	public InputField field;

	void Start() {
		field.onEndEdit.AddListener (SubmitInput);
	}

	private void SubmitInput(string input) {
		process.TestAnswer (input);
		field.text = "";
		field.Select ();
		field.ActivateInputField ();
	}
}
