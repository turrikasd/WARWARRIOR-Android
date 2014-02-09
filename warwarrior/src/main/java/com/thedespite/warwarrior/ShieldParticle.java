package com.thedespite.warwarrior;

import android.graphics.Canvas;

/**
 * Created by Despite on 3.2.2014.
 */
public class ShieldParticle extends Particle {

    Pawn owner;

    ShieldParticle(Pawn owner, vect2 position)
    {
        super(position);

        this.graphics = Sprite.S_SHIELD_PARTICLE;
        this.owner = owner;
        this.position.eAdd(-5 + rnd.nextFloat() * 10, -5 + rnd.nextFloat() * 10);
        this.velocity = 0.05f * rnd.nextFloat();
        this.rotation = -3 + rnd.nextInt(6);
    }

    public void Update(float td)
    {
        super.Update(td); // Super first to handle expired particles

        for (int i = 0; i < Pawn.pawns.size(); i++)
        {
            if (Pawn.pawns.get(i) == owner)
                continue; // Disallow picking up own shield

            /*
            if (CheckCollision(Pawn.pawns.get(i)))
            {
                Pawn.pawns.get(i).PickedShieldParticle();
                gameItems.remove(this);
                break;
            }
            */

            if (Tools.DoesCollideWithShield(Pawn.pawns.get(i), position))
            {
                Pawn.pawns.get(i).PickedShieldParticle();
                gameItems.remove(this);
                break;
            }
        }
    }

    public void Draw(Canvas canvas)
    {
        super.Draw(canvas);
    }
}
