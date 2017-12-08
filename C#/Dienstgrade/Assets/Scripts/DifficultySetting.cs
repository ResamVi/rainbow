using UnityEngine;
using System.Collections;

public class DifficultySetting : MonoBehaviour
{
	private bool easy = true;

	public void setToDifficult()
	{
		this.easy = false;
	}
}
