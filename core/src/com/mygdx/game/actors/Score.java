package com.mygdx.game.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.enums.GameState;
import com.mygdx.game.utils.GameManager;

/**
 * Created by kubar on 21.01.2018.
 */

public class Score extends Actor {
    private float score;
    private int multiplier;

    public Score() {
        score = 0;
        multiplier = 5;
    }

    @Override
    public void act(float delta){
        super.act(delta);
        if (GameManager.getInstance().getGameState() != GameState.RUNNING){
            return;
        }

        score += multiplier * delta;
    }

//    @Override
//    public void draw(){
//
//    }

    public int getScore(){
        return (int) Math.floor(score);
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }
}
