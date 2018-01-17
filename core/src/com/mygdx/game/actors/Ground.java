package com.mygdx.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.physics.GroundUserData;

/**
 * Created by kubar on 16.01.2018.
 */

public class Ground extends GameActor {

    public Ground(Body body){
        super(body);
    }

    @Override
    public GroundUserData getUserData() {
        return (GroundUserData) userData;
    }
}
