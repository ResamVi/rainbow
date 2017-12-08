using UnityEngine;
using System.Collections;

public class ButtonListener : MonoBehaviour 
{
	public DifficultySetting difficulty;

	public void SwitchScene(int value)
	{
		if (value == 0)
		{
			difficulty.setToDifficult ();
			Object.DontDestroyOnLoad (difficulty);
			Application.LoadLevel (3);
		}
		else 
			Application.LoadLevel(value);
	}

	public void QuitGame()
	{
		Application.Quit ();
	}
}
