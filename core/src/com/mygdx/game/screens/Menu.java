package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameMain;
import com.mygdx.game.stages.GameStage;

/**
 * Created by Marcin on 17.01.2018.
 */

public class Menu implements Screen{

    Texture playButtonActive;
    Texture playButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;

    public Menu(){

        playButtonActive = new Texture("play.png");
        playButtonInactive = new Texture("play2.png");
        exitButtonActive = new Texture("exit.png");
        exitButtonInactive = new Texture("exit2.png");
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            if(Gdx.input.isTouched()) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        playButtonActive.dispose();
        playButtonInactive.dispose();
        exitButtonActive.dispose();
        exitButtonInactive.dispose();
    }
}
