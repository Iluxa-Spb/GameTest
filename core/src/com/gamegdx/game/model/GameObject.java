package com.gamegdx.game.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;

public abstract class GameObject {

    Polygon bounds;
    Sprite object;

    public GameObject(TextureRegion textureRegion, float x, float y, float width, float height){
        object = new Sprite(textureRegion);
        object.setSize(width,height);
        object.setPosition(x, y);
        object.setOrigin(width / 2f, height / 2f);

        bounds = new Polygon(new float[]{0f,0f,width,0f,width,height,0f,height});
        bounds.setPosition(x,y);
        bounds.setOrigin(width/ 2f, height/ 2f);
    }


    public void draw(SpriteBatch batch){
        object.setPosition(bounds.getX(),bounds.getY());
        object.setRotation(bounds.getRotation());
        object.draw(batch);
    }
}
