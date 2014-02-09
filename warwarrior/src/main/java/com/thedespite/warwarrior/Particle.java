package com.thedespite.warwarrior;

import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by Despite on 28.1.2014.
 */
public class Particle extends GameItem {

    protected static final int size = 4;
    protected static final long LIFE_TIME = 5000;
    protected static Random rnd;
    protected long spawnTime;

    static {
        rnd = new Random();
    }

    public Particle(vect2 position)
    {
        super();

        this.position = new vect2(position);
        spawnTime = System.currentTimeMillis();
    }

    public static void GenerateDeadParticles(vect2 deadPos)
    {
        for (int i = 0; i < 50; i++)
        {
            new DeadParticle(deadPos);
        }
    }

    public static void GenerateShieldParticles(Pawn owner, vect2 hitPos, int hitAmount)
    {
        for (int i = 0; i < hitAmount; i++)
        {
            new ShieldParticle(owner, hitPos);
        }
    }

    public void Update(float dt)
    {
        if (System.currentTimeMillis() - spawnTime > LIFE_TIME)
        {
            gameItems.remove(this);
            return;
        }

        super.Update(dt);
    }

    public void Draw(Canvas canvas)
    {
        // Don't call super
        SpriteMgr.Draw(canvas, graphics, position, size);
    }
}
