package com.icsgame.game.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.icsgame.game.Player;
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
    int nSpray, nAngleRan, nDist;
    Vector2 vVelRan = new Vector2();
    Rectangle rectBullet = new Rectangle();
    float fSpeed;

    public Gun(ScrGame game, Player player){
        this.game = game;
        this.player = player;
    }

    @Override
    protected Projectile fireShot() {
        // Get the correct angle and velocity vector
        nAngleRan = ranGen.nextInt(nSpray*2)-nSpray;
        vVelRan.set(player.getAngleHead());
        vVelRan.setAngle(nAngleRan+player.getAngleHead().angle());
        vVelRan.nor();

        // Get Bullet Starting Location
        rectBullet.set(player.getHeadX()+(vVelRan.x*player.getHeadSize()),
                player.getHeadY()+(vVelRan.y*player.getHeadSize()), 20, 20);

        // Create Bullet
        return new Bullet(new Texture("bullet.png"),
                rectBullet, vVelRan, nDamage, fSpeed, nDist);
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
