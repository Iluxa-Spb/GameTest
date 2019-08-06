package com.gamegdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gamegdx.game.control.PlayerController;
import com.gamegdx.game.model.GameMap;

public class GameMapRenderer {
    public static final float UNIT_SCALE = 1f / 16f;
    public static float CAMERA_WIDTH = 800;
    public static float CAMERA_HEIGHT = 480;
    public static float deltaCff;
    public static float S = 0.005f;

    private GameMap gameMap;
    private PlayerController playerController;
    public OrthographicCamera camera;
    ShapeRenderer shapeRenderer;
    public Viewport vp;
    private OrthogonalTiledMapRenderer renderer;
    private Box2DDebugRenderer box2DRenderer;
    private InputMultiplexer inputMultiplexer;

    public int width;
    public int height;
    public float ppuX; //pixels on world X
    public float ppuY;//pixels in world Y

    public GameMapRenderer(GameMap gameMap, PlayerController playerController){
        this.gameMap = gameMap;
        this.playerController = playerController;
        //this.camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);

        //vp = new FitViewport(CAMERA_WIDTH,CAMERA_HEIGHT,camera);


        this.camera = new OrthographicCamera(CAMERA_WIDTH,CAMERA_HEIGHT);

        renderer = new OrthogonalTiledMapRenderer(gameMap.map, UNIT_SCALE);
        camera.setToOrtho(false, CAMERA_WIDTH*UNIT_SCALE, CAMERA_HEIGHT*UNIT_SCALE);
        camera.position.set(gameMap.player[0].getX(),gameMap.player[0].getY(),0);
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(playerController.stageControllers);
        Gdx.input.setInputProcessor(playerController.stageControllers);
        //playerController.stageControllers.setViewport(vp);
        box2DRenderer = new Box2DDebugRenderer();
        shapeRenderer = new ShapeRenderer();
        //shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(Color.RED);

    }

    public void setSize(int w, int h){
        this.width = w;
        this.height = h;
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
    }

    //set camera
    public void setCamera(float x, float y){
        this.camera.position.set((x/S)*S, (y/S)*S, 0);
        this.camera.update();
    }

    public void render(float delta){
        deltaCff = delta;
        gameMap.world.step(delta,1,1);
        gameMap.update(delta);

        setCamera(gameMap.player[0].getX(),gameMap.player[0].getY());
        camera.update();

        renderer.setView(camera);
        renderer.render();
        renderer.getBatch().begin();
        for (int i = 0; i < 8;i++){
            gameMap.player[i].draw(renderer.getBatch(), delta);
        }
        renderer.getBatch().end();
        playerController.update();
        playerController.stageControllers.act(delta);
        playerController.stageControllers.draw();
        box2DRenderer.render(gameMap.world, camera.combined);
        //renderObject();
    }

    public void draw(){
    }

    public void dispose(){

    }

    public void renderObject() {
        for (MapObject object : gameMap.map.getLayers().get("Objects").getObjects()) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
                shapeRenderer.end();
            } else if (object instanceof CircleMapObject) {
                Circle circle = ((CircleMapObject) object).getCircle();
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.circle(circle.x, circle.y, circle.radius);
                shapeRenderer.end();
            } else if (object instanceof PolylineMapObject) {
                Polyline line = ((PolylineMapObject) object).getPolyline();
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.polyline(line.getTransformedVertices());
                shapeRenderer.end();
            } else if (object instanceof PolygonMapObject) {
                Polygon poly = ((PolygonMapObject) object).getPolygon();
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.polygon(poly.getTransformedVertices());
                shapeRenderer.end();
            }
        }
    }
}
