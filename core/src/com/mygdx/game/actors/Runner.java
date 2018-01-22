package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
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
    private boolean shoot;
    private int frame_counter = 0;
    private int move_counter = 0;
    private int counter = 0;
    private TextureRegion runnerTextureJump;
    private TextureRegion runnerTextureShoot;
    private Array<TextureRegion> runnerTextureMove = new Array<TextureRegion>();

    public Runner(Body body){
        super(body);

        runnerTextureJump = AssetsManager.getTextureRegion(Constants.RUNNER_ASSETS_ID_JUMP);
        runnerTextureShoot = AssetsManager.getTextureRegion(Constants.RUNNER_ASSETS_ID_SHOOT);
        runnerTextureMove.add(AssetsManager.getTextureRegion(Constants.RUNNER_ASSETS_ID_MOVE_FRAME1));
        runnerTextureMove.add(AssetsManager.getTextureRegion(Constants.RUNNER_ASSETS_ID_MOVE_FRAME2));
        runnerTextureMove.add(AssetsManager.getTextureRegion(Constants.RUNNER_ASSETS_ID_MOVE_FRAME3));
        runnerTextureMove.add(AssetsManager.getTextureRegion(Constants.RUNNER_ASSETS_ID_MOVE_FRAME4));
        runnerTextureMove.add(AssetsManager.getTextureRegion(Constants.RUNNER_ASSETS_ID_MOVE_FRAME3));
        runnerTextureMove.add(AssetsManager.getTextureRegion(Constants.RUNNER_ASSETS_ID_MOVE_FRAME2));

    }

    @Override
    public RunnerUserData getUserData() {
        return (RunnerUserData) userData;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);
        if(shoot){
            if(counter > 20) {
                shoot = false;
                counter = 0;
            }
            else
            {
                batch.draw(runnerTextureShoot, screenRectangle.x, screenRectangle.y, screenRectangle.getWidth(), screenRectangle.getHeight());
                counter++;
            }

        }else if(jumping)
        {
            batch.draw(runnerTextureJump, screenRectangle.x, screenRectangle.y, screenRectangle.getWidth(), screenRectangle.getHeight());
        }
        else
        {
            batch.draw(runnerTextureMove.get(move_counter), screenRectangle.x, screenRectangle.y, screenRectangle.getWidth(), screenRectangle.getHeight());
            if(frame_counter > 5) {
                move_counter++;
                frame_counter = 0;
                if (move_counter == 5) move_counter = 0;
            }
            else frame_counter++;
        }


    }

    public void shoot(){
        shoot = true;
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
