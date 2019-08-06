package com.gamegdx.game.control;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gamegdx.game.control.gunsPanel.GunsPanel;
import com.gamegdx.game.control.gunsPanel.IconGunGroup;
import com.gamegdx.game.control.joystick.JoystickArea;
import com.gamegdx.game.model.Player;
import com.gamegdx.game.view.GameMapRenderer;

public class PlayerController {

    private float speed = 0;
    public Stage stageControllers;
    public JoystickArea joystickMove;
    public JoystickArea joystickRotation;
    public Player player;
    public Indicators indicators;
    public TimeIndicator timeIndicator;
    public MiniMap miniMap;
    public GunsPanel gunsPanel;
    public IconGunGroup iconGunGroup;

    public PlayerController(Player player){
        this.player = player;
        joystickMove = new JoystickArea();
        joystickRotation = new JoystickArea();
        joystickRotation.setPosition(GameMapRenderer.CAMERA_WIDTH-joystickRotation.getWidth()-60,10);
        stageControllers = new Stage(new FillViewport(GameMapRenderer.CAMERA_WIDTH,GameMapRenderer.CAMERA_HEIGHT));
        stageControllers.addActor(joystickMove);
        stageControllers.addActor(joystickRotation);
        miniMap = new MiniMap(stageControllers);
        stageControllers.addActor(miniMap);
        indicators = new Indicators(stageControllers, this.player);
        stageControllers.addActor(indicators);
        timeIndicator = new TimeIndicator(stageControllers, 120);
        gunsPanel = new GunsPanel();
        stageControllers.addActor(gunsPanel);
        iconGunGroup = new IconGunGroup(player.listGuns);
        stageControllers.addActor(iconGunGroup);
        //stageControllers.addActor(gunsPanel);
    }

    public void handle(){

        //playerBounds.setPosition(playerBounds.getX()/* + MathUtils.cosDeg(playerBounds.getRotation())*/,
        //        playerBounds.getY()/* + MathUtils.sinDeg(playerBounds.getRotation())*/);
    }

    public void update(){
        if (joystickMove.getIsJoystickDown()){
            player.setVelocity(joystickMove.getValueX(),joystickMove.getValueY());
            if (joystickMove.getFlagRad())player.run();
            else player.walking();
            player.rotationMove = joystickMove.getRotation();
            player.currentState = Player.State.RUN;
        } else player.currentState = Player.State.STANDING;
        if (joystickRotation.getIsJoystickDown()){
            player.rotationBody =joystickRotation.getRotation();
        }
        indicators.render(player);
        timeIndicator.update();
        //if (gunsPanel.getIsDown()) {gunsPanel.setAmmo(30,30);}
        //else gunsPanel.setDefaultAmmo();
        gunsPanel.setAmmo(iconGunGroup.getActiveGun().currentClip,iconGunGroup.getActiveGun().currentAmmo);
    }

    public void drow(SpriteBatch batch, float delta){
        stageControllers.act();
        stageControllers.draw();
    }

    public void dispose(){

    }
}
