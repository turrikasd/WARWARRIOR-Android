package com.thedespite.warwarrior;

import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by Despite on 30.1.2014.
 */
public class Enemy extends Pawn {

    protected long lastFire;

    public Enemy()
    {
        super();

        Random rnd = new Random();
        position = new vect2(Player.instance.position);
        position.eAdd(- 500 + rnd.nextInt(1000), - 500 + rnd.nextInt(1001));
        velocity = 0.1f;
        graphics = Sprite.S_ENEMY;
        lastFire = System.currentTimeMillis(); // Do not fire instantly
    }

    public void Update(float td)
    {
        SetDestinationRotationTowards(Player.instance.position);

        if (System.currentTimeMillis() - lastFire > 1575) // Fire every ~1.5s. Randomness to avoid firing in sync
        {
            firing = true;
            lastFire = System.currentTimeMillis();
        }

        super.Update(td);
    }

    public void Draw(Canvas canvas)
    {
        super.Draw(canvas);
    }
}
