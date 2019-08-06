package com.gamegdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gamegdx.game.utils.TiledObjectUtil;
import com.gamegdx.game.utils.UI;

import static com.gamegdx.game.utils.Assets.tiledMap;

public class GameMap {
    public static final float UNIT_SCALE = 1f / 16f;

    public World world;
    public TiledMap map;
    //private Stage stageMap;
    public Player[] player = new Player[8];

    float angle;

    public GameMap() {
        //stageMap = new Stage();
        world = new World(new Vector2(), false);
        map = new TiledMap();
        map = tiledMap;

        player[0] = new Player(1, 20, 20, world);
        player[1] = new Player(2, 25, 20, world);
        player[2] = new Player(3, 30, 20, world);
        player[3] = new Player(4, 35, 20, world);
        player[4] = new Player(5, 20, 44, world);
        player[5] = new Player(6, 25, 44, world);
        player[6] = new Player(7, 30, 44, world);
        player[7] = new Player(8, 35, 44, world);

        TiledObjectUtil.parseTiledObjectLayer(world, map.getLayers().get("Objects").getObjects());
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (angle == 360) angle = 0;
            player[0].rotationBody = angle++;
        }
        for (int i = 0; i < 8; i++)
            player[i].update();
        //buildBuildingsBodies("Objects");
    }

    //public Player getPlayer(){
    //    return player[0];
    //}

    public void dispose() {
        map.dispose();
        //stageMap.dispose();
        //ui.dispose();
//            world.dispose();
    }

    public void buildBuildingsBodies(String layer){
        MapObjects objects = tiledMap.getLayers().get(layer).getObjects();
        for (MapObject object: objects) {
            //Rectangle rectangle = ((RectangleMapObject)object).getRectangle();
            Polygon poly = ((PolygonMapObject) object).getPolygon();
            //create a dynamic within the world body (also can be KinematicBody or StaticBody
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            Body body = world.createBody(bodyDef);
            PolygonShape polygonShape = new PolygonShape();
            //polygonShape.set(poly.getTransformedVertices());

            //create a fixture for each body from the shape
            //Fixture fixture = body.createFixture(getShapeFromRectangle(rectangle),density);
            //Fixture fixture = body.createFixture(polygonShape,1f);

            //setting the position of the body's origin. In this case with zero rotation
            //body.setTransform(getTransformedCenterForRectangle(rectangle),0);
        }
    }
}
