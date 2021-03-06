﻿using UnityEngine;
using System.Collections;
using UnityEngine.UI;

namespace UITween
{
    public class CurrentAnimation
    {
        private AnimationParts animationPart;

        private float counterTween = 2f;
        private bool ended = true;

        public CurrentAnimation(AnimationParts animationPart)
        {
            this.animationPart = animationPart;
        }

        public void AnimationFrame(RectTransform rectTransform)
        {
            if (counterTween <= 1f) // Max value is 1f
            {
                // Position Animation
                if (animationPart.PositionPropetiesAnim.IsPositionEnabled())
                {
                    MoveAnimation(rectTransform, counterTween);
                }

                // Rotation Animation
                if (animationPart.RotationPropetiesAnim.IsRotationEnabled())
                {
                    RotateAnimation(rectTransform, counterTween);
                }

                // Scale Animation
                if (animationPart.ScalePropetiesAnim.IsScaleEnabled())
                {
                    ScaleAnimation(rectTransform, counterTween);
                }

                // Fade Animation
                if (animationPart.FadePropetiesAnim.IsFadeEnabled())
                {
                    SetAlphaValue(rectTransform.transform, counterTween);
                }
            }
        }

        public void LateAnimationFrame()
        {
            if (counterTween <= 1f) // Max value is 1f
            {
                counterTween += Time.deltaTime / animationPart.GetAnimationDuration();
            }
            else
            {
                if (!ended)
                {
                    animationPart.Ended();
                    ended = true;
                }
            }

            animationPart.FrameCheck();
        }

        public void PlayOpenAnimations()
        {
            // Open Pos Anim
            if (animationPart.PositionPropetiesAnim.IsPositionEnabled())
            {
                SetCurrentAnimPos(animationPart.PositionPropetiesAnim.TweenCurveEnterPos,
                                  animationPart.PositionPropetiesAnim.StartPos,
                                  animationPart.PositionPropetiesAnim.EndPos);
            }

            // Open Rot Anim
            if (animationPart.RotationPropetiesAnim.IsRotationEnabled())
            {
                SetCurrentAnimRot(animationPart.RotationPropetiesAnim.TweenCurveEnterRot,
                                  animationPart.RotationPropetiesAnim.StartRot,
                                  animationPart.RotationPropetiesAnim.EndRot);
            }

            // Open scale Anim
            if (animationPart.ScalePropetiesAnim.IsScaleEnabled())
            {
                SetCurrentAnimScale(animationPart.ScalePropetiesAnim.TweenCurveEnterScale,
                                    animationPart.ScalePropetiesAnim.StartScale,
                                    animationPart.ScalePropetiesAnim.EndScale);
            }

            // Open Fade Anim
            if (animationPart.FadePropetiesAnim.IsFadeEnabled())
            {
                OpenFadeAnim();
            }

            counterTween = 0f;
            ended = false;

            animationPart.ChangeStatus();
            animationPart.CheckCallbackStatus();
        }

        public void SetStatus(bool status)
        {
            animationPart.SetStatus(status);
        }

        public void PlayCloseAnimations()
        {
            // Close Pos Anim
            if (animationPart.PositionPropetiesAnim.IsPositionEnabled())
            {
                SetCurrentAnimPos(animationPart.PositionPropetiesAnim.TweenCurveExitPos,
                                  animationPart.PositionPropetiesAnim.EndPos,
                                  animationPart.PositionPropetiesAnim.StartPos);
            }

            // Close Rot Anim
            if (animationPart.RotationPropetiesAnim.IsRotationEnabled())
            {
                SetCurrentAnimRot(animationPart.RotationPropetiesAnim.TweenCurveExitRot,
                                  animationPart.RotationPropetiesAnim.EndRot,
                                  animationPart.RotationPropetiesAnim.StartRot);
            }

            // Close Scale Anim
            if (animationPart.ScalePropetiesAnim.IsScaleEnabled())
            {
                SetCurrentAnimScale(animationPart.ScalePropetiesAnim.TweenCurveExitScale,
                                    animationPart.ScalePropetiesAnim.EndScale,
                                    animationPart.ScalePropetiesAnim.StartScale);
            }

            // Close Fade Anim
            if (animationPart.FadePropetiesAnim.IsFadeEnabled())
            {
                CloseFadeAnim();
            }

            counterTween = 0f;
            ended = false;

            animationPart.ChangeStatus();
            animationPart.CheckCallbackStatus();
        }

