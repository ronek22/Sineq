package com.mygdx.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.physics.SpikeGroundUserData;

/**
 * Created by kubar on 20.01.2018.
 */

public class SpikeGround extends GameActor {
    public SpikeGround(Body body){
        super(body);
    }

    public float getPosition(){
        return body.getPosition().x;
    }

    @Override
    public SpikeGroundUserData getUserData() {
        return (SpikeGroundUserData) userData;
    }

    @Override
    public void act(float delta){
        super.act(delta);
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }

    public Body getBody(){
        return body;
    }
}
