package com.thedespite.warwarrior;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Despite on 28.1.2014.
 */
public class Pawn extends GameItem
{
    public static List<Pawn> pawns;

    protected static final float TURN_SPEED = 0.005f;

    protected int shieldCapacity;
    protected float destinationRotation;
    protected boolean firing = false;

    static {
        pawns = new ArrayList<Pawn>();
    }

    public Pawn()
    {
        super();

        pawns.add(this);
        shieldCapacity = 100;
    }

    protected void Fire()
    {
        new Projectile(this, position, rotation);
        firing = false;
    }

    protected void SetDestinationRotationTowards(vect2 destination)
    {
        destinationRotation = (float)Math.atan2(destination.y - position.y, destination.x - position.x);
    }

    protected void TookHit(int amount)
    {
        if (shieldCapacity == 0)
            Dead();

        int tempCap = shieldCapacity;

        shieldCapacity -= amount;
        if (shieldCapacity < 0)
            shieldCapacity = 0;

        Particle.GenerateShieldParticles(this, position, tempCap - shieldCapacity);
    }

    public void PickedShieldParticle()
    {
        shieldCapacity += 1;
    }

    public void Dead()
    {
        gameItems.remove(this);
        pawns.remove(this);

        // particles
        Particle.GenerateDeadParticles(position);
    }

    public void Rotate(float td)
    {
        if (Math.abs(destinationRotation - rotation) > 0.2f)
        { // Rotate only if over the tolerance
            if ((Math.abs(rotation) + Math.abs(destinationRotation) > Math.PI) && ((rotation > 0 && destinationRotation < 0) || (rotation < 0 && destinationRotation > 0)))
            { // Does cross 0
                if (destinationRotation > 0.0f)
                    rotation -= TURN_SPEED * td;
                else
                    rotation += TURN_SPEED * td;
            }

            else
            {
                if (destinationRotation - rotation >= 0)
                    rotation += TURN_SPEED * td;
                else
                    rotation -= TURN_SPEED * td;
            }

            if (rotation >= Math.PI)
                rotation -= 2 * Math.PI;
            else if (rotation < -Math.PI)
                rotation += 2 * Math.PI;
        } // End of if should touch rotation
    }

    public void Update(float td)
    {
        Rotate(td);

        if (firing)
            Fire();

        super.Update(td);
    }

    public void Draw(Canvas canvas)
    {
        super.Draw(canvas);

        if (shieldCapacity > 0) // Draw the shield on the pawn if excists
            SpriteMgr.Draw(canvas, Sprite.S_SHIELD, position, 64 + shieldCapacity);
    }
}