        public void SetAnimationPos(Vector2 StartAnchoredPos, Vector2 EndAnchoredPos, AnimationCurve EntryTween, AnimationCurve ExitTween, RectTransform rectTransform)
        {
            animationPart.PositionPropetiesAnim.SetPositionEnable(true);
            animationPart.PositionPropetiesAnim.SetPosStart(StartAnchoredPos, rectTransform);
            animationPart.PositionPropetiesAnim.SetPosEnd(EndAnchoredPos, rectTransform.transform);
            animationPart.PositionPropetiesAnim.SetAniamtionsCurve(EntryTween, ExitTween);
        }

        public void SetAnimationScale(Vector2 StartAnchoredScale, Vector2 EndAnchoredScale, AnimationCurve EntryTween, AnimationCurve ExitTween)
        {
            animationPart.ScalePropetiesAnim.StartScale = StartAnchoredScale;
            animationPart.ScalePropetiesAnim.SetScaleEnable(true);
            animationPart.ScalePropetiesAnim.EndScale = EndAnchoredScale;
            animationPart.ScalePropetiesAnim.SetAniamtionsCurve(EntryTween, ExitTween);
        }

        public void SetAnimationRotation(Vector2 StartAnchoredEulerAng, Vector2 EndAnchoredEulerAng, AnimationCurve EntryTween, AnimationCurve ExitTween)
        {
            animationPart.RotationPropetiesAnim.SetRotationEnable(true);
            animationPart.RotationPropetiesAnim.StartRot = StartAnchoredEulerAng;
            animationPart.RotationPropetiesAnim.EndRot = EndAnchoredEulerAng;
            animationPart.RotationPropetiesAnim.SetAniamtionsCurve(EntryTween, ExitTween);
        }

        public void SetFade()
        {
            animationPart.FadePropetiesAnim.SetFadeEnable(true);
        }

        public bool IsObjectOpened()
        {
            return animationPart.IsObjectOpened();
        }

        public void SetAniamtioDuration(float duration)
        {
            animationPart.SetAniamtioDuration(duration);
        }

        public float GetAnimationDuration() { return animationPart.GetAnimationDuration(); }

        #region PositionAnim

        private AnimationCurve currentAnimationCurvePos;
        private Vector3 currentStartPos;
        private Vector3 currentEndPos;

        public void SetCurrentAnimPos(AnimationCurve currentAnimationCurvePos, Vector3 currentStartPos, Vector3 currentEndPos)
        {
            this.currentAnimationCurvePos = currentAnimationCurvePos;
            this.currentStartPos = currentStartPos;
            this.currentEndPos = currentEndPos;
        }

        public void MoveAnimation(RectTransform _rectTransform, float _counterTween)
        {
            float evaluatedValue = currentAnimationCurvePos.Evaluate(_counterTween);
            Vector3 valueAdded = (currentEndPos - currentStartPos) * evaluatedValue;

            _rectTransform.anchoredPosition = (Vector2)(currentStartPos + valueAdded);
        }

        #endregion

        #region ScaleAnim

        private AnimationCurve currentAnimationCurveScale;
        private Vector3 currentStartScale;
        private Vector3 currentEndScale;

        public void SetCurrentAnimScale(AnimationCurve currentAnimationCurveScale, Vector3 currentStartScale, Vector3 currentEndScale)
        {
            this.currentAnimationCurveScale = currentAnimationCurveScale;
            this.currentStartScale = currentStartScale;
            this.currentEndScale = currentEndScale;
        }

        public void ScaleAnimation(RectTransform _rectTransform, float _counterTween)
        {
            float evaluatedValue = currentAnimationCurveScale.Evaluate(_counterTween);
            Vector3 valueAdded = (currentEndScale - currentStartScale) * evaluatedValue;

            _rectTransform.localScale = (Vector2)(currentStartScale + valueAdded);
        }

        #endregion

