package com.gamegdx.game.control.gunsPanel;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gamegdx.game.model.Gun;
import com.gamegdx.game.utils.Assets;

public class IconGun extends Actor {
    private Sprite spriteIcon;
    private Sprite rectangle;
    private Gun gun;
    private boolean isDown = false;
    private boolean isActive = false;
    private boolean isOut = false;
    private float currentX;

    public IconGun(Gun gun){
        this.gun = gun;
        spriteIcon = new Sprite(Assets.atlasGuns.findRegion(""+gun.getQuad()));
        spriteIcon.setSize(spriteIcon.getWidth()*2f,spriteIcon.getHeight()*2f);
        this.setWidth(spriteIcon.getWidth());
        this.setHeight(spriteIcon.getHeight());
        spriteIcon.setY(getY());

        addListener(new IconGunInputListener(this));

        debug();
    }

    public IconGun(Gun gun,int num){
        this.gun = gun;
        spriteIcon = new Sprite(Assets.atlasGuns.findRegion(""+gun.getQuad()));
        spriteIcon.setSize(spriteIcon.getWidth()*2f,spriteIcon.getHeight()*2f);
        this.setY(this.getY()+spriteIcon.getHeight()*num);
        this.setWidth(spriteIcon.getWidth());
        this.setHeight(spriteIcon.getHeight());
        spriteIcon.setY(getY());

        addListener(new IconGunInputListener(this));

        debug();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
        spriteIcon.setColor(Color.RED);
        batch.draw(spriteIcon, spriteIcon.getX(),spriteIcon.getY(),spriteIcon.getWidth(),spriteIcon.getHeight());
    }

    public void setIconPosition(float num){
        this.setY(0+spriteIcon.getHeight()*num);
        spriteIcon.setY(getY());
        spriteIcon.setX(getX());
    }
    public void setDefX(){this.setX(0); spriteIcon.setX(0);}

    public void setTouched(){isDown = true;}
    public void setUnTouched(){isDown = false;}
    public void setActive(){isActive = true;}
    public void setUnActive(){isActive = false;}
    public boolean getIsDown(){return isDown;}
    public boolean getIsActive(){return isActive;}

    public void changeCursor(float x){
        if (!isDown) currentX = x;
        //spriteIcon.setX(x- currentX);
        float dx = x - this.getWidth()/2;
        float length = (float)Math.sqrt((dx * dx));
        if (length < this.getWidth()/2){
            spriteIcon.setX(dx);
        }else {
            float k = this.getWidth() / length;
            spriteIcon.setX(dx * k);
        }
        if(length<this.getWidth()/2) isOut = false;
        else isOut = true;
        System.out.println(isOut);
    }
}
