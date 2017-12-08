using UnityEngine;
using System.Collections;

public class ActivateOnEnable : MonoBehaviour {

	public EasyTween EasyTweenStart;

	void Start () 
	{
		EasyTweenStart.OpenCloseObjectAnimation();
	}
}