        #region RotationAnim

        private AnimationCurve currentAnimationCurveRot;
        private Vector3 currentStartRot;
        private Vector3 currentEndRot;

        public void SetCurrentAnimRot(AnimationCurve currentAnimationCurveRot, Vector3 currentStartRot, Vector3 currentEndRot)
        {
            this.currentAnimationCurveRot = currentAnimationCurveRot;
            this.currentStartRot = currentStartRot;
            this.currentEndRot = currentEndRot;
        }

        public void RotateAnimation(RectTransform _rectTransform, float _counterTween)
        {
            float evaluatedValue = currentAnimationCurveRot.Evaluate(_counterTween);
            Vector3 valueAdded = (currentEndRot - currentStartRot) * evaluatedValue;

            _rectTransform.localEulerAngles = (Vector2)(currentStartRot + valueAdded);
        }

        #endregion

        #region FadeAnim

        private float alphaValue = 0f;

        public void OpenFadeAnim()
        {
            alphaValue = 0f;
        }

        public void CloseFadeAnim()
        {
            alphaValue = 1f;
        }

        public void SetAlphaValue(Transform _objectToSetAlpha, float _counterTween)
        {
            if (_objectToSetAlpha.GetComponent<MaskableGraphic>())
            {
                MaskableGraphic GraphicElement = _objectToSetAlpha.GetComponent<MaskableGraphic>();

                Color objectColor = GraphicElement.color;

                _counterTween = Mathf.Clamp(_counterTween, 0f, 1f);

                objectColor.a = Mathf.Abs(alphaValue - _counterTween);
                GraphicElement.color = objectColor;
            }

            if (_objectToSetAlpha.childCount > 0)
            {
                for (int i = 0; i < _objectToSetAlpha.childCount; i++)
                {
                    if (!_objectToSetAlpha.GetChild(i).GetComponent<ReferencedFrom>())
                    {
                        SetAlphaValue(_objectToSetAlpha.GetChild(i), _counterTween);
                    }
                }
            }
        }

        #endregion
    }

    [System.Serializable]
    public class PositionPropetiesAnim
    {
        #region PositionEditor

        [SerializeField]
        [HideInInspector]
        private bool positionEnabled;

        public void SetPositionEnable(bool enabled) { positionEnabled = enabled; }
        public bool IsPositionEnabled() { return positionEnabled; }

        [HideInInspector]
        public AnimationCurve TweenCurveEnterPos;
        [HideInInspector]
        public AnimationCurve TweenCurveExitPos;
        [HideInInspector]
        public Vector3 StartPos;
        [HideInInspector]
        public Vector3 EndPos;
#if UNITY_EDITOR
		[SerializeField] [HideInInspector]
		public Vector3 StartWorldPos ;
		[SerializeField] [HideInInspector]
		public Vector3 EndWorldPos ;
#endif

        public void SetPosStart(Vector3 StartPos, RectTransform rectTr)
        {
            this.StartPos = StartPos;
#if UNITY_EDITOR
			float xMes = (rectTr.anchorMin.x + rectTr.anchorMax.x) / 2f;
			float yMes = (rectTr.anchorMin.y + rectTr.anchorMax.y) / 2f;
			
			Transform rootObject = rectTr.root;
			
			Rect rectangleScreen = rootObject.GetComponent<RectTransform>().rect;
			
			StartWorldPos.x = (xMes * rectangleScreen.width + StartPos.x) * rootObject.localScale.x;
			StartWorldPos.y = (yMes * rectangleScreen.height + StartPos.y) * rootObject.localScale.y;
#endif
        }

        public void SetPosEnd(Vector3 EndPos, Transform rectTr)
        {
            this.EndPos = EndPos;
#if UNITY_EDITOR
			EndWorldPos.x = StartWorldPos.x + (EndPos.x - StartPos.x) * rectTr.root.localScale.x;
			EndWorldPos.y = StartWorldPos.y + (EndPos.y - StartPos.y) * rectTr.root.localScale.y;
#endif
        }

        public void SetAniamtionsCurve(AnimationCurve EntryTween, AnimationCurve ExitTween)
        {
            TweenCurveEnterPos = EntryTween;
            TweenCurveExitPos = ExitTween;
        }

