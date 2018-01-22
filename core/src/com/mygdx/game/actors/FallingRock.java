package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.physics.FallingRockUserData;
import com.mygdx.game.utils.AssetsManager;
import com.mygdx.game.utils.Constants;

import java.util.Random;

/**
 * Created by mikbr on 18.01.2018.
 */

public class FallingRock extends GameActor {

    private TextureRegion rockTexture;
    public FallingRock(Body body){
        super(body);
        rockTexture = AssetsManager.getTextureRegion(Constants.FALLING_ROCK_ASSETS_ID);
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

    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);
        batch.draw(rockTexture, screenRectangle.x, screenRectangle.y, screenRectangle.getWidth(), screenRectangle.getHeight());
    }


}
