package com.E.smash.ui;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AnimatedActor extends Actor {
    private final Animation<TextureRegion> animation;
    private float stateTime = 0;
    private boolean looping = true;

    public AnimatedActor(Animation<TextureRegion> animation) {
        this.animation = animation;
        TextureRegion frame = animation.getKeyFrame(0);
        setSize(frame.getRegionWidth(), frame.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, looping);
        batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight());
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }
}