        #endregion
    }

    [System.Serializable]
    public class ScalePropetiesAnim
    {
        #region ScaleEditor

        [SerializeField]
        [HideInInspector]
        private bool scaleEnabled;

        public void SetScaleEnable(bool enabled) { scaleEnabled = enabled; }
        public bool IsScaleEnabled() { return scaleEnabled; }

        [HideInInspector]
        public AnimationCurve TweenCurveEnterScale;
        [HideInInspector]
        public AnimationCurve TweenCurveExitScale;
        [HideInInspector]
        public Vector3 StartScale;
        [HideInInspector]
        public Vector3 EndScale;

        public void SetAniamtionsCurve(AnimationCurve EntryTween, AnimationCurve ExitTween)
        {
            TweenCurveEnterScale = EntryTween;
            TweenCurveExitScale = ExitTween;
        }

        #endregion
    }

    [System.Serializable]
    public class RotationPropetiesAnim
    {
        #region RotationEditor

        [SerializeField]
        [HideInInspector]
        private bool rotationEnabled;

        public void SetRotationEnable(bool enabled) { rotationEnabled = enabled; }
        public bool IsRotationEnabled() { return rotationEnabled; }

        [HideInInspector]
        public AnimationCurve TweenCurveEnterRot;
        [HideInInspector]
        public AnimationCurve TweenCurveExitRot;
        [HideInInspector]
        public Vector3 StartRot;
        [HideInInspector]
        public Vector3 EndRot;

        public void SetAniamtionsCurve(AnimationCurve EntryTween, AnimationCurve ExitTween)
        {
            TweenCurveEnterRot = EntryTween;
            TweenCurveExitRot = ExitTween;
        }

        #endregion
    }

    [System.Serializable]
    public class FadePropetiesAnim
    {
        #region FadeEditor

        [SerializeField]
        [HideInInspector]
        private bool fadeInOutEnabled;

        public void SetFadeEnable(bool enabled) { fadeInOutEnabled = enabled; }
        public bool IsFadeEnabled() { return fadeInOutEnabled; }

        #endregion
    }

    public interface IAniamtionPartProxy
    {
        bool IsObjectOpened();
        void ChangeStatus();
        void SetAniamtioDuration(float duration);
        float GetAnimationDuration();
    }

    [System.Serializable]
    public class AnimationParts : IAniamtionPartProxy
    {
        public delegate void DisableOrDestroy(bool disable, AnimationParts part);
        public static event DisableOrDestroy OnDisableOrDestroy;

        public delegate void CheckForButtons();
        public static event CheckForButtons OnCheckForButtons;

        #region PositionEditor

        [HideInInspector]
        public PositionPropetiesAnim PositionPropetiesAnim = new PositionPropetiesAnim();

        #endregion

        #region ScaleEditor

        [HideInInspector]
        public ScalePropetiesAnim ScalePropetiesAnim = new ScalePropetiesAnim();

        #endregion

        #region RotationEditor

        [HideInInspector]
        public RotationPropetiesAnim RotationPropetiesAnim = new RotationPropetiesAnim();

        #endregion

        #region FadeEditor

        [HideInInspector]
        public FadePropetiesAnim FadePropetiesAnim = new FadePropetiesAnim();

        #endregion

        #region PUBLIC_Var

        public void SetAniamtioDuration(float duration) { if (duration > 0f) animationDuration = duration; else duration = 0.01f; }
        public float GetAnimationDuration() { return animationDuration; }

        public enum State { OPEN, CLOSE };
        public State ObjectState = State.CLOSE;

        public bool SaveState = false;

        public enum EndTweenClose { DEACTIVATE, DESTROY, NOTHING };
        public EndTweenClose EndState = EndTweenClose.DEACTIVATE;

