package com.gamegdx.game.control.gunsPanel;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.gamegdx.game.control.joystick.Joystick;

public class GunsPanelInputListener extends InputListener {
    GunsPanel gunsPanel;

    public GunsPanelInputListener(GunsPanel gunsPanel){
        this.gunsPanel = gunsPanel;
    }
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        gunsPanel.setTouched();
        return true;
    }

    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        gunsPanel.setUnTouched();
    }

    public void touchDragged (InputEvent event, float x, float y, int pointer) {

    }
}
