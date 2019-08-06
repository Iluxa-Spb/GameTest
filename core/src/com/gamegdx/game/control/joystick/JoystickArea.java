package com.gamegdx.game.control.joystick;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class JoystickArea extends Group {

    private final Vector2 tmp = new Vector2();

    private class AreaListener extends InputListener{
        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            joystick.setCenterPosition(x,y);
            joystick.parentToLocalCoordinates(tmp.set(x,y));
            jListener.touchDown(event, tmp.x, tmp.y, pointer, button);

            return true;
        }

        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            joystick.parentToLocalCoordinates(tmp.set(x,y));
            jListener.touchUp(event, tmp.x, tmp.y, pointer, button);
            //joystick.setDefaultXY();
        }

        public void touchDragged (InputEvent event, float x, float y, int pointer) {
            joystick.parentToLocalCoordinates(tmp.set(x,y));
            jListener.touchDragged(event, tmp.x, tmp.y, pointer);
        }
    }
    private Joystick joystick;
    JoystickInputListener jListener;

    public JoystickArea(){
        joystick = new Joystick();
        addActor(joystick);

        setX(10);
        setY(10);
        setWidth(300);
        setHeight(400);

        jListener = new JoystickInputListener(joystick);
        addListener(new AreaListener());
        debug();
    }

    public JoystickArea(float x, float y){
        joystick = new Joystick();
        addActor(joystick);
        setX(x);
        setY(y);
        setWidth(300);
        setHeight(400);

        jListener = new JoystickInputListener(joystick);
        addListener(new AreaListener());
        debug();
    }

    public boolean getIsJoystickDown(){
        return joystick.getIsJoystickDown();
    }

    public float getValueX(){
        return joystick.getValueX();
    }
    public boolean getFlagRad(){return joystick.getFlagRad();}
    public float getValueY(){
        return joystick.getValueY();
    }
    public float getRotation(){
        return joystick.getRotation();
    }
}
