package com.thedespite.warwarrior;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.util.Log;

/**
 * Created by Despite on 28.1.2014.
 */
public class Game {

    public static vect2 screenDimensions;

    float td;
    long lastTime;
    long lastEnemyTime;


    // Called after LoadData
    public void Start()
    {
        if (Camera.instance == null)
            new Camera();

        if (Player.instance == null)
            new Player();

        Restart();
    }

    // Called after Start
    public void Restart()
    {
        lastTime = System.currentTimeMillis();
        lastEnemyTime = lastTime - 12000; // 16s - 12s = 4sec to first enemy
    }

    public void End()
    {

    }

    // Called first
    public void LoadData(Context context)
    {
        SpriteMgr.drawables[Sprite.S_PLAYER.ID()] = context.getResources().getDrawable(R.drawable.player);
        SpriteMgr.drawables[Sprite.S_ENEMY.ID()] = context.getResources().getDrawable(R.drawable.enemy);
        SpriteMgr.drawables[Sprite.S_PROJECTILE.ID()] = context.getResources().getDrawable(R.drawable.projectile);
        SpriteMgr.drawables[Sprite.S_SHIELD.ID()] = context.getResources().getDrawable(R.drawable.shield);
        SpriteMgr.drawables[Sprite.S_SHIELD_PARTICLE.ID()] = context.getResources().getDrawable(R.drawable.shieldparticle);
        SpriteMgr.drawables[Sprite.S_EXPLOSION_PARTICLE.ID()] = context.getResources().getDrawable(R.drawable.explosionparticle);
        SpriteMgr.drawables[Sprite.S_BACKGROUND.ID()] = context.getResources().getDrawable(R.drawable.background);
    }

    public void Update()
    {
        td = System.currentTimeMillis() - lastTime;

        Camera.instance.Update(td); // Update camera first

        if (System.currentTimeMillis() - lastEnemyTime > 16000)
        { // Add new enemy if it was 16s since last
            new Enemy();
            lastEnemyTime = System.currentTimeMillis();
        }

        for (int i = 0; i < GameItem.gameItems.size(); i++)
        {
            GameItem.gameItems.get(i).Update(td);
        }

        lastTime = System.currentTimeMillis();
    }

    public void Draw(Canvas canvas)
    {
        // Clear the background
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);

        // Draw the background
        for (int x = (int)(Camera.instance.position.x - Sprite.S_BACKGROUND.Dimensions().x) / (int)Sprite.S_BACKGROUND.Dimensions().x * (int)Sprite.S_BACKGROUND.Dimensions().x;
             x < (int)Camera.instance.position.x + screenDimensions.x;
             x += Sprite.S_BACKGROUND.Dimensions().x)
        {
            for (int y = (int)(Camera.instance.position.y - Sprite.S_BACKGROUND.Dimensions().y) / (int)Sprite.S_BACKGROUND.Dimensions().y * (int)Sprite.S_BACKGROUND.Dimensions().y;
                 y < (int)Camera.instance.position.y + screenDimensions.y;
                 y += Sprite.S_BACKGROUND.Dimensions().y)
            {
                SpriteMgr.Draw(canvas, Sprite.S_BACKGROUND, new vect2(x, y));
            }
        }

        for (int i = 0; i < GameItem.gameItems.size(); i++)
        {
            GameItem.gameItems.get(i).Draw(canvas);
        }
    }
}
