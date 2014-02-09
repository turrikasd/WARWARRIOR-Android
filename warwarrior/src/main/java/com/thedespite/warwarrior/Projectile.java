package com.thedespite.warwarrior;

import android.graphics.Canvas;

/**
 * Created by Despite on 30.1.2014.
 */
public class Projectile extends GameItem {

    protected static final int size = 8;
    protected long spawnTime;
    protected Pawn owner;

    public Projectile(Pawn owner, vect2 firedPos, float firedRotation)
    {
        super();

        this.owner = owner;
        rotation = firedRotation; // Rotation first. required for everything
        position = new vect2(firedPos); // Create new instance instead of copying reference
        MoveToDir(16.0f); // Move to the front of Pawn
        velocity = 0.6f;
        graphics = Sprite.S_PROJECTILE;
        spawnTime = System.currentTimeMillis();
    }

    public void Update(float td)
    {
        if (System.currentTimeMillis() - spawnTime > 2000)
        {
            gameItems.remove(this);
            return;
        }

        for (int i = 0; i < Pawn.pawns.size(); i++)
        {
            /*
            if (CheckCollision(Pawn.pawns.get(i)))
            {
                Pawn.pawns.get(i).TookHit(25);

                gameItems.remove(this);
                break;
            }*/

            if (Pawn.pawns.get(i) == owner)
                continue; // Don't hit self

            if (Tools.DoesCollideWithShield(Pawn.pawns.get(i), position))
            {
                Pawn.pawns.get(i).TookHit(25);

                gameItems.remove(this);
                break;
            }
        }

        super.Update(td);
    }

    public void Draw(Canvas canvas)
    { // Doesn't call super
        SpriteMgr.Draw(canvas, graphics, position, size);
    }
}
