package com.mygdx.game.physics;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.enums.UserDataType;
import com.mygdx.game.utils.Constants;

/**
 * Created by kubar on 17.01.2018.
 */

public class RunnerUserData extends UserData {
    private Vector2 jumpingLinearImpulse;
    private Vector2 movingRightLinearImpulse;
    private Vector2 movingLeftLinearImpulse;

    public RunnerUserData(float width, float height) {
        super(width, height);
        jumpingLinearImpulse = Constants.RUNNER_JUMPING_LINEAR_IMPULSE;
        movingRightLinearImpulse = Constants.RUNNER_MOVING_RIGHT_LINEAR_IMPULSE;
        movingLeftLinearImpulse = Constants.RUNNER_MOVING_LEFT_LINEAR_IMPULSE;
        userDataType = UserDataType.RUNNER;
    }

    public Vector2 getJumpingLinearImpulse() {
        return jumpingLinearImpulse;
    }
    public Vector2 getMoveRightLinearImpulse() {
        return movingRightLinearImpulse;
    }
    public Vector2 getMoveLeftLinearImpulse() {
        return movingLeftLinearImpulse;
    }

    public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse){
        this.jumpingLinearImpulse = jumpingLinearImpulse;
    }
    public float getHitAngularImpulse() {
        return Constants.RUNNER_HIT_ANGULAR_IMPULSE;
    }


}
