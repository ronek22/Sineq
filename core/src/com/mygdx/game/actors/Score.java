package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.enums.GameState;
import com.mygdx.game.utils.AssetsManager;
import com.mygdx.game.utils.GameManager;

/**
 * Created by kubar on 21.01.2018.
 */

public class Score extends Actor {
    private float score;
    private int multiplier;
    private Rectangle bounds;
    private BitmapFont font;

    public Score(Rectangle bounds) {
        this.bounds = bounds;
        setWidth(bounds.width);
        setHeight(bounds.height);
        score = 0;
        multiplier = 5;
        font = AssetsManager.getSmallFont();
    }

    @Override
    public void act(float delta){
        super.act(delta);
        if (GameManager.getInstance().getGameState() != GameState.RUNNING){
            return;
        }

        score += multiplier * delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (getScore() == 0) {
            return;
        }
        font.draw(batch, String.format("%d", getScore()), bounds.x, bounds.y, bounds.width, Align.right, true);
    }

    public int getScore(){
        return (int) Math.floor(score);
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }
}
