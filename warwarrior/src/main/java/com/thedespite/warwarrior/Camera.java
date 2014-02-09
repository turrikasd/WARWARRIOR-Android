package com.thedespite.warwarrior;

/**
 * Created by Despite on 28.1.2014.
 */
public class Camera {

    public static Camera instance;

    public vect2 position;

    public Camera()
    {
        position = new vect2(0.0f, 0.0f);

        instance = this;
    }

    public vect2 Transform(vect2 vector)
    {
        return vector.sub(position);
    }

    public vect2 TransformTouch(vect2 vector)
    {
        return vector.add(position);
    }

    public void Update(float td)
    {
        float multiplier = 10.0f;

        position.x -= multiplier * (position.x - Player.instance.position.x + Game.screenDimensions.x / 2) / Game.screenDimensions.x;
        position.y -= multiplier * (position.y - Player.instance.position.y + Game.screenDimensions.y / 2) / Game.screenDimensions.y;
    }
}
