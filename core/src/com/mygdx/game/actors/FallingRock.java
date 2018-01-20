package com.mygdx.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.physics.FallingRockUserData;
import java.util.Random;

/**
 * Created by mikbr on 18.01.2018.
 */

public class FallingRock extends GameActor {

    public FallingRock(Body body){
        super(body);
    }

    public float getPosition(){
        return body.getPosition().x;
    }

    @Override
    public FallingRockUserData getUserData() {  return (FallingRockUserData) userData;   }

    @Override
    public void act(float delta) {
        super.act(delta);
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }

    public static int getRandom_x(){
        Random r = new Random();
        return r.nextInt(3)+4;
    }

    public Body getBody(){
        return body;
    }


}
