package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Baloon extends BaseActor {
    public float speed;
    public float amplitude;
    public float oscillation;
    public float initialY;
    public float time;
    public int offsetX;

    public Baloon() {
        this.speed = 80 * MathUtils.random(0.5f, 2.0f);
        this.amplitude = 50 * MathUtils.random(0.5f, 2.0f);
        this.oscillation = 0.01f * MathUtils.random(0.5f, 2.0f);
        this.initialY = 120 * MathUtils.random(0.5f, 2.0f);
        this.time = 0;
        this.offsetX = -100;
        setTexture(new Texture("red-balloon.png"));

        // initial spawn location off-screen
        setX(offsetX);
    }

    public void act(float dt){
        super.act(dt);
        time += dt;
        //set starting location to left of window
        float xPos = speed * time + offsetX;
        float yPos = amplitude * MathUtils.sin(oscillation * xPos) + initialY;
        setPosition(xPos, yPos);
    }
}
