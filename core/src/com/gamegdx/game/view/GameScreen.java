package com.gamegdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gamegdx.game.control.PlayerController;
import com.gamegdx.game.model.GameMap;

public class GameScreen implements Screen {

    //static final float WIDTH = Gdx.graphics.getWidth();
    //static final float HEIGHT = Gdx.graphics.getHeight();

    //private SpriteBatch batch;
    private GameMap gameMap;
    private GameMapRenderer gameMapRenderer;
    private PlayerController playerController;
    //private Viewport viewport;

    @Override
    public void show() {
        gameMap = new GameMap();
        playerController = new PlayerController(gameMap.player[0]);
        gameMapRenderer = new GameMapRenderer(gameMap, playerController);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        gameMapRenderer.render(delta);
        gameMapRenderer.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameMapRenderer.setSize(width, height);
        playerController.stageControllers.getViewport().update(width,height,true);
        //float aspectRatio = (float) height / width;
        //camera.setToOrtho(false,100f, 100f * aspectRatio);
        //camera.zoom = 0.7f;
        //gameMapRenderer.camera.viewportWidth = width;
        //gameMapRenderer.camera.viewportHeight = height;
        gameMapRenderer.camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        gameMap.dispose();
        gameMapRenderer.dispose();
        playerController.dispose();
    }
}
