package com.mygdx.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.physics.RunnerUserData;

/**
 * Created by kubar on 16.01.2018.
 */

public class Runner extends GameActor {

    private boolean jumping;
    private boolean hit;
    private boolean onPlatform;
    private boolean moving;

    public Runner(Body body){
        super(body);
    }

    @Override
    public RunnerUserData getUserData() {
        return (RunnerUserData) userData;
    }

    public void jump() {
        if(!jumping){
            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
            jumping = true;
        }
    }

    public void move() {
        if(!moving){
                body.applyLinearImpulse(getUserData().getMoveRightLinearImpulse(), body.getWorldCenter(), true);
                moving = true;
        }
        else{
                body.applyLinearImpulse(getUserData().getMoveLeftLinearImpulse(), body.getWorldCenter(), true);
                moving = false;
        }
    }

    public void landed() {
        jumping = false;
    }

    public float getPosition(){
        return body.getPosition().x;
    }


    public void hit() {
        body.applyAngularImpulse(getUserData().getHitAngularImpulse(), true);
        hit = true;
    }

    public boolean isHit() {
        return hit;
    }


    public void platform(){
        onPlatform = true;
    }

    public boolean isOnPlatform() { return onPlatform; }




}
