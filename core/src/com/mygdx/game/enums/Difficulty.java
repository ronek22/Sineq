package com.mygdx.game.enums;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.Constants;

/**
 * Created by kubar on 20.01.2018.
 */

public enum Difficulty {
    DIF_1(1, Constants.ENEMY_LINEAR_VELOCITY, Constants.RUNNER_GRAVITY_SCALE, Constants.RUNNER_JUMPING_LINEAR_IMPULSE, Constants.PLATFORM_LINEAR_VELOCITY, Constants.PLATFORM_AMOUNT, Constants.ENEMY_AMOUNT, 5 ),
    DIF_2(2, new Vector2(-11f, 0), Constants.RUNNER_GRAVITY_SCALE * 1.1f, new Vector2(0, 17f), new Vector2(-5.5f, 0), 15, 10, 10 ),
    DIF_3(3, new Vector2(-15f, 0), Constants.RUNNER_GRAVITY_SCALE * 1.1f, new Vector2(0, 17f), new Vector2(-6f, 0), 20, 15, 20 ),
    DIF_4(4, new Vector2(-18f, 0), Constants.RUNNER_GRAVITY_SCALE * 1.1f, new Vector2(0, 17f), new Vector2(-6.5f, 0), 25, 20, 40 ),
    DIF_5(5, new Vector2(-21f, 0), Constants.RUNNER_GRAVITY_SCALE * 1.5f, new Vector2(0, 19f), new Vector2(-7f, 0), 30, 25, 80 ),
    DIF_6(6, new Vector2(-25f, 0), Constants.RUNNER_GRAVITY_SCALE * 1.5f, new Vector2(0, 19f), new Vector2(-7.5f, 0), 45, 30, 120 ),
    DIF_7(7, new Vector2(-30f, 0), Constants.RUNNER_GRAVITY_SCALE * 1.7f, new Vector2(0, 20f), new Vector2(-8f, 0), 70, 40, 150 );

    private int level;
    private Vector2 enemyLinearVelocity;
    private float runnerGravityScale;
    private Vector2 runnerJumpingLinearImpulse;
    private Vector2 platformLinearVelocity;
    private int amountPlatform;
    private int amountEnemies;
    private int scoreMultiplier;

    Difficulty(int level, Vector2 enemyLinearVelocity, float runnerGravityScale, Vector2 runnerJumpingLinearImpulse,
               Vector2 platformLinearVelocity, int amountPlatform, int amountEnemies, int scoreMultiplier){
        this.level = level;
        this.enemyLinearVelocity = enemyLinearVelocity;
        this.runnerGravityScale = runnerGravityScale;
        this.runnerJumpingLinearImpulse = runnerJumpingLinearImpulse;
        this.platformLinearVelocity = platformLinearVelocity;
        this.amountPlatform = amountPlatform;
        this.amountEnemies = amountEnemies;
        this.scoreMultiplier = scoreMultiplier;
    }

    public int getLevel() {
        return level;
    }

    public Vector2 getEnemyLinearVelocity() {
        return enemyLinearVelocity;
    }

    public float getRunnerGravityScale() {
        return runnerGravityScale;
    }

    public Vector2 getRunnerJumpingLinearImpulse() {
        return runnerJumpingLinearImpulse;
    }

    public Vector2 getPlatformLinearVelocity() {
        return platformLinearVelocity;
    }

    public int getAmountPlatform() {
        return amountPlatform;
    }

    public int getAmountEnemies() {
        return amountEnemies;
    }

    public int getScoreMultiplier() {
        return scoreMultiplier;
    }
}
