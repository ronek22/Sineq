package com.mygdx.game.enums;

import com.mygdx.game.utils.Constants;

/**
 * Created by kubar on 17.01.2018.
 */

public enum PlatformType {
    DEFAULT(2f, 0.5f, Constants.PLATFORM_X, Constants.PLATFORM_Y, Constants.PLATFORM_DENSITY, Constants.PLATFORM_FRICTION);

    private float width;
    private float height;
    private float x;
    private float y;
    private float density;
    private float friction;

    PlatformType(float width, float height, float x, float y, float density, float friction){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.density = density;
        this.friction = friction;
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
}
