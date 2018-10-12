package com.icsgame.game.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.icsgame.game.Player;
import com.icsgame.screens.ScrGame;
import sun.misc.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Gun {

    ScrGame game;
    Player player;

    // Gun info
    String sGun;
    int nDamage, nBulletPerShot, nAmmo, nAmmoMax, nCooldown, nSpray;
    boolean canFire = true;
    int tickCount = 0;

    public Gun(ScrGame game, Player player){
        this.game = game;
        this.player = player;
    }

    public void fire(){
        if(canFire){
            if(nAmmo > 0) {
                for (int i = 0; i < nBulletPerShot; i++) {
                    game.getBullets().add(new Bullet(new Texture("bullet.png"), new Rectangle(player.getX(), player.getY(), 20, 20), player.getAngleHead(), nDamage, nSpray));
                }
                canFire = false;
                nAmmo--;
            }
        }
    }

    public void update(){
        if(!canFire){
            if(tickCount >= nCooldown){
                tickCount = 0;
                canFire = true;
            } else {
                tickCount++;
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
}
