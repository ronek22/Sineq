package com.mygdx.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.GameMain;
import com.mygdx.game.utils.GameEventListener;

public class AndroidLauncher extends AndroidApplication implements GameEventListener {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new GameMain(this), config);
	}

	@Override
	public void submitScore(int score) {

	}
}
