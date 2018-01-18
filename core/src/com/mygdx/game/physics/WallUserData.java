package com.mygdx.game.physics;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.enums.UserDataType;
import com.mygdx.game.utils.Constants;

public class WallUserData extends UserData {

    private Vector2 linearVelocity;

    public WallUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.WALL;
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }

}