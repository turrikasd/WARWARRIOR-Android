package com.thedespite.warwarrior;

import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by Despite on 1.2.2014.
 */
public class DeadParticle extends Particle {

    DeadParticle(vect2 position)
    {
        super(position);

        this.graphics = Sprite.S_EXPLOSION_PARTICLE;
        this.position.eAdd(-5 + rnd.nextFloat() * 10, -5 + rnd.nextFloat() * 10);
        this.velocity = 0.1f * rnd.nextFloat();
        this.rotation = -3 + rnd.nextInt(6);
    }

    public void Update(float td)
    {
        super.Update(td); // Super first to handle expired particles

        for (int i = 0; i < Pawn.pawns.size(); i++)
        {
            /*
            if (CheckCollision(Pawn.pawns.get(i)))
            {
                velocity = Pawn.pawns.get(i).velocity * 1.2f;
                rotation = Pawn.pawns.get(i).rotation;
                break;
            }
            */

            if (Tools.DoesCollideWithShield(Pawn.pawns.get(i), position))
            {
                velocity = Pawn.pawns.get(i).velocity * 1.2f;
                rotation = Pawn.pawns.get(i).rotation;
                break;
            }
        }
    }

    public void Draw(Canvas canvas)
    {
        super.Draw(canvas);
    }
}
