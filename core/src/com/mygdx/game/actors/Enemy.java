package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.physics.EnemyUserData;
import com.mygdx.game.utils.AssetsManager;

public class Enemy extends GameActor {

    private TextureRegion enemyTexture;

    public Enemy(Body body) {
        super(body);
        enemyTexture = AssetsManager.getTextureRegion(getUserData().getTexture());
    }

    @Override
    public EnemyUserData getUserData() {
        return (EnemyUserData) userData;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);
        batch.draw(enemyTexture, screenRectangle.x, screenRectangle.y, screenRectangle.getWidth(), screenRectangle.getHeight());
    }

    public Body getBody(){
        return body;
    }

}