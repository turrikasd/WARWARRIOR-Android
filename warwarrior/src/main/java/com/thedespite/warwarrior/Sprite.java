package com.thedespite.warwarrior;

/**
 * Created by Despite on 29.1.2014.
 */
public enum Sprite
{
    S_PLAYER(0),
    S_ENEMY(1),
    S_PROJECTILE(2),
    S_SHIELD(3),
    S_SHIELD_PARTICLE(4),
    S_EXPLOSION_PARTICLE(5),
    S_BACKGROUND(6),
    SPRITE_COUNT(7);


    private final int id;

    private Sprite(int id)
    {
        this.id = id;
    }

    public int ID()
    {
        return id;
    }

    public vect2 Dimensions()
    {
        return new vect2(SpriteMgr.drawables[this.ID()].getIntrinsicWidth(),
                         SpriteMgr.drawables[this.ID()].getIntrinsicHeight());
    }
}