package com.gamegdx.game.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.gamegdx.game.utils.Assets;
import com.gamegdx.game.utils.Vertices;

import static com.gamegdx.game.model.Player.SIZE;
import static java.lang.Math.cos;

public class Skeleton {
    //private  final float  SIZE = 4;

    public Vertices body;
    public Vertices armL;
    public Vertices armR;
    public Vertices shoulderL;
    public Vertices shoulderR;

    private Sprite spriteBody;
    private Sprite spriteHead;
    private Sprite spriteArmL;
    private Sprite spriteArmR;
    //private Sprite arms;
    private Sprite spriteShoulderL;
    private Sprite spriteShoulderR;
    private Sprite spriteLegs;

    private Animation animationLegs;
    private Animation animationReload;
    private Animation animationSwitch;
    private Animation animationKnife;

    private int currentFrameMove = 3;
    private boolean flagMove = false;

    private float elapsedTime = 0f;

    public Skeleton(float x, float y, float angle, int n){
        //body = new Vertices(SIZE,SIZE/2,SIZE*2,SIZE);
        body = new Vertices(SIZE,SIZE/2,SIZE*2-SIZE/2,SIZE/4);
        body.setVertices(x,y,angle, 0);
        //armR = new Vertices()

        spriteBody = new Sprite(Assets.atlasPlayer.findRegion("body-"+n));
        spriteBody.setSize(SIZE*2,SIZE);
        spriteBody.setPosition(x, y);
        spriteBody.setOriginCenter();

        spriteLegs = new Sprite(Assets.atlasPlayer2.findRegion("2_legs",1));
        spriteLegs.setSize(SIZE,SIZE);
        spriteLegs.setPosition(spriteBody.getX() , spriteBody.getY() - (SIZE / 2f));
        spriteLegs.setOriginCenter();

        /*arms = new Sprite(Assets.atlasPlayer.findRegion("arm-"+n));
        arms.setSize(SIZE,SIZE);
        arms.setPosition(body.getX() , body.getY() - (SIZE / 2f));
        arms.setOriginCenter();*/

        spriteHead = new Sprite(Assets.atlasPlayer.findRegion("head-"+n));
        spriteHead.setSize(SIZE,SIZE);
        //spriteHead.setPosition(spriteBody.getX() + (SIZE/ 2f), spriteBody.getY() );
        spriteHead.setOriginCenter();

        spriteShoulderR = new Sprite(Assets.atlasPlayer.findRegion("shoulder-"+n));
        spriteShoulderR.setSize(SIZE / 2f,SIZE);
        spriteShoulderR.setPosition(spriteBody.getX()+(SIZE / 8f), spriteBody.getY() - (SIZE / 4f));
        //shoulderR.setOrigin(SIZE-(SIZE / 8f),SIZE - (SIZE / 4f));
        spriteShoulderR.setOrigin(0,0);
        //spriteShoulderR.flip(false,true);
        //shoulderR.setOriginBasedPosition(0,0);
        //shoulderR.setOriginCenter();

        spriteShoulderL = new Sprite(Assets.atlasPlayer.findRegion("shoulder-"+n));
        spriteShoulderL.setSize(SIZE / 2f,SIZE);
        //spriteShoulderL.setPosition(spriteBody.getX()-(SIZE / 8f)+SIZE*2, spriteBody.getY() - (SIZE / 4f));
        //spriteShoulderL.setOrigin(-SIZE + (SIZE / 8f),SIZE - (SIZE / 4f));
        spriteShoulderL.setOrigin(0,0);
        spriteShoulderL.flip(true,false);

        spriteArmR = new Sprite(Assets.atlasPlayer.findRegion("arm-"+n));
        spriteArmR.setSize(SIZE / 2f,SIZE);
        //spriteArmR.setPosition(spriteShoulderR.getX(), (spriteShoulderR.getY()+(SIZE/4f)-SIZE));
        spriteArmR.setOrigin(SIZE - (SIZE / 8f),(SIZE+(SIZE/2f)));

        spriteArmL = new Sprite(Assets.atlasPlayer.findRegion("arm-"+n));
        spriteArmL.setSize(SIZE / 2f - SIZE,SIZE);
        //spriteArmL.setPosition(spriteShoulderL.getX(), (spriteShoulderL.getY()+(SIZE/4f)-SIZE));
        //spriteArmL.setOrigin(-SIZE + (SIZE / 8f),(SIZE+(SIZE/2f)));
        spriteArmL.setOrigin(0,0);

        createAnimation();
        //System.out.println(body.x1+"/"+body.y1);
        //System.out.println(body.x2+"/"+body.y2);
        //System.out.println(body.x3+"/"+body.y3);
        //System.out.println(body.x4+"/"+body.y4);
    }

