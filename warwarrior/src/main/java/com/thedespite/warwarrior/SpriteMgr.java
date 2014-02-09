package com.thedespite.warwarrior;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by Despite on 29.1.2014.
 */
public class SpriteMgr {

    public static Drawable[] drawables = new Drawable[Sprite.SPRITE_COUNT.ID()];

    public static void Draw(Canvas canvas, Sprite graphics, vect2 pos)
    { // Used for background
        vect2 p = new vect2(pos);
        p = Camera.instance.Transform(p);

        drawables[graphics.ID()].setBounds((int)p.x,
                                           (int)p.y,
                                           (int)p.x + (int)graphics.Dimensions().x,
                                           (int)p.y + (int)graphics.Dimensions().y);
        drawables[graphics.ID()].draw(canvas);
    }

    public static void Draw(Canvas canvas, Sprite graphics, vect2 pos, float rotation)
    {
        vect2 p = new vect2(pos);
        p = Camera.instance.Transform(p);

        float multiplier = 0.03f;
        int lesserScreenDimension = (int)Game.screenDimensions.x;
        if (Game.screenDimensions.y < lesserScreenDimension)
            lesserScreenDimension = (int)Game.screenDimensions.y;

        int halfOffSet = (int)(multiplier * lesserScreenDimension);

        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.rotate((float)(rotation * (180 / Math.PI) + 90.0f), p.x, p.y);
        drawables[graphics.ID()].setBounds((int)p.x - halfOffSet, (int)p.y - halfOffSet, (int)p.x + halfOffSet, (int)p.y + halfOffSet);
        drawables[graphics.ID()].draw(canvas);
        canvas.restore();
    }

    public static void Draw(Canvas canvas, Sprite graphics, vect2 pos, int size)
    { // Used by projectiles, particles and shields
        vect2 p = new vect2(pos);
        p = Camera.instance.Transform(p);

        drawables[graphics.ID()].setBounds((int)p.x - size / 2, (int)p.y - size / 2, (int)p.x + size / 2, (int)p.y + size / 2);
        drawables[graphics.ID()].draw(canvas);
    }
}
