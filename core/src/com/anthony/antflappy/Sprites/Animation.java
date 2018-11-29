package com.anthony.antflappy.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int numOfFrame;
    private int currentIndex;

    public Animation(TextureRegion region, int numOfFrame, float circleTime) {
        int frameWith = region.getRegionWidth() / numOfFrame;
        this.numOfFrame = numOfFrame;
        frames = new Array<TextureRegion>();
        for (int i = 0; i < numOfFrame; i++) {
            frames.add(new TextureRegion(region, i * frameWith, 0, frameWith, region.getRegionHeight()));
        }
        maxFrameTime = circleTime / numOfFrame;
        currentIndex = 0;
    }

    public void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime >= maxFrameTime) {
            currentIndex++;
            currentFrameTime = 0;
        }
        if (currentIndex >= numOfFrame) {
            currentIndex = 0;
        }
    }

    public TextureRegion getTextureRegion() {
        return frames.get(currentIndex);
    }
}
