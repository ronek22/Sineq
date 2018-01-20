package com.mygdx.game.physics;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.enums.UserDataType;
import com.mygdx.game.utils.Constants;

/**
 * Created by kubar on 20.01.2018.
 */

public class SpikeGroundUserData extends UserData {

    private Vector2 linearVelocity;

    public SpikeGroundUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.SPIKE_GROUND;
        linearVelocity = Constants.PLATFORM_LINEAR_VELOCITY;
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        this.linearVelocity = linearVelocity;
    }


    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }

}
