package com.gamegdx.game.control.joystick;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.gamegdx.game.control.joystick.Joystick;

public class JoystickInputListener extends InputListener {

    Joystick joystick;

    public JoystickInputListener(Joystick joystick){
        this.joystick = joystick;
    }
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        joystick.setTouched();
        joystick.changeCursor(x,y);
        return true;
    }

    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        joystick.setUnTouched();
    }

    public void touchDragged (InputEvent event, float x, float y, int pointer) {
        joystick.changeCursor(x,y);
        if (joystick.getIsJoystickDown())
            joystick.handleChangedListener();
    }

}
