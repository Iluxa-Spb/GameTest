package com.gamegdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gamegdx.game.control.PlayerController;

public class Man extends GameObject {

    PlayerController playerController;
    public Man(TextureRegion textureRegion, float x, float y, float width, float height) {
        super(textureRegion, x, y, width, height);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        playerController.handle();
    }

    public float getX(){
        return getX();
    }

    public float getY(){
        return getY();
    }
}