        public enum CallbackCall
        {
            END_OF_INTRO_ANIM,
            END_OF_EXIT_ANIM,
            END_OF_INTRO_AND_END_OF_EXIT_ANIM,
            START_INTRO_ANIM,
            START_INTRO_END_OF_EXIT_ANIM,
            START_INTRO_END_OF_INTRO_ANIM,
            START_INTRO_END_OF_INTRO_AND_END_OF_EXIT_ANIM,
            START_EXIT_ANIM,
            START_EXIT_END_OF_EXIT_ANIM,
            START_EXIT_END_OF_INTRO_ANIM,
            START_EXIT_END_OF_INTRO_AND_END_OF_EXIT_ANIM,
            START_INTRO_AND_START_EXIT_END_OF_EXIT_ANIM,
            START_INTRO_AND_START_EXIT_END_OF_INTRO_ANIM,
            START_INTRO_AND_START_EXIT_END_OF_INTRO_AND_END_OF_EXIT_ANIM,
            START_EXIT_START_INTRO_ANIM
        };

        public CallbackCall CallCallback = CallbackCall.END_OF_INTRO_ANIM;

        public Transform[] CallbackObjectIntro = new Transform[0];
        public Transform[] CallbackObjectExit = new Transform[0];

        #endregion

        #region PRIVATE_Var

        private bool CheckNextFrame = false;
        private bool CallOnThisFrame = false;
        private Transform[] CallbackObject;

        [SerializeField]
        [HideInInspector]
        private float animationDuration = 1f;

        #endregion

        #region PUBLIC_Methods

        public AnimationParts(State ObjectState, bool SaveState, EndTweenClose EndState, CallbackCall CallCallback, Transform[] CallbackObjectIntro, Transform[] CallbackObjectExit)
        {
            this.ObjectState = ObjectState;
            this.SaveState = SaveState;
            this.EndState = EndState;
            this.CallCallback = CallCallback;
            this.CallbackObjectIntro = CallbackObjectIntro;
            this.CallbackObjectExit = CallbackObjectExit;
        }

        public void CheckCallbackStatus()
        {
            if ((CallCallback == CallbackCall.START_INTRO_END_OF_EXIT_ANIM
                 || CallCallback == CallbackCall.START_INTRO_ANIM
                 || CallCallback == CallbackCall.START_INTRO_END_OF_INTRO_ANIM
                 || CallCallback == CallbackCall.START_INTRO_END_OF_INTRO_AND_END_OF_EXIT_ANIM
                 || CallCallback == CallbackCall.START_INTRO_AND_START_EXIT_END_OF_EXIT_ANIM
                 || CallCallback == CallbackCall.START_INTRO_AND_START_EXIT_END_OF_INTRO_ANIM
                 || CallCallback == CallbackCall.START_INTRO_AND_START_EXIT_END_OF_INTRO_AND_END_OF_EXIT_ANIM
                 || CallCallback == CallbackCall.START_EXIT_START_INTRO_ANIM) && ObjectState == State.OPEN)
            {
                CheckCallBack(CallbackObjectIntro);
            }
            else if ((CallCallback == CallbackCall.START_EXIT_END_OF_EXIT_ANIM
                      || CallCallback == CallbackCall.START_EXIT_ANIM
                      || CallCallback == CallbackCall.START_EXIT_END_OF_INTRO_ANIM
                      || CallCallback == CallbackCall.START_EXIT_END_OF_INTRO_AND_END_OF_EXIT_ANIM
                      || CallCallback == CallbackCall.START_INTRO_AND_START_EXIT_END_OF_EXIT_ANIM
                      || CallCallback == CallbackCall.START_INTRO_AND_START_EXIT_END_OF_INTRO_ANIM
                      || CallCallback == CallbackCall.START_INTRO_AND_START_EXIT_END_OF_INTRO_AND_END_OF_EXIT_ANIM
                      || CallCallback == CallbackCall.START_EXIT_START_INTRO_ANIM) && ObjectState == State.CLOSE)
            {
                CheckCallBack(CallbackObjectExit);
            }
        }

