package com.mygdx.game.screens;

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

    GameMain game;

    Texture playButtonActive;
    Texture playButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;

    public Menu(GameMain game){

        this.game = game;
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
        game.batch.begin();

        if(Gdx.input.getX() < 50 + 200 && Gdx.input.getX() > 50 && Gdx.input.getY() < 270 && Gdx.input.getY() > 170) {
            game.batch.draw(playButtonActive, 50, 200);
            if(Gdx.input.isTouched()) {
                game.setScreen(new GameScreen());
            }
        }else {
            game.batch.draw(playButtonInactive, 50, 200);
        }
        if(Gdx.input.getX() < 550 + 200 && Gdx.input.getX() > 550 && Gdx.input.getY() < 270 && Gdx.input.getY() > 170) {
            game.batch.draw(exitButtonActive, 550, 200);
            if(Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        }else {
            game.batch.draw(exitButtonInactive, 550, 200);
        }
        game.batch.end();

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

    }
}
