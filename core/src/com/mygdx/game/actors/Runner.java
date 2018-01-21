package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.enums.Difficulty;
import com.mygdx.game.physics.RunnerUserData;
import com.mygdx.game.utils.AssetsManager;
import com.mygdx.game.utils.Constants;

/**
 * Created by kubar on 16.01.2018.
 */

public class Runner extends GameActor {

    private boolean jumping;
    private boolean hit;
    private boolean onPlatform;
    private boolean moving;
    private TextureRegion runnerTexture;

    public Runner(Body body){
        super(body);
        runnerTexture = AssetsManager.getTextureRegion(Constants.RUNNER_ASSETS_ID);
    }

    @Override
    public RunnerUserData getUserData() {
        return (RunnerUserData) userData;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);
        batch.draw(runnerTexture, screenRectangle.x, screenRectangle.y, screenRectangle.getWidth(), screenRectangle.getHeight());
    }


    public void jump() {
        if(!jumping){
            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
            jumping = true;
            onPlatform = false;
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

    public float getX(){
        return body.getPosition().x;
    }
    public float getY() { return body.getPosition().y; }


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

    public void onDifficultyChange(Difficulty newDifficulty) {
        setGravityScale(newDifficulty.getRunnerGravityScale());
        getUserData().setJumpingLinearImpulse(newDifficulty.getRunnerJumpingLinearImpulse());
    }

    public void setGravityScale(float gravityScale){
        body.setGravityScale(gravityScale);
        body.resetMassData();
    }

    public Body getBody(){
        return body;
    }



}
