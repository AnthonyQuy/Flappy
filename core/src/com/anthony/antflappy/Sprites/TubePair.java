package com.anthony.antflappy.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class TubePair {
    public static int TUBE_WIDTH = 52;
    private static int FLUCTUATION = 130;
    private static int GAP = 100;
    private static int LOWEST_BOT = 120;
    private Texture topTube, bottomTube;
    private Rectangle recTop, recBot;
    private Vector2 posTopTube, posBotTube;
    private Random random;

    public TubePair(float x) {
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        random = new Random();
        posTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + GAP + LOWEST_BOT);
        recTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        posBotTube = new Vector2(x, posTopTube.y - GAP - bottomTube.getHeight());
        recBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());

    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public void reposition(float x) {
        posTopTube.set(x, random.nextInt(FLUCTUATION) + GAP + LOWEST_BOT);
        posBotTube.set(x, posTopTube.y - GAP - bottomTube.getHeight());
        recTop.setPosition(posTopTube.x, posTopTube.y);
        recBot.setPosition(posBotTube.x, posBotTube.y);
    }

    public boolean collides(Rectangle obj) {
        return obj.overlaps(recTop) || obj.overlaps(recBot);
    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }


}
