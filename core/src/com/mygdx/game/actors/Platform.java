package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.physics.PlatformUserData;
import com.mygdx.game.utils.AssetsManager;

/**
 * Created by kubar on 17.01.2018.
 */

public class Platform extends GameActor {

    private TextureRegion platformTexture;

    public Platform(Body body){
        super(body);
        platformTexture = AssetsManager.getTextureRegion(getUserData().getTexture());
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

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);
        batch.draw(platformTexture, screenRectangle.x, screenRectangle.y, screenRectangle.getWidth(), screenRectangle.getHeight());
    }

    @Override
    public String toString(){
        return String.valueOf(getUserData().getWidth());
    }

    public Body getBody(){
        return body;
    }
}
