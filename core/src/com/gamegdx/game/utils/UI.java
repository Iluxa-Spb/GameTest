package com.gamegdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gamegdx.game.view.GameMapRenderer;

public class UI {

    private Stage stage;
    private Skin skin;
    private Label label;

    public UI(Stage stage){
        this.stage = stage;
        skin = new Skin(Gdx.files.internal("font/DigitFont/skin.json"));
        addLabelToStage();
    }

    private void addLabelToStage(){
        label = new Label("Kill ME!",skin.get("default",Label.LabelStyle.class));
        label.setAlignment(Align.center);
        label.setColor(new Color(1,0,0,0.8f));
        label.setPosition(GameMapRenderer.CAMERA_WIDTH/2, 20,Align.center);
        label.setFontScale(0.3f);
        label.setText("00:00:00");
        stage.addActor(label);
    }

    public void draw(){
        //stage.getViewport().update(800, 480);
        stage.act();
        stage.draw();
    }

    public void dispose(){
//        stage.dispose();
    }
}
