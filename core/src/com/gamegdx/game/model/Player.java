package com.gamegdx.game.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.gamegdx.game.utils.Assets;

import java.util.ArrayList;
import java.util.List;

public class Player{

    //States
    public enum State{
        STANDING, WALKING, RUN , DEAD, RELOAD, SWITCH
    }
    public State currentState;
    public State previousState;

    //speed move
    public static final float SPEED = 4f;
    //SIZE
    public static final float SIZE = 1f;

    public static final float FRAME_DURATION = 1f/30f;

    private Vector2 velocity = new Vector2();

    //texture player
    private Skeleton skeleton;

    //number Texture Player
    private int numberTexrurePackPlayer;
    private float legsnum = 0;
    public float rotationBody = 0f;
    public float rotationMove = 0f;
    public float health = 100;
    public float armor = 100;

    public List<Gun> listGuns;

    private Body playerBody;

    private Body createPlayerBody(float x, float y, World world){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        Body playerBody = world.createBody(def);

        CircleShape circle = new CircleShape();
        circle.setPosition(new Vector2(0,0));
        circle.setRadius(SIZE);
        Fixture f = playerBody.createFixture(circle, 70f);
        //f.setFriction(1F);
        //f.setUserData("player");
        circle.dispose();

        playerBody.setTransform(x,y,1.5f);

        return playerBody;
    }

    public Player(int n, float x, float y, World world){
        playerBody = createPlayerBody(x,y, world);
        currentState = State.STANDING;
        previousState = State.STANDING;
        numberTexrurePackPlayer = n;
        skeleton = new Skeleton(x,y,rotationBody, numberTexrurePackPlayer);

        velocity.set(0,0);
        playerBody.setLinearDamping(5);
        playerBody.setAngularDamping(5);

        listGuns = new ArrayList<Gun>();
        listGuns.add(new Gun(Assets.gunsList.getGun(0)));
        listGuns.add(new Gun(Assets.gunsList.getGun(1)));
        listGuns.add(new Gun(Assets.gunsList.getGun(7)));
    }

    public void walking(){
        currentState = State.WALKING;
        playerBody.setLinearVelocity((velocity.x*SPEED), (velocity.y*SPEED));
    }public void run(){
        currentState = State.RUN;
        playerBody.setLinearVelocity(velocity.x*SPEED, velocity.y*SPEED);
    }

    public void setVelocity(float x, float y){
        velocity.set(x,y);
    }

    public void update(){
        //legsAnimation();
        //currentState = getState();
        //playerBody.setTransform(playerBody.getPosition().x,playerBody.getPosition().y,rotationBody);
        skeleton.update(playerBody.getPosition().x,playerBody.getPosition().y,rotationBody);

    }

    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
        skeleton.draw(batch, parentAlpha);
        //batch.draw(textureRegion,legs.getX(),legs.getY());
        //batch.draw(animationLegs.getKeyFrame(elapsedTime,true),0,0 );
    }

    public float getX(){
        return playerBody.getPosition().x;
    }

    public float getY(){
        return playerBody.getPosition().y;
    }

    //public State getState(){
        //System.out.println();
        //System.out.println(velocity.y +" "+playerBody.getLinearVelocity().y);
        //if (playerBody.getLinearVelocity().x !=0||playerBody.getLinearVelocity().y !=0)
        //    return State.RUN;
        //else if (health <= 0)
        //    return State.DEAD;
        //else return State.STANDING;
    //}

    /*public TextureRegion getFrame(float dt){
        TextureRegion textureRegion;
        switch (currentState){
            case RUN:
                //textureRegion = (TextureRegion) animationLegs.getKeyFrame(elapsedTime,true);
                break;
            case WALKING:
                //textureRegion = (TextureRegion) animationLegs.getKeyFrame(elapsedTime, true);
                break;
            case RELOAD:
                //textureRegion = (TextureRegion) animationReload.getKeyFrame(elapsedTime, true);
                break;
            case STANDING:
                //textureRegion = Assets.atlasPlayer2.findRegion("2_legs",4);
                break;
            default:
                textureRegion = Assets.atlasPlayer2.findRegion("2_legs",4);
                break;
        }
        return textureRegion;
    }*/
}
