package com.gamegdx.game.control.gunsPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.gamegdx.game.utils.Assets;
import com.gamegdx.game.view.GameMapRenderer;

public class GunsPanel extends Actor {
    private Label ammoLabel;
    private Skin skin;
    private Sprite spritePanel;
    private Sprite spriteLogo;
    private boolean isDown = false;

    public GunsPanel(){
        skin = new Skin(Gdx.files.internal("font/DigitFont/skin.json"));
        spritePanel = new Sprite(Assets.atlasIndicators.findRegion("ammo"));
        spritePanel.setSize(spritePanel.getWidth()*1.5f,spritePanel.getHeight()*1.5f);
        spritePanel.setPosition(GameMapRenderer.CAMERA_WIDTH-spritePanel.getWidth(),0);
        spriteLogo = new Sprite(Assets.atlasIndicators.findRegion("logo"));
        spriteLogo.setSize(spriteLogo.getWidth()/1.5f,spriteLogo.getHeight()/1.5f);
        spriteLogo.setPosition(spritePanel.getX()+(spritePanel.getWidth()/2-spriteLogo.getWidth()/2),spritePanel.getY()+10);

        this.setBounds(GameMapRenderer.CAMERA_WIDTH-spritePanel.getWidth(), 0, spritePanel.getWidth(),spritePanel.getHeight());

        ammoLabel = new Label("-/-",skin.get("default",Label.LabelStyle.class));
        ammoLabel.setWidth(60);
        ammoLabel.setHeight(40);
        ammoLabel.setColor(Color.WHITE);
        ammoLabel.setPosition(this.getX()+spritePanel.getWidth()/4,this.getY()+spritePanel.getHeight()/2-3, Align.left);
        ammoLabel.setFontScale(0.2f);
        ammoLabel.debug();

        addListener(new GunsPanelInputListener(this));
        debug();
    }

    public void setAmmo(int holder, int ammo){
        ammoLabel.setText(holder+"/"+ammo);
    }

    public void setDefaultAmmo(){
        ammoLabel.setText("-/-");
    }

    public void setUnTouched(){
        isDown = false;
    }

    public void setTouched(){isDown = true; }

    public boolean getIsDown() { return isDown; }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        //setAmmo(30,30);
        return super.hit(x, y, touchable);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
        batch.draw(spritePanel, spritePanel.getX(), spritePanel.getY(),spritePanel.getWidth(),spritePanel.getHeight());
        batch.draw(spriteLogo, spriteLogo.getX(),spriteLogo.getY(),spriteLogo.getWidth(),spriteLogo.getHeight());
        ammoLabel.draw(batch,parentAlpha);
    }
}