        public void Ended()
        {
            if (ObjectState == State.CLOSE)
            {
                if (CallCallback == CallbackCall.END_OF_EXIT_ANIM
                    || CallCallback == CallbackCall.END_OF_INTRO_AND_END_OF_EXIT_ANIM
                    || CallCallback == CallbackCall.START_INTRO_END_OF_EXIT_ANIM
                    || CallCallback == CallbackCall.START_INTRO_END_OF_INTRO_AND_END_OF_EXIT_ANIM
                    || CallCallback == CallbackCall.START_EXIT_END_OF_EXIT_ANIM
                    || CallCallback == CallbackCall.START_EXIT_END_OF_INTRO_AND_END_OF_EXIT_ANIM
                    || CallCallback == CallbackCall.START_INTRO_AND_START_EXIT_END_OF_EXIT_ANIM
                    || CallCallback == CallbackCall.START_INTRO_AND_START_EXIT_END_OF_INTRO_AND_END_OF_EXIT_ANIM
                    )
                {
                    CheckCallBack(CallbackObjectExit);
                }

                if (EndState == EndTweenClose.DEACTIVATE)
                {
                    if (OnDisableOrDestroy != null)
                    {
                        OnDisableOrDestroy(true, this);
                    }
                }
                else if (EndState == EndTweenClose.DESTROY)
                {
                    if (OnDisableOrDestroy != null)
                    {
                        OnDisableOrDestroy(false, this);
                    }
                }
            }

            if ((CallCallback == CallbackCall.END_OF_INTRO_ANIM
                 || CallCallback == CallbackCall.END_OF_INTRO_AND_END_OF_EXIT_ANIM
                 || CallCallback == CallbackCall.START_INTRO_END_OF_INTRO_ANIM
                 || CallCallback == CallbackCall.START_INTRO_END_OF_INTRO_AND_END_OF_EXIT_ANIM
                 || CallCallback == CallbackCall.START_EXIT_END_OF_INTRO_ANIM
                 || CallCallback == CallbackCall.START_EXIT_END_OF_INTRO_AND_END_OF_EXIT_ANIM
                 || CallCallback == CallbackCall.START_INTRO_AND_START_EXIT_END_OF_INTRO_ANIM
                 || CallCallback == CallbackCall.START_INTRO_AND_START_EXIT_END_OF_INTRO_AND_END_OF_EXIT_ANIM) && ObjectState == State.OPEN)
            {
                CheckCallBack(CallbackObjectIntro);
            }

            if (SaveState)
            {
                if (ObjectState == State.OPEN) ObjectState = State.CLOSE;
                else if (ObjectState == State.CLOSE) ObjectState = State.OPEN;
            }

            if (OnCheckForButtons != null)
            {
                OnCheckForButtons();
            }
        }

        public void FrameCheck()
        {
            if (CheckNextFrame)
            {
                if (CallOnThisFrame)
                {
                    CallCallbackObjects();
                }

                CallOnThisFrame = !CallOnThisFrame;
            }
        }

        public bool IsObjectOpened()
        {
            if (ObjectState == State.CLOSE)
            {
                return false;
            }

            return true;
        }

        public void ChangeStatus()
        {
            if (ObjectState == State.CLOSE)
            {
                ObjectState = State.OPEN;
            }
            else
            {
                ObjectState = State.CLOSE;
            }
        }

        public void SetStatus(bool open)
        {
            if (open)
            {
                ObjectState = State.OPEN;
            }
            else
            {
                ObjectState = State.CLOSE;
            }
        }

        #endregion

        #region PRIVATE_Methods

        private void CheckCallBack(Transform[] CallbackObject)
        {
            this.CallbackObject = CallbackObject;

            if (CallbackObject.Length > 0)
            {
                CheckNextFrame = !CheckNextFrame;

                for (int i = 0; i < CallbackObject.Length; i++)
                {
                    CallbackObject[i].gameObject.SetActive(true);
                }
            }
        }

        private void CallCallbackObjects()
        {
            if (CallbackObject.Length > 0)
            {
                CheckNextFrame = !CheckNextFrame;

                for (int i = 0; i < CallbackObject.Length; i++)
                {
                    try
                    {
                        ICallbackAfterTween callBack = CallbackObject[i].GetComponent(typeof(ICallbackAfterTween)) as ICallbackAfterTween;
                        callBack.CallBack();
                    }
                    catch
                    {
                        Debug.LogError("This Transform doesn't Implement \"ICallbackAfterTween\" Interface.");
                    }
                }
            }
        }

        #endregion
    }

    public interface ICallbackAfterTween
    {
        void CallBack();
    }
}