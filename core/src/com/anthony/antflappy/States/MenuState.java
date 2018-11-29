package com.anthony.antflappy.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State {
    private Texture img_bg;
    private Texture btn_play;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        img_bg = new Texture("bg.png");
        btn_play = new Texture("playbtn.png");
        System.out.println("Init Menu State");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    protected void dispose() {
        img_bg.dispose();
        btn_play.dispose();
        System.out.println("Disposed Menu State");
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(img_bg, 0, 0);
        sb.draw(btn_play, cam.position.x - (btn_play.getWidth() / 2), cam.position.y);
        sb.end();
    }
}
