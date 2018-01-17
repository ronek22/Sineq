package com.mygdx.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.physics.PlatformUserData;

/**
 * Created by kubar on 17.01.2018.
 */

public class Platform extends GameActor {

    public Platform(Body body){
        super(body);
    }

    @Override
    public PlatformUserData getUserData() {
        return (PlatformUserData) userData;
    }

    @Override
    public void act(float delta){
        super.act(delta);
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }
}
