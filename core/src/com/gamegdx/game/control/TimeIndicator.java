package com.gamegdx.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.gamegdx.game.view.GameMapRenderer;

import java.util.Date;

public class TimeIndicator {
    private Sprite timeLogo;
    private Label timeLabel;
    private Color color;
    private Skin skin;

    public float count;
    private String minutes;
    private String seconds;
    private String milliseconds;

    public TimeIndicator(Stage stage, int time){
        skin = new Skin(Gdx.files.internal("font/DigitFont/skin.json"));

        count = time;
        color = new Color(1,0,0,.5f);

        timeLabel = new Label(minutes+":"+seconds+":"+milliseconds,skin.get("default",Label.LabelStyle.class));
        timeLabel.setWidth(100);
        timeLabel.setHeight(20);
        timeLabel.setColor(color);
        timeLabel.setPosition(GameMapRenderer.CAMERA_WIDTH/2, 20,Align.center);
        timeLabel.setFontScale(0.3f);
        timeLabel.debug();
        stage.addActor(timeLabel);
    }

    public void update(){
        if (count>0)count -= Gdx.graphics.getRawDeltaTime();
        minutes =  String.format("%02d",(int)count/60);
        seconds =  String.format("%02d",(int)count%60);
        milliseconds = String.format("%02d",(int)((count%(int)count)*100));
        timeLabel.setText(minutes+":"+seconds+":"+milliseconds);
    }

    public void dispose(){
        skin.dispose();
    }
}
