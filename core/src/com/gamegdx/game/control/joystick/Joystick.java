package com.gamegdx.game.control.joystick;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gamegdx.game.utils.Assets;

import java.util.ArrayList;
import java.util.List;

public class Joystick extends Actor {

    private Sprite circle;
    private Sprite curJoystick;
    private boolean isJoystickDown = false;
    private float rad = 160;
    private float inversRad = 0;
    private static final float CUR_RADIUS = 20;

    private float curX;
    private float curY;
    private float centerX;
    private float centerY;
    //private float valueX = 0;
    //private float valueY = 0;
    private Vector2 value;
    private float rotation = 0;
    private boolean intoRad;

    private List<JoystickChangedListener> listeners = new ArrayList<JoystickChangedListener>();

    public Joystick(){
        circle = new Sprite(Assets.atlasControllers.findRegion("02"));
        curJoystick = new Sprite(Assets.atlasControllers.findRegion("46"));
        setDefaultWH();
        setDefaultXY();
        addListener(new JoystickInputListener(this));
        value = new Vector2();
        debug();
    }

    public Joystick(float x, float y){
        circle = new Sprite(Assets.atlasControllers.findRegion("02"));
        curJoystick = new Sprite(Assets.atlasControllers.findRegion("46"));
        setDefaultWH();
        setX(x);
        setY(y);
        addListener(new JoystickInputListener(this));
        value = new Vector2();
        debug();
    }

    /*public void addJoystickChangeListener(JoystickChangedListener listener){
        listener.add(listener);
    }

    public void removeJoystickChangeListener(JoystickChangedListener listener){
        listener.remove(listener);
    }

    public void clearJoystickChangeListener(){
        listener.clear();
    }*/

    public boolean getIsJoystickDown(){
        return isJoystickDown;
    }

    public float getValueX(){
        return value.x;
    }

    public float getValueY(){
        return value.y;
    }

    public void setCenterPosition(float x, float y){
        setPosition(x-rad,y-rad);
        centerX = x;
        centerY = y;
    }

    public void handleChangedListener(){
        for (JoystickChangedListener listener: listeners){
            listener.changed(value.x, value.y);
        }
    }

    public void resetCur(){
        curX = 0;
        curY = 0;
    }

    public float getRotation(){
        return value.angle()-90;
    }

    public void setDefaultWH(){
        setWidth(160);
        setHeight(160);
        rad = 80;
        inversRad = 1 / rad;
    }

    public void setDefaultXY(){
        setX(40);
        setY(40);
    }

    public void setWidth(float w){
        super.setWidth(w);
        super.setHeight(w);
        rad = w / 2;
        inversRad = 1 / rad;
    }

    public void setHeight(float h){
        super.setWidth(h);
        super.setHeight(h);
        rad = h / 2;
        inversRad = 1 / rad;
    }

    public boolean getFlagRad(){
        return intoRad;
    }
    public void setTouched(){
        isJoystickDown = true;
    }

    public void setUnTouched(){
        isJoystickDown = false;
    }

    public void changeCursor(float x, float y){
        float dx = x - rad;
        float dy = y - rad;
        float length = (float)Math.sqrt((dx * dx + dy * dy));
        if (length < rad){
            this.curX = dx;
            this.curY = dy;
        }else {
            float k = rad / length;
            this.curX = dx * k;
            this.curY = dy * k;
        }
        if(length<rad/2) intoRad = false;
        else intoRad = true;
        value.x = curX * inversRad;
        value.y = curY * inversRad;
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        Actor actor = super.hit(x,y,touchable);
        if (actor == null) return null;
        else {
            float dx = x - rad;
            float dy = x - rad;
            return  (dx * dx + dy * dy <= rad * rad) ? this:null;
        }
        //return super.hit(x, y, touchable);
    }

    /*public void setWidth(float w){
        super.setWidth(w);
        super.setHeight(w);
        rad = w / 2;
    }

    public void setHeight(float h){
        super.setHeight(h);
        super.setWidth(h);
        setHeight(h);
        rad = h / 2;
    }*/

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isJoystickDown) {
            batch.draw(circle, this.getX(), this.getY(), this.getWidth(), this.getHeight());
            if (isJoystickDown) {
                batch.draw(curJoystick, this.getX() + rad - CUR_RADIUS + curX,
                        this.getY() + rad - CUR_RADIUS + curY,
                        2 * CUR_RADIUS,
                        2 * CUR_RADIUS);
            } else {
                batch.draw(curJoystick,
                        this.getX() + rad - CUR_RADIUS,
                        this.getY() + rad - CUR_RADIUS,
                        2 * CUR_RADIUS,
                        2 * CUR_RADIUS);
            }
        }
    }
}