    public void update(float x, float y, float angle){
        body.setVertices(x,y,angle, spriteShoulderL.getWidth());

        //body.setPosition(playerBody.getPosition().x-SIZE, playerBody.getPosition().y-SIZE/2);
        spriteBody.setPosition(x, y);
        spriteBody.setRotation(angle);

        /*if (currentState == Player.State.STANDING) legs.setRotation(body.getRotation());
        else legs.setRotation(rotationMove);

        legs.setRegion(Assets.atlasPlayer2.findRegion("2_legs",currentFrameMove));
        legs.setPosition(body.getX(), body.getY());*/

        //arms.setRegion(Assets.atlasPlayer2.findRegion("2_arm",1));
        //arms.setPosition(body.getX(), body.getY());
        //arms.setRotation(body.getRotation());

        spriteHead.setPosition(spriteBody.getX() + (SIZE/ 2f), spriteBody.getY() );
        spriteHead.setRotation(spriteBody.getRotation());

        //shoulderR.setPosition(body.getX()+(SIZE / 8f), body.getY() - (SIZE / 4f));
        //spriteShoulderR.setPosition(body.origin2.x, body.origin2.y);
        spriteShoulderR.setPosition(body.x3, body.y3);
        spriteShoulderR.setRotation(spriteBody.getRotation()-0);

        spriteShoulderL.setPosition(body.x2,body.y2);
        spriteShoulderL.setRotation(spriteBody.getRotation()+0);

        spriteArmR.setPosition(spriteShoulderR.getX(), (spriteShoulderR.getY()+(SIZE/4f)-SIZE));
        spriteArmR.setRotation(spriteBody.getRotation());

        spriteArmL.setPosition(spriteShoulderL.getX(), (spriteShoulderL.getY()+(SIZE/4f)-SIZE));
        spriteArmL.setRotation(spriteBody.getRotation());

    }

    public void draw(Batch batch, float parentAlpha){
        elapsedTime+=parentAlpha;
        animationLegs.getKeyFrame(elapsedTime,true);
        TextureRegion textureRegion = (TextureRegion) animationLegs.getKeyFrame(elapsedTime,true);
        //batch.draw(getFrame(elapsedTime),legs.getX(),legs.getY(),legs.getOriginX(),legs.getOriginY(),legs.getWidth(),legs.getHeight(),legs.getScaleX(),legs.getScaleY(),legs.getRotation());
        batch.draw(spriteHead,spriteHead.getX(),spriteHead.getY(), spriteHead.getOriginX(),spriteHead.getOriginY(),spriteHead.getWidth(),spriteHead.getHeight(),spriteHead.getScaleX(),spriteHead.getScaleY(),spriteHead.getRotation());
        //batch.draw(spriteArmL,spriteArmL.getX(),spriteArmL.getY(), spriteArmL.getOriginX(),spriteArmL.getOriginY(),spriteArmL.getWidth(),spriteArmL.getHeight(),spriteArmL.getScaleX(),spriteArmL.getScaleY(),spriteArmL.getRotation());
        //batch.draw(spriteArmR,spriteArmR.getX(),spriteArmR.getY(), spriteArmR.getOriginX(),spriteArmR.getOriginY(),spriteArmR.getWidth(),spriteArmR.getHeight(),spriteArmR.getScaleX(),spriteArmR.getScaleY(),spriteArmR.getRotation());
        //textureRegion = (TextureRegion) animationReload.getKeyFrame(elapsedTime,true);
        //batch.draw(arms,arms.getX(),arms.getY(), arms.getOriginX(),arms.getOriginY(),arms.getWidth(),arms.getHeight(),arms.getScaleX(),arms.getScaleY(),arms.getRotation());
        batch.draw(spriteShoulderL,spriteShoulderL.getX(),spriteShoulderL.getY(), spriteShoulderL.getOriginX(),spriteShoulderL.getOriginY(),spriteShoulderL.getWidth(),spriteShoulderL.getHeight(),spriteShoulderL.getScaleX(),spriteShoulderL.getScaleY(),spriteShoulderL.getRotation());
        batch.draw(spriteShoulderR,spriteShoulderR.getX(),spriteShoulderR.getY(), spriteShoulderR.getOriginX(),spriteShoulderR.getOriginY(),spriteShoulderR.getWidth(),spriteShoulderR.getHeight(),spriteShoulderR.getScaleX(),spriteShoulderR.getScaleY(),spriteShoulderR.getRotation());
        batch.draw(spriteBody,spriteBody.getX(),spriteBody.getY(), spriteBody.getOriginX(),spriteBody.getOriginY(),spriteBody.getWidth(),spriteBody.getHeight(),spriteBody.getScaleX(),spriteBody.getScaleY(),spriteBody.getRotation());
    }

    public void createAnimation(){
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 1;i<6;i++)
            frames.add(new TextureRegion(Assets.atlasPlayer2.findRegion("2_legs",i)));
        animationLegs = new Animation(0.1f, frames);
        frames.clear();
        for (int i = 1;i<4;i++)
            frames.add(new TextureRegion(Assets.atlasPlayer2.findRegion("2_arm_reload",i)));
        animationReload = new Animation(0.2f,frames);
    }

    private void legsAnimation(){
        if (flagMove){
            if (currentFrameMove <6){
                currentFrameMove += 1;
            }else {
                flagMove = false;
                currentFrameMove -=1;
            }
        } else {
            if (currentFrameMove >1){
                currentFrameMove -=1;
            } else {
                flagMove = true;
                currentFrameMove +=1;
            }
        }
    }



    private Vector2 rotate(Vector2 point, float angle){
        Vector2 rotated_point = new Vector2();
        rotated_point.x = point.x * MathUtils.cosDeg(angle)-point.y*MathUtils.sinDeg(angle);
        rotated_point.y = point.x * MathUtils.sinDeg(angle)+point.y*MathUtils.cosDeg(angle);
        return  rotated_point;
    }
}
