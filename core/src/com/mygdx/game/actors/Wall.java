package com.mygdx.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.physics.WallUserData;

/**
 * Created by kubar on 16.01.2018.
 */

public class Wall extends GameActor {

    public Wall(Body body){
        super(body);
    }

    public float getPosition(){
        return body.getPosition().x;
    }

    @Override
    public WallUserData getUserData() {
        return (WallUserData) userData;
    }
}
