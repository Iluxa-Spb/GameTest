package com.gamegdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.gamegdx.game.model.Skeleton;
import com.gamegdx.game.utils.Assets;
import com.gamegdx.game.view.GameScreen;

import static com.gamegdx.game.utils.Assets.manager;

public class Main extends Game {

    private Screen gameScreen;

    @Override
    public void create() {
        Assets.load();
        while (!Assets.manager.update())
            System.out.println(manager.getProgress()*100+"%");
        Assets.setAtlas();
        gameScreen = new GameScreen();
//        ((GameScreen)gameScreen).setTextureAtlas(assets.getManager().get("atlas_player.atlas", TextureAtlas.class));
        setScreen(gameScreen);
    }

    @Override
    public void dispose() {
        super.dispose();
        gameScreen.dispose();
        manager.dispose();
    }
}