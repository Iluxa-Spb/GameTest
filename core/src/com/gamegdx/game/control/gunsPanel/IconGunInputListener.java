package com.gamegdx.game.control.gunsPanel;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class IconGunInputListener extends InputListener {
    private IconGun iconGun;
    private boolean isDragged = false;

    public IconGunInputListener(IconGun iconGun){
        this.iconGun = iconGun;
    }
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        //iconGun.setTouched();
        //iconGun.changeCursor(x);
        return true;
    }

    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        iconGun.setUnTouched();
        if(!this.isDragged) {
            iconGun.setActive();
        }
        isDragged = false;
        iconGun.setDefX();
    }

    public void touchDragged (InputEvent event, float x, float y, int pointer) {
        //iconGun.parentToLocalCoordinates();
        //iconGun.parentToLocalCoordinates(new Vector2(x,y));
        iconGun.changeCursor(x);
        iconGun.setTouched();
        isDragged = true;

    }
}
