package com.gamegdx.game.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import static com.gamegdx.game.model.Player.SIZE;

public class Vertices {
    private float cos;
    private float sin;
    private float localX; //originX
    private float localY; //originY
    private float localX2;
    private float localY2;
    private float worldOriginX;
    private float worldOriginY;
    private float localXCos;
    private float localXSin;
    private float localYCos;
    private float localYSin;
    private float localX2Cos;
    private float localX2Sin;
    private float localY2Cos;
    private float localY2Sin;
    public float x1;
    public float y1;
    public float x2;
    public float y2;
    public float x3;
    public float y3;
    public float x4;
    public float y4;
    public Vector2 origin1;
    public Vector2 origin2;

    public Vertices(float originX, float originY, float width, float height) {
        localX = -originX; //originX
        localY = -originY; //originY
        localX2 = localX + width;
        localY2 = localY + height;
        origin1 = new Vector2();
        origin2 = new Vector2();

    }

    public void setVertices(float x, float y, float angle, float position){
        worldOriginX = x - localX;
        worldOriginY = y - localY;
        if (angle != 0) {
            cos = MathUtils.cosDeg(angle);
            sin = MathUtils.sinDeg(angle);
            localXCos = localX * cos;
            localXSin = localX * sin;
            localYCos = localY * cos;
            localYSin = localY * sin;
            localX2Cos = localX2 * cos;
            localX2Sin = localX2 * sin;
            localY2Cos = localY2 * cos;
            localY2Sin = localY2 * sin;

            x1 = localXCos - localYSin + worldOriginX;
            y1 = localYCos + localXSin + worldOriginY;

            x2 = localXCos - localY2Sin + worldOriginX;
            y2 = localY2Cos + localXSin + worldOriginY;

            x3 = localX2Cos - localY2Sin + worldOriginX;
            y3 = localY2Cos + localX2Sin + worldOriginY;

            //origin1.x = ((localXCos - localYSin)+(localX2Cos - localY2Sin))/2+worldOriginX;
            //origin1.y = ((localYCos + localXSin)+(localY2Cos + localXSin))/2+worldOriginX;

            x4 = x1 + (x3 - x2);
            y4 = y3 - (y2 - y1);
        } else {
            x1 = localX + worldOriginX;
            y1 = localY + worldOriginY;
            x2 = localX + worldOriginX;
            y2 = localY2 + worldOriginY;
            x3 = localX2 + worldOriginX;
            y3 = localY2 + worldOriginY;
            x4 = localX2 + worldOriginX;
            y4 = localY + worldOriginY;
            origin1.x = localX + worldOriginX;
            origin1.y = localY + position/2f + worldOriginY;
            origin2.x = localX2 - position + worldOriginX;
            origin2.y = localY + position/2f + worldOriginY;
        }
    }
}
