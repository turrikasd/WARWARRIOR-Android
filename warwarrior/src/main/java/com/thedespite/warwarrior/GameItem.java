package com.thedespite.warwarrior;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Despite on 28.1.2014.
 */
public class GameItem
{

    static List<GameItem> gameItems;
    static {     // Static "constructor"
        gameItems = new ArrayList<GameItem>();
    }

    protected vect2 position;
    protected float velocity;
    protected float rotation;
    protected Sprite graphics;
    protected boolean visible = true;

    public GameItem()
    {
        gameItems.add(this);
    }

    protected void Move(float td)
    {
        vect2 dir = new vect2((float)Math.cos(rotation), (float)Math.sin(rotation));
        dir.eMul(velocity);
        dir.eMul(td);
        position.eAdd(dir);
    }

    protected void MoveToDir(float length)
    {
        vect2 dir = new vect2((float)Math.cos(rotation), (float)Math.sin(rotation));
        dir.eMul(length);
        position.eAdd(dir);
    }

    protected boolean CheckCollision(GameItem gameItem)
    {
        if (Math.abs(this.position.x - gameItem.position.x) < 30.0f &&
            Math.abs(this.position.y - gameItem.position.y) < 30.0f)
            return true;

        return false;
    }

    public void Update(float td)
    {
        Move(td);
    }

    public void Draw(Canvas canvas)
    {
        SpriteMgr.Draw(canvas, graphics, position, rotation);
    }
}
