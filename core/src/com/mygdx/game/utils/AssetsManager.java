package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by kubar on 21.01.2018.
 */

public class AssetsManager {
    private static BitmapFont smallFont;
    private static BitmapFont smallestFont;
    private static BitmapFont largeFont;

    private AssetsManager() {

    }

    public static void loadAssets() {
        // Fonts
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_NAME));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 24;
        smallestFont = generator.generateFont(parameter);
        smallestFont.setColor(.5f, .5f, .5f, 1f);

        parameter.size = 36;
        smallFont = generator.generateFont(parameter);
        smallFont.setColor(.5f, .5f, .5f, 1f);

        parameter.size = 72;
        largeFont = generator.generateFont(parameter);
        largeFont.setColor(.5f, .5f, .5f, 1f);

        generator.dispose();
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
    }
}
