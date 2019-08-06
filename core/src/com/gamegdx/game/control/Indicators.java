package com.gamegdx.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.gamegdx.game.model.Player;
import com.gamegdx.game.utils.Assets;
import com.gamegdx.game.view.GameMapRenderer;

public class Indicators extends Actor {
    private static float SIZE = 20f;

    private Sprite healthLogo;
    private Sprite armorLogo;
    private Label healthLabel;
    private Label armorLabel;
    private Color healthColor;
    private Skin skin;
    private BitmapFont font;

    private int health;
    private int armor;


    public Indicators(Stage stage, Player player){
        this.setStage(stage);
        skin = new Skin(Gdx.files.internal("font/DigitFont/skin.json"));

        armor = (int)player.armor;
        health = (int)player.health;
        healthColor = new Color();
        setHealthColor(health);

        armorLogo = new Sprite(Assets.atlasIndicators.findRegion("armor"));
        armorLogo.setSize(SIZE,SIZE);
        armorLogo.setPosition(110, 10);
        armorLogo.setColor(0,1,0,.5f);

        healthLogo = new Sprite(Assets.atlasIndicators.findRegion("health"));
        healthLogo.setSize(SIZE,SIZE);
        healthLogo.setPosition(10, 10);
        healthLogo.setColor(healthColor);

        healthLabel = new Label(""+health,skin.get("default",Label.LabelStyle.class));
        healthLabel.setWidth(60);
        healthLabel.setHeight(40);
        //healthLabel.setAlignment(Align.center);
        healthLabel.setColor(healthColor);
        healthLabel.setPosition(70, 20,Align.center);
        healthLabel.setFontScale(0.5f);
        healthLabel.debug();
        stage.addActor(healthLabel);

        armorLabel = new Label(""+armor,skin.get("default",Label.LabelStyle.class));
        armorLabel.setWidth(60);
        armorLabel.setHeight(40);
        //armorLabel.setAlignment(Align.left);
        armorLabel.setColor(healthColor);
        armorLabel.setPosition(150, 20,Align.left);
        armorLabel.setFontScale(0.5f);
        armorLabel.debug();
        stage.addActor(armorLabel);

        font = new BitmapFont();
        font.setColor(Color.RED);
    }

    public void setHealthColor(int health){
        healthColor.set((200-health*2)*0.01f,(health*2)*0.01f,0,0.7f);
    }

    public void render(Player player){
        armor = (int)player.armor;
        health = (int)player.health;
        healthLabel.setText(""+health);
        armorLabel.setText(""+armor);
        setHealthColor(health);
        healthLabel.setColor(healthColor);
        armorLabel.setColor(healthColor);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
        batch.draw(healthLogo, healthLogo.getX(),healthLogo.getY(),healthLogo.getWidth(),healthLogo.getHeight() );
        batch.draw(armorLogo, armorLogo.getX(),armorLogo.getY(),armorLogo.getWidth(),armorLogo.getHeight());
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, GameMapRenderer.CAMERA_HEIGHT-5);
        //font.draw(batch, "Delta: " + Gdx.graphics.getDeltaTime(), 10, GameMapRenderer.CAMERA_HEIGHT-20);
        //font.draw(batch, "Position: x:" + playerController.player.getX()+", y: "+playerController.player.getY(), 10, 40);
        //font.draw(batch, "Rotation: "+playerController.player.rotationBody , 10, 60);
    }

    public void dispose(){
        skin.dispose();
    }
}
