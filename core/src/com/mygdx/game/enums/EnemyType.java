package com.mygdx.game.enums;

import com.mygdx.game.utils.Constants;

public enum EnemyType {

    RUNNING_SMALL(1f, 1f, Constants.ENEMY_X, Constants.RUNNING_SHORT_ENEMY_Y, Constants.ENEMY_DENSITY, Constants.RUNNING_SMALL_TEXTURE),
//    RUNNING_WIDE(2f, 1f, Constants.ENEMY_X, Constants.RUNNING_SHORT_ENEMY_Y, Constants.ENEMY_DENSITY),
//    RUNNING_LONG(1f, 2f, Constants.ENEMY_X, Constants.RUNNING_LONG_ENEMY_Y, Constants.ENEMY_DENSITY),
    RUNNING_BIG(2f, 2f, Constants.ENEMY_X, Constants.RUNNING_LONG_ENEMY_Y, Constants.ENEMY_DENSITY, Constants.RUNNING_BIG_TEXTURE);
//    FLYING_SMALL(1f, 1f, Constants.ENEMY_X, Constants.FLYING_ENEMY_Y, Constants.ENEMY_DENSITY),
//    FLYING_WIDE(2f, 1f, Constants.ENEMY_X, Constants.FLYING_ENEMY_Y, Constants.ENEMY_DENSITY);

    private float width;
    private float height;
    private float x;
    private float y;
    private float density;
    private String texture;

    EnemyType(float width, float height, float x, float y, float density, String texture) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.density = density;
        this.texture = texture;
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

    public String getTexture() {
        return texture;
    }
}