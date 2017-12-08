using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using UITween;
using UnityEngine.UI;

public class CreateAnimImage : MonoBehaviour , ICallbackAfterTween{

	public CreateAnimImage[] createImageOtherReference;

	public GameObject CreateInstance;

	public int HowManyButtons;

	public Vector3 StartAnim;
	public Vector3 EndAnim;

	public float Offset;

	public AnimationCurve EnterAnim;
	public AnimationCurve ExitAnim;

	public RectTransform RootRect;
	public RectTransform RootCanvas;

	private float yInstance = 0f;

	private Vector3 startPosStored;
	private Vector3 endPosStored;

	private Rect storeInitialRect;

	private List<EasyTween> Created = new List<EasyTween>();

	void Start()
	{
		startPosStored = StartAnim;
		endPosStored = EndAnim;

		storeInitialRect = RootRect.rect;
	}

	public void CallBack()
	{
		if (Created.Count == 0)
		{
			for (int i = 0; i < createImageOtherReference.Length; i++)
			{
				createImageOtherReference[i].DestroyButtons();
			}

			CreateButtons();
		}
	}

	public void DestroyButtons()
	{
		for (int i = 0; i < Created.Count; i++)
		{
			Created[i].OpenCloseObjectAnimation();
		}

		Created.Clear();
	}

	public void CreateButtons()
	{
		yInstance = EndAnim.y;

		CreatePanels();
		AdaptCanvas();

		StartAnim = startPosStored;
		EndAnim = endPosStored;
	}

	void CreatePanels()
	{
		for (int i = 0; i < HowManyButtons; i++)
		{
			GameObject createInstance = Instantiate(CreateInstance) as GameObject;
			createInstance.transform.SetParent(RootRect);
			
			EasyTween easy = createInstance.GetComponent<EasyTween>();
			Created.Add(easy);
			
			easy.rectTransform = createInstance.GetComponent<RectTransform>();
			easy.rectTransform.localScale *= RootCanvas.localScale.x;
			
			easy.rectTransform.anchorMin = new Vector2(0f, 1f);
			easy.rectTransform.anchorMax = new Vector2(1f, 1f);
			easy.rectTransform.offsetMax = new Vector2(0f, createInstance.GetComponent<RectTransform>().offsetMax.y);
			easy.rectTransform.offsetMin = new Vector2(0f, createInstance.GetComponent<RectTransform>().offsetMin.y);
			
			easy.rectTransform.anchoredPosition = new Vector3(StartAnim.x, yInstance, 0f);
			EndAnim.y = StartAnim.y = yInstance;
			
			easy.SetAnimationPosition(StartAnim, EndAnim , EnterAnim, ExitAnim);
			easy.SetFade();

			easy.OpenCloseObjectAnimation();
			
			yInstance += Offset;
		}
	}

	void AdaptCanvas()
	{
		Rect canvasRect = storeInitialRect;

		float valueHeight = Mathf.Abs( Offset ) * HowManyButtons;

		if ( valueHeight > canvasRect.height)
		{
			canvasRect.height = valueHeight;
		}

		SetSize(RootRect, new Vector2(canvasRect.width, canvasRect.height), storeInitialRect);
	}

	void SetSize(RectTransform trans, Vector2 newSize, Rect initialSize) 
	{
		Vector2 oldSize = initialSize.size;
		Vector2 deltaSize = newSize - oldSize;
		trans.offsetMin = - 2 * new Vector2(deltaSize.x * trans.pivot.x, deltaSize.y * trans.pivot.y);
		trans.offsetMax = Vector2.zero;
	}
}