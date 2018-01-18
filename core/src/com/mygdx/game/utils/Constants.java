package com.mygdx.game.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by kubar on 16.01.2018.
 */

public class Constants {

    public static final int APP_WIDTH = 800;
    public static final int APP_HEIGHT = 480;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);

    public static final float GROUND_X = 0;
    public static final float GROUND_Y = 0;
    public static final float GROUND_WIDTH = 40f;
    public static final float GROUND_HEIGHT = 2f;
    public static final float GROUND_DENSITY = 0f;

    public static final float RUNNER_X = 2;
    public static final float RUNNER_Y = GROUND_Y + GROUND_HEIGHT;
    public static final float RUNNER_WIDTH = 1f;
    public static final float RUNNER_HEIGHT = 2f;
    public static float RUNNER_DENSITY = 0.5f;
    public static final float RUNNER_GRAVITY_SCALE = 5f; // thanks to that, runner fall faster
    public static final Vector2 RUNNER_JUMPING_LINEAR_IMPULSE = new Vector2(0, 17f);

    public static final float ENEMY_X = 25f;
    public static final float ENEMY_DENSITY = RUNNER_DENSITY;
    public static final float RUNNING_SHORT_ENEMY_Y = 1.5f;
    public static final float RUNNING_LONG_ENEMY_Y = 2f;
    public static final float FLYING_ENEMY_Y = 3f;
    public static final Vector2 ENEMY_LINEAR_VELOCITY = new Vector2(-10f, 0);
    public static final float RUNNER_HIT_ANGULAR_IMPULSE = 10f;

    public static final float PLATFORM_X = 27f;
    public static final float PLATFORM_Y = 2f;
    public static final float PLATFORM_GAP = 4f;
    public static final int PLATFORM_AMOUNT = 10;
    public static final float PLATFORM_DENSITY = 0f;
    public static final float PLATFORM_FRICTION = 0f;
    public static final Vector2 PLATFORM_LINEAR_VELOCITY = new Vector2(-5f, 0);


}
