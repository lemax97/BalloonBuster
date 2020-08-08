package com.mygdx.game;

import com.badlogic.gdx.Game;

public class BalloonGame extends Game {
    @Override
    public void create() {
        BalloonLevel balloonLevel = new BalloonLevel(this);
        setScreen(balloonLevel);

    }
}
