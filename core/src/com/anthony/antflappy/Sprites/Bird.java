package com.anthony.antflappy.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private final int GRAVITY = -15;
    private final int MOVEMENT = 100;
    private Vector3 velocity;
    private Vector3 position;
    private Rectangle rec;
    private Texture texture;
    private Animation animation;
    private Sound sWing;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("birdanimation.png");
        animation = new Animation(new TextureRegion(texture), 3, 0.6f);
        rec = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
        sWing = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt) {
        animation.update(dt);
        if (position.y > 0)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        velocity.scl(1 / dt);
        if (position.y < 0) position.y = 0;
        rec.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return animation.getTextureRegion();
    }

    public void jump() {
        velocity.y = 250;
        sWing.play();
    }

    public Rectangle getBound() {
        return rec;
    }

    public void dispose() {
        sWing.dispose();
        texture.dispose();
    }
}
