package com.anthony.antflappy.States;

import com.anthony.antflappy.MyGame;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected State(GameStateManager gsm) {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, MyGame.WIDTH / 2, MyGame.HEIGHT / 2);
        mouse = new Vector3();
    }

    protected abstract void handleInput();

    protected abstract void update(float dt);

    protected abstract void render(SpriteBatch sb);

    protected abstract void dispose();
}
