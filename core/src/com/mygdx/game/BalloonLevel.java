package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;


public class BalloonLevel extends BaseScreen {

	private BaseActor background;

	private float spawnTimer;
	private float spawnInterval;

	private int popped;
	private int escaped;
	private int clickCount;

	private Label poppedLabel;
	private Label escapedLabel;
	private Label hitRatioLabel;

	// game world dimensions
	final int mapWidth = 640;
	final int mapHeight = 480;

	public BalloonLevel(Game game) {
		super(game);
	}

	@Override
	public void create () {
		background = new BaseActor();
		background.setTexture(new Texture("sky.jpg"));
		background.setPosition(0,0);
		mainStage.addActor(background);

		spawnTimer = 0;
		spawnInterval = 0.5f;

		//set up user interface
		BitmapFont font = new BitmapFont();
		LabelStyle style = new LabelStyle(font, Color.NAVY);

		popped = 0;
		poppedLabel = new Label("Popped: 0", style);
		poppedLabel.setFontScale(2);
		poppedLabel.setPosition(20, 440);
		uiStage.addActor(poppedLabel);

		escaped = 0;
		escapedLabel = new Label("Escaped: 0", style);
		escapedLabel.setFontScale(2);
		escapedLabel.setPosition(220, 440);
		uiStage.addActor(escapedLabel);

		clickCount = 0;
		hitRatioLabel = new Label("Hit Ratio: ---", style);
		hitRatioLabel.setFontScale(2);
		hitRatioLabel.setPosition(420, 440);
		uiStage.addActor(hitRatioLabel);
	}

	@Override
	public void update(float dt) {
		spawnTimer += dt;
		//check time for next balloon spawn
		if (spawnTimer > spawnInterval){
			spawnTimer -= spawnInterval;
			final Baloon baloon = new Baloon();
			baloon.addListener(
					new InputListener(){
						public boolean touchDown (InputEvent inputEvent, float x, float y, int pointer, int button){
							popped++;
							baloon.remove();
							return true;
						}
					});
			mainStage.addActor(baloon);
		}

		//remove balloons that are off-screen
		for (Actor actor: mainStage.getActors()){
			if (actor.getX() > mapWidth || actor.getY() > mapHeight){
				escaped++;
				actor.remove();
			}
		}

		//update user interface
		poppedLabel.setText("Popped: " + popped);
		escapedLabel.setText("Escaped: " + escaped);
		if (clickCount > 0){
			int percent = (int)(100.0f * popped / clickCount);
			hitRatioLabel.setText("Hit Ratio: " + percent + "%");
		}
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button){
		clickCount++;
		return false;
	}
}
