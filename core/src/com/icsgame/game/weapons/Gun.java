package com.icsgame.game.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.icsgame.game.Player;
import com.icsgame.game.enemies.Enemy;
import com.icsgame.game.weapons.projectiles.Bullet;
import com.icsgame.game.weapons.projectiles.Projectile;
import com.icsgame.screens.ScrGame;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/* =========================== Gun =============================
Extends Weapon
Weapon that fires Bullets
Contained in Player or Enemies
Fires Bullets
================================================================ */

public class Gun extends Weapon {

    // Gun info
    int nDist;
    float fSpeed;

    public Gun(ScrGame game, Player player){
        super();
        this.game = game;
        this.player = player;
        isPlayer = true;
    }

    public Gun(ScrGame game, Enemy enemy){
        super();
        this.game = game;
        this.enemy = enemy;
        isPlayer = false;
    }

    @Override
    protected Projectile fireShot() {
        // Set Width and Height of Bullet
        rectProjectile.setWidth(20);
        rectProjectile.setHeight(20);

        // Create Bullet
        if (isPlayer) {
            return new Bullet(game, new Texture("bullet.png"),
                    rectProjectile, vVelTemp, nDamage, fSpeed, nDist, 0);
        } else {
            return new Bullet(game, new Texture("bullet.png"),
                    rectProjectile, vVelTemp, nDamage, fSpeed, nDist, 1);
        }
    }

    @Override
    public void loadType(String sType) {
        this.sType = sType;

        // Load File
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("libs/"+sType+".properties");

            // load a properties file
            prop.load(input);

            // get the property value
            nDamage = Integer.valueOf(prop.getProperty("damage"));
            nCooldown = Integer.valueOf(prop.getProperty("cooldown"));
            nReload = Integer.valueOf(prop.getProperty("reload"));
            nAmmoMax = Integer.valueOf(prop.getProperty("ammoMax"));
            nShotsPerFire = Integer.valueOf(prop.getProperty("bulletPerShot"));
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
}
