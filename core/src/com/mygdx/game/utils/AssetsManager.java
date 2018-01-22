package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.HashMap;

/**
 * Created by kubar on 21.01.2018.
 */

public class AssetsManager {
    private static HashMap<String, TextureRegion> texturesMap = new HashMap<String, TextureRegion>();
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
        texturesMap.put(Constants.RUNNER_ASSETS_ID,
                new TextureRegion((new Texture(Gdx.files.internal(Constants.RUNNER_IMAGE_PATH)))));


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

        // Bullet
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

        // Fonts
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_NAME));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 24;
        smallestFont = generator.generateFont(parameter);
        smallestFont.setColor(57/255f, 14/255f, 14/255f, 1f);

        parameter.size = 36;
        smallFont = generator.generateFont(parameter);
        smallFont.setColor(57/255f, 14/255f, 14/255f, 1f);

        parameter.size = 72;
        largeFont = generator.generateFont(parameter);
        largeFont.setColor(57/255f, 14/255f, 14/255f, 1f);

        generator.dispose();
    }

    public static TextureRegion getTextureRegion(String key){
        return texturesMap.get(key);
    }

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
    }
}
