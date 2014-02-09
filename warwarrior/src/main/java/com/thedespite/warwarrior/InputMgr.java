package com.thedespite.warwarrior;

/**
 * Created by Despite on 30.1.2014.
 */
public class InputMgr {

    //public static vect2 fingerPos = vect2.zero();
    public static vect2 fingerPosTranslated = vect2.zero();
    public static boolean newFinger = false;

    public static void SetFingerPosRelativeToCamera(vect2 fingerPos)
    {
        fingerPosTranslated = (Camera.instance.TransformTouch(fingerPos));
    }
}
