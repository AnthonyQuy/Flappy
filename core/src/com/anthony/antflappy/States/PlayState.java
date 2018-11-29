package com.anthony.antflappy.States;

import com.anthony.antflappy.Sprites.Bird;
import com.anthony.antflappy.Sprites.TubePair;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public class PlayState extends State {
    private final int TUBE_SPACE = 125;
    private final int NUM_OF_TUBE = 4;
    private final int GOUND_OFFSET = -40;
    private Bird bird;
    private Array<TubePair> tubes;
    private Texture bg;
    private Texture ground;
    private Vector2 grPos1, grPos2;
    private Music bgMusic;


    PlayState(GameStateManager gsm) {
        super(gsm);
        System.out.println("Start Play State");
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        bgMusic.play();
        bgMusic.setVolume(0.1f);

        bird = new Bird(50, 300);
        bg = new Texture("bg.png");

        tubes = new Array<TubePair>();
        for (int i = 0; i < NUM_OF_TUBE; i++) {
            tubes.add(new TubePair(cam.viewportWidth + i * (TUBE_SPACE + TubePair.TUBE_WIDTH)));
        }

        ground = new Texture("ground.png");
        grPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GOUND_OFFSET);
        grPos2 = new Vector2(cam.position.x - cam.viewportWidth / 2 + ground.getWidth(), GOUND_OFFSET);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
        bird.update(dt);
        updateGround();
        cam.position.x = bird.getPosition().x + 80;

        if (bird.getPosition().y <= ground.getHeight() + GOUND_OFFSET) {
            gsm.set(new MenuState(gsm));
        }
        for (TubePair tubePair : tubes) {
            if (cam.position.x - (cam.viewportWidth / 2) > tubePair.getPosBotTube().x + TubePair.TUBE_WIDTH) {
                tubePair.reposition(tubePair.getPosBotTube().x + NUM_OF_TUBE * (TUBE_SPACE + TubePair.TUBE_WIDTH));
            }
            if (tubePair.collides(bird.getBound())) {
                gsm.set(new MenuState(gsm));
                break;
            }
        }

        cam.update();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);

        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for (TubePair tubePair : tubes) {
            sb.draw(tubePair.getTopTube(), tubePair.getPosTopTube().x, tubePair.getPosTopTube().y);
            sb.draw(tubePair.getBottomTube(), tubePair.getPosBotTube().x, tubePair.getPosBotTube().y);
        }
        sb.draw(ground, grPos1.x, grPos1.y);
        sb.draw(ground, grPos2.x, grPos2.y);
        sb.end();
    }

    @Override
    protected void dispose() {
        bgMusic.dispose();
        bg.dispose();
        bird.dispose();
        for (TubePair tube : tubes) {
            tube.dispose();
        }
        ground.dispose();
        System.out.println("Disposed Play State");
    }

    private void updateGround() {
        if (cam.position.x - cam.viewportWidth / 2 > grPos1.x + ground.getWidth())
            grPos1.add(ground.getWidth() * 2, 0);
        if (cam.position.x - cam.viewportWidth / 2 > grPos2.x + ground.getWidth())
            grPos2.add(ground.getWidth() * 2, 0);

    }
}
