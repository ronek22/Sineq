package com.mygdx.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.physics.UserData;

/**
 * Created by kubar on 16.01.2018.
 */

public abstract class GameActor extends Actor {
    protected Body body;
    protected UserData userData;

    public GameActor(Body body){
        this.body = body;
        this.userData = (UserData) body.getUserData();
    }

    public abstract UserData getUserData();
}
