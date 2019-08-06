package com.gamegdx.game.model;

public class Gun {
    private int quad;
    private int damage;
    private int delay;
    private float spread;
    private int clip;
    private int ofclip;
    private int redelay;
    private float wlspeed;
    private float buspeed;
    private float vangle;
    private int money;
    private int type;
    private String name;

    private int clipMax;
    public int currentAmmo;
    public int currentClip;

    public Gun(Gun gun){
        this.quad = gun.quad;
        this.damage = gun.damage;
        this.delay = gun.delay;
        this.spread = gun.spread;
        this.clip = gun.clip;
        this.ofclip = gun.ofclip;
        this.redelay = gun.redelay;
        this.wlspeed = gun.wlspeed;
        this.buspeed = gun.buspeed;
        this.vangle = gun.vangle;
        this.money = gun.money;
        this.type = gun.type;
        this.name = gun.name;

        if (this.type != 3) clipMax = clip * 3;
        else clipMax = clip;
        currentAmmo = 0;
        currentClip = clip;
    }

    public Gun(int quad, int damage, int delay, float spread, int clip, int ofclip, int redelay, float wlspeed, float buspeed, float vangle, int money, int type, String name){
        this.quad = quad;
        this.damage = damage;
        this.delay = delay;
        this.spread = spread;
        this.clip = clip;
        this.ofclip = ofclip;
        this.redelay = redelay;
        this.wlspeed = wlspeed;
        this.buspeed = buspeed;
        this.vangle = vangle;
        this.money = money;
        this.type = type;
        this.name = name;

        if (this.type != 3) clipMax = clip * 3;
        else clipMax = clip;
        currentAmmo = 0;
        currentClip = clip;
    }

    public int getType(){return type;}
    public int getQuad(){return quad;}
}
