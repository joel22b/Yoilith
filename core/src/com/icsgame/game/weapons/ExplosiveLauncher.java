package com.icsgame.game.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.icsgame.game.Player;
import com.icsgame.screens.ScrGame;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

public class ExplosiveLauncher {

    ScrGame game;
    Player player;
    Random ranGen = new Random();

    // ExplosiveLauncher info
    String sExplosiveLauncher;
    int nDamage, nBombsPerShot, nBombs, nBombsMax, nCooldown, nSpray, nAngleRan, nRange, nTime;
    Vector2 vVelRan = new Vector2();
    Rectangle rectExplosive = new Rectangle();
    float fSpeed;
    boolean bCanFire = true;
    int nTickCount = 0;


    public ExplosiveLauncher(ScrGame game, Player player){
        this.game = game;
        this.player = player;
    }

    public void fire(){
        if(bCanFire){
            if(nBombs > 0) {
                for (int i = 0; i < nBombsPerShot; i++) {
                    // Get the correct angle and velocity vector
                    nAngleRan = ranGen.nextInt(nSpray*2)-nSpray;
                    vVelRan.set(player.getAngleHead());
                    vVelRan.setAngle(nAngleRan+player.getAngleHead().angle());
                    vVelRan.nor();

                    // Get Bullet Starting Location
                    rectExplosive.set(player.getHeadX()+(vVelRan.x*player.getHeadSize()), player.getHeadY()+(vVelRan.y*player.getHeadSize()), 80, 80);
                    // Create Bullet
                    game.getExplosives().add(new Explosive(new Texture("extra/bomb.png"),
                            rectExplosive, vVelRan, fSpeed, nDamage, nRange, nTime));
                }
                bCanFire = false;
                nBombs--;
            }
        }
    }

    public void update(){
        if(!bCanFire){
            if(nTickCount >= nCooldown){
                nTickCount = 0;
                bCanFire = true;
            } else {
                nTickCount++;
            }
        }
    }

    public void loadType(String sExplosiveLauncher){
        this.sExplosiveLauncher = sExplosiveLauncher;

        // Load File
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("libs/"+sExplosiveLauncher+".properties");

            // load a properties file
            prop.load(input);

            // get the property value
            nDamage = Integer.valueOf(prop.getProperty("damage"));
            nCooldown = Integer.valueOf(prop.getProperty("cooldown"));
            nBombsMax = Integer.valueOf(prop.getProperty("bombsMax"));
            nBombsPerShot = Integer.valueOf(prop.getProperty("bombsPerShot"));
            nSpray = Integer.valueOf(prop.getProperty("spray"));
            fSpeed = Float.valueOf(prop.getProperty("speed"));
            nRange = Integer.valueOf(prop.getProperty("range"));
            nTime = Integer.valueOf(prop.getProperty("time"));
            nBombs = nBombsMax;

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getBombsMax() { return nBombsMax; }

    public int getBombs() { return nBombs; }

    public void reload() { nBombs = nBombsMax; }
}
