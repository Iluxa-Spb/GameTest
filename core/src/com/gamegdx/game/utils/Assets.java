package com.gamegdx.game.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.gamegdx.game.model.GunsList;

public class Assets {

    public static final AssetManager manager = new AssetManager();

    public static final String controllers_ = "models/atlas_controllers.atlas";
    public static final String player_ = "models/atlas_player.atlas";
    public static final String player2_ = "models/atlas_player2.atlas";
    public static final String guns_ = "models/guns.atlas";
    public static final String indicators_ = "models/user_interface.atlas";

    public static TextureAtlas atlasControllers;
    public static TextureAtlas atlasPlayer;
    public static TextureAtlas atlasPlayer2;
    public static TextureAtlas atlasGuns;
    public static TextureAtlas atlasIndicators;

    public static TiledMap tiledMap;

    public static GunsList gunsList;

    public static void load(){
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        //manager.load("maps/de_dust2/de_dust2.tmx", TiledMap.class);
        manager.load("maps/fy_iceworld/fy_iceworld.tmx", TiledMap.class);
        //loadMap("maps/de_dust2/de_dust2.tmx");
        manager.load(controllers_,TextureAtlas.class);
        manager.load(player_,TextureAtlas.class);
        manager.load(player2_,TextureAtlas.class);
        manager.load(indicators_,TextureAtlas.class);
        manager.load(guns_,TextureAtlas.class);

        gunsList = new GunsList();
    }

    public static void setMap(String name){
        //TmxMapLoader tmxMapLoader = new TmxMapLoader();
        //tiledMap = manager.get("maps/de_dust2/de_dust2.tmx");
    }

    public static void setAtlas(){
        atlasPlayer = new TextureAtlas(player_);
        atlasPlayer2 = new TextureAtlas(player2_);
        atlasIndicators = new TextureAtlas(indicators_);
        atlasGuns = new TextureAtlas(guns_);
        atlasControllers = new TextureAtlas(controllers_);
        //tiledMap = manager.get("maps/de_dust2/de_dust2.tmx");
        tiledMap = manager.get("maps/fy_iceworld/fy_iceworld.tmx");
    }

    public static void dispose(){
        manager.dispose();
        tiledMap.dispose();
        atlasPlayer.dispose();
        atlasPlayer2.dispose();
        atlasGuns.dispose();
        atlasIndicators.dispose();
        atlasControllers.dispose();
    }
}
