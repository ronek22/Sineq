package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.mygdx.game.physics.BulletUserData;
import com.mygdx.game.physics.UserData;
import com.mygdx.game.utils.AssetsManager;
import com.mygdx.game.utils.Constants;

/**
 * Created by kubar on 19.01.2018.
 */

public class Bullet extends GameActor {

    private boolean fired = false;
    private TextureRegion bulletTexture;

    public Bullet(Body body){
        super(body);
        bulletTexture = AssetsManager.getTextureRegion(Constants.BULLET_ASSETS_ID);
    }

    @Override
    public BulletUserData getUserData() {
        return (BulletUserData) userData;
    }

    @Override
    public void act(float delta){
        super.act(delta);
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }

    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);
        batch.draw(bulletTexture, screenRectangle.x, screenRectangle.y, screenRectangle.getWidth(), screenRectangle.getHeight());
    }

    public float getX (){
        return body.getPosition().x;
    }

    public Body getBody(){
        return body;
    }


}
