package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.mygdx.game.physics.SpikeGroundUserData;
import com.mygdx.game.utils.AssetsManager;

/**
 * Created by kubar on 20.01.2018.
 */

public class SpikeGround extends GameActor {
    private Texture spikes;

    public SpikeGround(Body body){
        super(body);
        spikes = AssetsManager.getTextureRegion("spikes").getTexture();
        spikes.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.ClampToEdge);
    }


    @Override
    public SpikeGroundUserData getUserData() {
        return (SpikeGroundUserData) userData;
    }

    @Override
    public void act(float delta){
        super.act(delta);
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);
        batch.draw(spikes, screenRectangle.x, screenRectangle.y, 0, 0, (int)screenRectangle.getWidth(), (int)screenRectangle.getHeight());
    }

    public Body getBody(){
        return body;
    }
}
