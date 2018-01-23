package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.mygdx.game.GameMain;
import com.mygdx.game.utils.AssetsManager;
import com.mygdx.game.utils.GameManager;

/**
 * Created by kubar on 21.01.2018.
 */

public class OverScreen implements Screen {
    private final int VIEWPORT_WIDTH = 800;
    private final int VIEWPORT_HEIGHT = 480;

    GameMain game;
    private BitmapFont title;
    private BitmapFont score;
    private int scoreValue;
    private TextureRegion menuButton;
    private OrthographicCamera camera;
    private ScalingViewport scalingViewport;
    private Vector3 touchPoint;

    public OverScreen(GameMain game, int scoreValue){
        this.game = game;
        setUpCamera();
        scalingViewport = new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
        this.scoreValue = scoreValue;
        menuButton = AssetsManager.getTextureRegion("menuButton");
        title = AssetsManager.getLargeFont();
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

        touchPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPoint);

        game.getBatch().begin();
        game.getBatch().draw(menuButton, 320, 107);
        title.draw(game.getBatch(), "GAME OVER", 220, 350);
        score.draw(game.getBatch(), String.format("%d", scoreValue), 330, 245, 140, Align.center, true);
        game.getBatch().end();

        if(touchPoint.x < 320 + menuButton.getTexture().getWidth() && touchPoint.x > 320 && touchPoint.y < 107 + menuButton.getTexture().getHeight() && touchPoint.y > 107) {
            if(Gdx.input.isTouched()) {
                game.setScreen(new Menu(game));
            }
        }
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
        title.dispose();
        score.dispose();
        menuButton.getTexture().dispose();
    }
}
