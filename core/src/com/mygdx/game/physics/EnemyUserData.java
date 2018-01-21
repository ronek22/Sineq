package com.mygdx.game.physics;

import com.badlogic.gdx.math.Vector2;
        import com.mygdx.game.enums.UserDataType;
        import com.mygdx.game.utils.Constants;

public class EnemyUserData extends UserData {

    private Vector2 linearVelocity;
    private String texture;

    public EnemyUserData(float width, float height, String texture) {
        super(width, height);
        userDataType = UserDataType.ENEMY;
        linearVelocity = Constants.ENEMY_LINEAR_VELOCITY;
        this.texture = texture;
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }

    public String getTexture() {
        return texture;
    }
}