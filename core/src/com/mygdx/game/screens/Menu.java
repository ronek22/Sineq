package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
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

    private GameMain game;
    private TextureRegion playButton;
    private TextureRegion exitButton;
    private TextureRegion scoreButton;
    private TextureRegion title;
    private OrthographicCamera camera;
    private ScalingViewport scalingViewport;
    private BitmapFont score;
    private Vector3 touchPoint;

    public Menu(GameMain game){
        this.game = game;
        setUpCamera();
        scalingViewport = new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
        playButton = AssetsManager.getTextureRegion("playButton");
        scoreButton = AssetsManager.getTextureRegion("scoreButton");
        exitButton = AssetsManager.getTextureRegion("exitButton");
        title = AssetsManager.getTextureRegion("titleLabel");
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
        Gdx.gl.glClearColor(250/255f, 247/255f, 240/255f, 1);

        game.getBatch().begin();


        touchPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPoint);

        game.getBatch().draw(playButton, 80, 133);
        game.getBatch().draw(scoreButton, 298, 107);
        game.getBatch().draw(exitButton, 596, 133);
        game.getBatch().draw(title, 213, 384);

        score.draw(game.getBatch(), String.format("%d", GameManager.getInstance().getScore()), 330, 225, 140, Align.center, true);


        if(touchPoint.x < 80 + playButton.getTexture().getWidth() && touchPoint.x > 80 && touchPoint.y < 133 + playButton.getTexture().getHeight() && touchPoint.y > 133) {
            if(Gdx.input.isTouched()) {
                game.setScreen(new GameScreen(game));
            }
        }
        if(touchPoint.x < 596 + exitButton.getTexture().getWidth() && touchPoint.x > 596 && touchPoint.y < 133 + exitButton.getTexture().getHeight() && touchPoint.y > 133) {
            if(Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
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
        score.dispose();
    }
}
