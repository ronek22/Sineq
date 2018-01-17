package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.Menu;

public class GameMain extends Game {

	public SpriteBatch batch;
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new Menu(this));
	}

	public void render(){
		super.render();
	}
}
