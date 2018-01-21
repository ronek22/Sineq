package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.mygdx.game.GameMain;
import com.mygdx.game.stages.GameStage;
import com.mygdx.game.utils.AssetsManager;
import com.mygdx.game.utils.Constants;
import com.mygdx.game.utils.GameManager;

/**
 * Created by Marcin on 17.01.2018.
 */

public class Menu implements Screen{

    private final int VIEWPORT_WIDTH = 800;
    private final int VIEWPORT_HEIGHT = 480;

    GameMain game;
    Texture playButtonActive;
    Texture playButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;
    private OrthographicCamera camera;
    private ScalingViewport scalingViewport;
    private BitmapFont score;

    public Menu(GameMain game){
        this.game = game;
        setUpCamera();
        scalingViewport = new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
        playButtonActive = new Texture("play.png");
        playButtonInactive = new Texture("play2.png");
        exitButtonActive = new Texture("exit.png");
        exitButtonInactive = new Texture("exit2.png");
        score = AssetsManager.getSmallFont();
    }

    private void setUpCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
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
        camera.update();

    }

    @Override
    public void resize(int width, int height) {
        scalingViewport.update(width, height);
        game.getBatch().setProjectionMatrix(camera.combined);
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
