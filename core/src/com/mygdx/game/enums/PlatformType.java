package com.mygdx.game.enums;

import com.mygdx.game.utils.Constants;

/**
 * Created by kubar on 17.01.2018.
 */

public enum PlatformType {
    DEFAULT(2f, 0.5f, Constants.PLATFORM_X, Constants.PLATFORM_Y, Constants.PLATFORM_DENSITY, Constants.PLATFORM_FRICTION, 4f),
    WIDE(4f, 0.5f, Constants.PLATFORM_X, Constants.PLATFORM_Y, Constants.PLATFORM_DENSITY, Constants.PLATFORM_FRICTION, 6f);

    private float width;
    private float height;
    private float x;
    private float y;
    private float density;
    private float friction;
    private float gap;

    PlatformType(float width, float height, float x, float y, float density, float friction, float gap){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.density = density;
        this.friction = friction;
        this.gap = gap;
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public void resetPosition(){
        this.x = Constants.PLATFORM_X;
        this.y = Constants.PLATFORM_Y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getDensity() {
        return density;
    }

    public float getFriction() {
        return friction;
    }

    public float getGap() { return gap;}

    public String getName() {
        return this.name();
    }

    public static void reset() {
        PlatformType.WIDE.setX(Constants.PLATFORM_X);
        PlatformType.WIDE.setY(Constants.PLATFORM_Y);

        PlatformType.DEFAULT.setX(Constants.PLATFORM_X);
        PlatformType.DEFAULT.setY(Constants.PLATFORM_Y);
    }
}
