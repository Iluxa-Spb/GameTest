package com.gamegdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class GunsList {
    //ArrayList<Gun> guns = new ArrayList<Gun>();
    private Gun guns[];
    private int num;

    public GunsList(){
        FileHandle file = Gdx.files.internal("guns.txt");
        String text = file.readString();
        String textArray[] = text.split("\\r?\\n");
        num = Integer.parseInt(textArray[0]);
        guns = new Gun[num];
        for (int i = 1; i<=num; i++){
            String lineArray[] = textArray[i].split("\\t");
            guns[i-1] = new Gun(Integer.parseInt(lineArray[0]),Integer.parseInt(lineArray[1]),Integer.parseInt(lineArray[2]),Float.parseFloat(lineArray[3]),
                    Integer.parseInt(lineArray[4]),Integer.parseInt(lineArray[5]),Integer.parseInt(lineArray[6]),Float.parseFloat(lineArray[7]),
                    Float.parseFloat(lineArray[8]),Float.parseFloat(lineArray[9]),Integer.parseInt(lineArray[10]),Integer.parseInt(lineArray[11]),
                    lineArray[12]);
        }
    }
    public Gun getGun(int i){
        return guns[i];
    }
}
