package com.gamegdx.game.control;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gamegdx.game.utils.Assets;
import com.gamegdx.game.view.GameMapRenderer;

public class MiniMap extends Actor {
    private static final float SIZE = 128f;
    private Sprite spriteMiniMap;
    public MiniMap(Stage stage){
        spriteMiniMap = new Sprite(Assets.atlasIndicators.findRegion("radar"));
        spriteMiniMap.setSize(SIZE,SIZE);
        spriteMiniMap.setPosition(0,GameMapRenderer.CAMERA_HEIGHT-spriteMiniMap.getHeight());

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
        batch.draw(spriteMiniMap, spriteMiniMap.getX(),spriteMiniMap.getY(),spriteMiniMap.getWidth(),spriteMiniMap.getHeight());
    }
}
