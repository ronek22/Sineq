package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.Menu;
import com.mygdx.game.utils.AssetsManager;
import com.mygdx.game.utils.GameEventListener;
import com.mygdx.game.utils.GameManager;

public class GameMain extends Game {

	private SpriteBatch batch;

	public GameMain(GameEventListener listener){
		GameManager.getInstance().setGameEventListener(listener);
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		AssetsManager.loadAssets();
		setScreen(new Menu(this));
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void render(){
		super.render();
	}
}
