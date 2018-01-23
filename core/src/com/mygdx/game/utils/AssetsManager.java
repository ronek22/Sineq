package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kubar on 21.01.2018.
 */

public class AssetsManager {
    private static HashMap<String, TextureRegion> texturesMap = new HashMap<String, TextureRegion>();
    private static Array<TextureRegion> runnerTextureMove = new Array<TextureRegion>();

    private static BitmapFont smallFont;
    private static BitmapFont smallestFont;
    private static BitmapFont largeFont;

    private AssetsManager() {

    }

    public static void loadAssets() {
        // Background
        texturesMap.put(Constants.BACKGROUND_ASSETS_ID,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH))));

        // Runner
        texturesMap.put(Constants.RUNNER_ASSETS_ID_MOVE_FRAME1,
                new TextureRegion((new Texture(Gdx.files.internal(Constants.RUNNER_IMAGE_MOVE_FRAME1)))));
        texturesMap.put(Constants.RUNNER_ASSETS_ID_MOVE_FRAME2,
                new TextureRegion((new Texture(Gdx.files.internal(Constants.RUNNER_IMAGE_MOVE_FRAME2)))));
        texturesMap.put(Constants.RUNNER_ASSETS_ID_MOVE_FRAME3,
                new TextureRegion((new Texture(Gdx.files.internal(Constants.RUNNER_IMAGE_MOVE_FRAME3)))));
        texturesMap.put(Constants.RUNNER_ASSETS_ID_MOVE_FRAME4,
                new TextureRegion((new Texture(Gdx.files.internal(Constants.RUNNER_IMAGE_MOVE_FRAME4)))));



        texturesMap.put(Constants.RUNNER_ASSETS_ID_JUMP,
                new TextureRegion((new Texture(Gdx.files.internal(Constants.RUNNER_JUMP_IMAGE_PATH)))));
        texturesMap.put(Constants.RUNNER_ASSETS_ID_SHOOT,
                new TextureRegion((new Texture(Gdx.files.internal(Constants.RUNNER_SHOOT_IMAGE_PATH)))));

        // FILL RunnerTextureMove
        runnerTextureMove.add(AssetsManager.getTextureRegion(Constants.RUNNER_ASSETS_ID_MOVE_FRAME1));
        runnerTextureMove.add(AssetsManager.getTextureRegion(Constants.RUNNER_ASSETS_ID_MOVE_FRAME2));
        runnerTextureMove.add(AssetsManager.getTextureRegion(Constants.RUNNER_ASSETS_ID_MOVE_FRAME3));
        runnerTextureMove.add(AssetsManager.getTextureRegion(Constants.RUNNER_ASSETS_ID_MOVE_FRAME4));
        runnerTextureMove.add(AssetsManager.getTextureRegion(Constants.RUNNER_ASSETS_ID_MOVE_FRAME3));
        runnerTextureMove.add(AssetsManager.getTextureRegion(Constants.RUNNER_ASSETS_ID_MOVE_FRAME2));


        // Platforms
        texturesMap.put(Constants.PLATFORM_DEFAULT_TEXTURE,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.PLATFORM_DEFAULT_IMAGE_PATH))));

        texturesMap.put(Constants.PLATFORM_WIDE_TEXTURE,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.PLATFORM_WIDE_IMAGE_PATH))));

        // Enemies
        texturesMap.put(Constants.RUNNING_SMALL_TEXTURE,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.RUNNING_SMALL_IMAGE_PATH))));

        texturesMap.put(Constants.RUNNING_BIG_TEXTURE,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.RUNNING_BIG_IMAGE_PATH))));

        texturesMap.put(Constants.RUNNING_WIDE_TEXTURE,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.RUNNING_WIDE_IMAGE_PATH))));

        texturesMap.put(Constants.RUNNING_LONG_TEXTURE,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.RUNNING_LONG_IMAGE_PATH))));

        texturesMap.put(Constants.FLYING_WIDE_TEXTURE,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.FLYING_WIDE_IMAGE_PATH))));

        texturesMap.put(Constants.FLYING_SMALL_TEXTURE,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.FLYING_SMALL_IMAGE_PATH))));

        // Bullet
        texturesMap.put(Constants.FALLING_ROCK_ASSETS_ID,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.FALLING_ROCK_IMAGE_PATH))));

        // Falling Rock
        texturesMap.put(Constants.BULLET_ASSETS_ID,
                new TextureRegion(new Texture(Gdx.files.internal(Constants.BULLET_IMAGE_PATH))));

        // MENU TEXTURES
        texturesMap.put("playButton",
                new TextureRegion(new Texture(Gdx.files.internal("play.png"))));

        texturesMap.put("exitButton",
                new TextureRegion(new Texture(Gdx.files.internal("exit.png"))));

        texturesMap.put("scoreButton",
                new TextureRegion(new Texture(Gdx.files.internal("best.png"))));

        texturesMap.put("titleLabel",
                new TextureRegion(new Texture(Gdx.files.internal("title.png"))));

        texturesMap.put("menuButton",
                new TextureRegion(new Texture(Gdx.files.internal("menuButton.png"))));

        // Spikes
        texturesMap.put("spikes",
                new TextureRegion(new Texture(Gdx.files.internal("spikes.png"))));


        for(Map.Entry<String, TextureRegion> entry: texturesMap.entrySet()){
            entry.getValue().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

//        tile = new TiledDrawable(getTextureRegion("spikes"));

        // Fonts
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_NAME));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 24;
        smallestFont = generator.generateFont(parameter);
        smallestFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        smallestFont.setColor(57/255f, 14/255f, 14/255f, 1f);

        parameter.size = 36;
        smallFont = generator.generateFont(parameter);
        smallFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        smallFont.setColor(57/255f, 14/255f, 14/255f, 1f);

        parameter.size = 72;
        largeFont = generator.generateFont(parameter);
        largeFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        largeFont.setColor(57/255f, 14/255f, 14/255f, 1f);

        generator.dispose();
    }

    public static TextureRegion getTextureRegion(String key){
        return texturesMap.get(key);
    }

    public static TextureRegion getAnimation(int ind) { return runnerTextureMove.get(ind);}

    public static BitmapFont getSmallFont() {
        return smallFont;
    }

    public static BitmapFont getSmallestFont() {
        return smallestFont;
    }

    public static BitmapFont getLargeFont() {
        return largeFont;
    }

    public static void dispose() {
        smallestFont.dispose();
        smallFont.dispose();
        largeFont.dispose();
        texturesMap.clear();
        runnerTextureMove.clear();
    }
}
