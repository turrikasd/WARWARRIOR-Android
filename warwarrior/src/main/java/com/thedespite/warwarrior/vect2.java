package com.thedespite.warwarrior;

/**
 * Created by Despite on 28.1.2014.
 */
public class vect2
{

    public float x;
    public float y;

    public vect2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public vect2(vect2 vector)
    {
        this.x = vector.x;
        this.y = vector.y;
    }


    public void eAdd(float x, float y)
    {
        this.x += x;
        this.y += y;
    }

    public void eAdd(vect2 vector)
    {
        this.x += vector.x;
        this.y += vector.y;
    }

    public void eSub(float x, float y)
    {
        this.x -= x;
        this.y -= y;
    }

    public void eSub(vect2 vector)
    {
        this.x -= vector.x;
        this.y -= vector.y;
    }

    public void eMul(float times)
    {
        this.x *= times;
        this.y *= times;
    }

    public void eDiv(float parts)
    {
        this.x /= parts;
        this.y /= parts;
    }

    public vect2 add(float x, float y)
    {
        return new vect2(this.x + x, this.y + y);
    }

    public vect2 add(vect2 vector)
    {
        return add(vector.x, vector.y);
    }

    public vect2 sub(vect2 vector)
    {
        return new vect2(this.x - vector.x, this.y - vector.y);
    }

    public vect2 mul(float times)
    {
        return new vect2(times * this.x, times * this.y);
    }

    public vect2 normalized()
    {
        float x = Math.abs(this.x);
        float y = Math.abs(this.y);

        float divider;

        if (x >= y)
            divider = x;
        else
            divider = y;

        return new vect2(this.x / divider, this.y / divider);
    }

    public static vect2 zero()
    {
        return new vect2(0.0f, 0.0f);
    }

    public static vect2 one()
    {
        return new vect2(1.0f, 1.0f);
    }

    public static vect2 up()
    {
        return new vect2(0.0f, -1.0f);
    }

    public static vect2 down()
    {
        return new vect2(0.0f, 1.0f);
    }

    public static vect2 left()
    {
        return new vect2(-1.0f, 0.0f);
    }

    public static vect2 right()
    {
        return new vect2(1.0f, 0.0f);
    }
}