package com.thedespite.warwarrior;

import android.graphics.Canvas;

/**
 * Created by Despite on 28.1.2014.
 */
public class Player extends Pawn {

    public static Player instance;

    public Player()
    {
        super();
        instance = this;
        position = new vect2(Game.screenDimensions.x / 2, Game.screenDimensions.y / 2);
        velocity = 0.35f;
        graphics = Sprite.S_PLAYER;
    }

    public void Update(float dt)
    {
        // set rotation to point in the direction of finger
        SetDestinationRotationTowards(InputMgr.fingerPosTranslated);

        if (InputMgr.newFinger)
        {
            firing = true;
            InputMgr.newFinger = false;
        }

        super.Update(dt);
    }

    public void Draw(Canvas canvas)
    {
        super.Draw(canvas);
    }
}
