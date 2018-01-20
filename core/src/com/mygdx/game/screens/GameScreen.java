package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameMain;
import com.mygdx.game.stages.GameStage;

/**
 * Created by kubar on 16.01.2018.
 */

public class GameScreen implements Screen {


    private GameStage stage;
    GameMain game;
    private float Timer;
    Texture Liczba1;
    Texture Liczba2;
    Texture Liczba3;
    Texture Liczba4;
    Texture Liczba5;
    private int Time = 0;
    private BitmapFont font;
    private SpriteBatch batch;

    public GameScreen(GameMain game) {
        this.game = game;
        Liczba1 = new Texture("play.png");
        Liczba1 = new Texture("play.png");
        Liczba1 = new Texture("play.png");
        Liczba1 = new Texture("play.png");
        Liczba1 = new Texture("play.png");
        stage = new GameStage(game);

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
    }
    @Override
    public void render(float delta) {
        //Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the stage
        stage.draw();
        stage.getBatch().begin();
        Timer += Gdx.graphics.getDeltaTime();
        if(Timer > 1){
            Time++;
            System.out.println(Time);
            Timer=0;

        }
        batch.begin();
        font.draw(batch, String.valueOf(Time), 250, 400); //you can change the position as you like
        batch.end();

        stage.getBatch().end();

        //stage.draw(Time, 50, 200);
        stage.act(delta);

    }

    @Override
    public void resize(int width, int height) {

    }



    @Override
    public void show() {

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

