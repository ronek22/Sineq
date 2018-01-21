package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.GameMain;
import com.mygdx.game.stages.GameStage;
import com.mygdx.game.utils.AssetsManager;
import com.mygdx.game.utils.GameManager;

/**
 * Created by Marcin on 17.01.2018.
 */

public class Menu implements Screen{

    GameMain game;
    Texture playButtonActive;
    Texture playButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;
    private BitmapFont score;

    public Menu(GameMain game){
        this.game = game;
        playButtonActive = new Texture("play.png");
        playButtonInactive = new Texture("play2.png");
        exitButtonActive = new Texture("exit.png");
        exitButtonInactive = new Texture("exit2.png");
        score = AssetsManager.getSmallFont();
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        score.draw(game.getBatch(), String.format("BEST SCORE: %d", GameManager.getInstance().getScore()), 500, 440);

        if(Gdx.input.getX() < 50 + 200 && Gdx.input.getX() > 50 && Gdx.input.getY() < 270 && Gdx.input.getY() > 170) {
            game.getBatch().draw(playButtonActive, 50, 200);
            if(Gdx.input.isTouched()) {
                game.setScreen(new GameScreen(game));
            }
        }else {
            game.getBatch().draw(playButtonInactive, 50, 200);
        }
        if(Gdx.input.getX() < 550 + 200 && Gdx.input.getX() > 550 && Gdx.input.getY() < 270 && Gdx.input.getY() > 170) {
            game.getBatch().draw(exitButtonActive, 550, 200);
            if(Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        }else {
            game.getBatch().draw(exitButtonInactive, 550, 200);
        }
        game.getBatch().end();

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
        score.dispose();
    }
}
