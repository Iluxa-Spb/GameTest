package com.gamegdx.game.control.gunsPanel;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.gamegdx.game.model.Gun;
import com.gamegdx.game.view.GameMapRenderer;

import java.util.ArrayList;
import java.util.List;

public class IconGunGroup extends Group {
    private final Vector2 tmp = new Vector2();
    private boolean isDragged = false;

    private class GroupListener extends InputListener {
        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            //joystick.setCenterPosition(x,y);
            //iconGun.parentToLocalCoordinates(tmp.set(x,y));
            //iListener.touchDown(event, tmp.x, tmp.y, pointer, button);

            return true;
        }

        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            if (!isDragged) changeGun();
            isDragged = false;
            //iconGun.parentToLocalCoordinates(tmp.set(x,y));
            //iListener.touchUp(event, tmp.x, tmp.y, pointer, button);
            //joystick.setDefaultXY();
        }

        public void touchDragged (InputEvent event, float x, float y, int pointer) {
            isDragged = true;
            //iconGun.parentToLocalCoordinates(tmp.set(x,y));
            //iListener.touchDragged(event, tmp.x, tmp.y, pointer);
        }
    }
    private List<IconGun> iconList;
    private List<Gun> gunList;
    private List<IconGunInputListener> iListener;
    private int activeIcon;
    public boolean getIsDown(int n){
        return iconList.get(n).getIsDown();
    }

    public IconGunGroup (List<Gun> list){
        this.gunList = list;
        iconList = new ArrayList<IconGun>();
        //iListener = new ArrayList<IconGunInputListener>();

        activeIcon = 0;

        for (int i = 0; i<gunList.size();i++){
            iconList.add(new IconGun(gunList.get(i),i));
            addActor(iconList.get(i));
            //iListener.add(new IconGunInputListener(iconList.get(i)));
            //addListener(iListener.get(i));
            //iconList.get(i).addListener(iListener.get(i));
        }

        setX(GameMapRenderer.CAMERA_WIDTH-iconList.get(0).getWidth()-5);
        setY(35);
        setWidth(iconList.get(0).getWidth());
        setHeight(iconList.get(0).getHeight()*gunList.size());

        addListener(new GroupListener());
        //addListener(iListener.get(0));
        debug();
    }

    public void update(){

    }

    public void changeGun(){
        for (int i = 0; i<gunList.size();i++){
            if (iconList.get(i).getIsActive()){
                activeIcon = i;
                iconList.get(i).setUnActive();
                iconList.get(i).setIconPosition(0);
            }

        }
        int count = 1;
        for (int i = 0; i<gunList.size();i++){
            if (i != activeIcon){
                iconList.get(i).setIconPosition(count);
                count++;
            }

        }
    }

    public Gun getActiveGun(){return gunList.get(activeIcon);}
}
