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

/* =========================== Gun =============================
Weapon that fires Bullets
Contained in Player or Enemies
Fires Bullets
================================================================ */

public class Gun {

    ScrGame game;
    Player player;
    Random ranGen = new Random();

    // Gun info
    String sGun;
    int nDamage, nBulletPerShot, nAmmo, nAmmoMax, nCooldown, nSpray, nAngleRan, nDist;
    Vector2 vVelRan = new Vector2();
    Rectangle rectBullet = new Rectangle();
    float fSpeed;
    boolean bCanFire = true;
    int nTickCount = 0;


    public Gun(ScrGame game, Player player){
        this.game = game;
        this.player = player;
    }

    public void fire(){
        if(bCanFire){
            if(nAmmo > 0) {
                for (int i = 0; i < nBulletPerShot; i++) {
                    // Get the correct angle and velocity vector
                    nAngleRan = ranGen.nextInt(nSpray*2)-nSpray;
                    vVelRan.set(player.getAngleHead());
                    vVelRan.setAngle(nAngleRan+player.getAngleHead().angle());
                    vVelRan.nor();

                    // Get Bullet Starting Location
                    rectBullet.set(player.getHeadX()+(vVelRan.x*player.getHeadSize()), player.getHeadY()+(vVelRan.y*player.getHeadSize()), 20, 20);

                    // Create Bullet
                    game.getBullets().add(new Bullet(new Texture("bullet.png"),
                            rectBullet, vVelRan, nDamage, nAngleRan, fSpeed, nDist));
                }
                bCanFire = false;
                nAmmo--;
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

    public void loadType(String sGun){
        this.sGun = sGun;

        // Load File
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("libs/"+sGun+".properties");

            // load a properties file
            prop.load(input);

            // get the property value
            nDamage = Integer.valueOf(prop.getProperty("damage"));
            nCooldown = Integer.valueOf(prop.getProperty("cooldown"));
            nAmmoMax = Integer.valueOf(prop.getProperty("ammoMax"));
            nBulletPerShot = Integer.valueOf(prop.getProperty("bulletPerShot"));
            nSpray = Integer.valueOf(prop.getProperty("spray"));
            fSpeed = Float.valueOf(prop.getProperty("speed"));
            nDist = Integer.valueOf(prop.getProperty("distance"));
            nAmmo = nAmmoMax;

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

    public int getAmmoMax() { return nAmmoMax; }

    public int getAmmo() { return nAmmo; }

    public void reload() { nAmmo = nAmmoMax; }
}
