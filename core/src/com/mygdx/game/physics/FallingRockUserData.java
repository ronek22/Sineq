package com.mygdx.game.physics;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.enums.UserDataType;
import com.mygdx.game.utils.Constants;

public class FallingRockUserData extends UserData {

    private Vector2 linearVelocity;

    public FallingRockUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.FALLING_ROCK;
        linearVelocity = Constants.FALLING_ROCK_LINEAR_VELOCITY;
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }

}