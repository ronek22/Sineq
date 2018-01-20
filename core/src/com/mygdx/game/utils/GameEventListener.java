package com.mygdx.game.utils;

/**
 * Created by kubar on 20.01.2018.
 */

public interface GameEventListener {
    /**
     * Submits a given score. Used every time the game is over
     *
     * @param score
     */
    public void submitScore(int score);
}
