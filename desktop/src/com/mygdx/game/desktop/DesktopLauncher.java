package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.GameMain;
import com.mygdx.game.utils.Constants;
import com.mygdx.game.utils.GameEventListener;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.APP_WIDTH;
		config.height = Constants.APP_HEIGHT;
		
		new LwjglApplication(new GameMain(new GameEventListener() {
			@Override
			public void submitScore(int score) {
				Gdx.app.log(GameEventListener.class.getSimpleName(), "submitScore");
			}
		}), config);
	}
}
