package com.thedespite.warwarrior;

/**
 * Created by Despite on 3.2.2014.
 */
public class Tools {

    public static boolean DoesCollideWithShield(Pawn p, vect2 position)
    {
        if (GetShieldRadius(p) > Distance(p.position, position))
            return true;

        return false;
    }

    public static float GetShieldRadius(Pawn p)
    {
        return 0.5f * (p.shieldCapacity + 48.0f);
    }

    public static float Distance(vect2 a, vect2 b)
    {
        float x = a.x - b.x;
        float y = a.y - b.y;

        return (float)Math.sqrt(x * x + y * y);
    }
}
