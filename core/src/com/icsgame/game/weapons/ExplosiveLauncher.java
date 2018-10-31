package com.icsgame.game.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.icsgame.game.Player;
import com.icsgame.game.weapons.projectiles.Explosive;
import com.icsgame.game.weapons.projectiles.Projectile;
import com.icsgame.screens.ScrGame;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/* =========================== ExplosiveLauncher =============================
Extends Weapon
The weapon that fires Explosives
Contained in a player or enemy
Loads in data from .properties file
================================================================ */

public class ExplosiveLauncher extends Weapon {

    // ExplosiveLauncher info
    int nSpray, nAngleRan, nRange, nTime;
    Vector2 vVelRan = new Vector2();
    Rectangle rectExplosive = new Rectangle();
    float fSpeed;

    public ExplosiveLauncher(ScrGame game, Player player){
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
        rectExplosive.set(player.getHeadX()+(vVelRan.x*player.getHeadSize()),
                player.getHeadY()+(vVelRan.y*player.getHeadSize()), 80, 80);
        // Create Bullet
        return new Explosive(new Texture("extra/bomb.png"),
                rectExplosive, vVelRan, fSpeed, nDamage, nRange, nTime);
    }

    @Override
    public void loadType(String sExplosiveLauncher) {
        this.sType = sExplosiveLauncher;

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
            nAmmoMax = Integer.valueOf(prop.getProperty("bombsMax"));
            nShotsPerFire = Integer.valueOf(prop.getProperty("bombsPerShot"));
            nSpray = Integer.valueOf(prop.getProperty("spray"));
            fSpeed = Float.valueOf(prop.getProperty("speed"));
            nRange = Integer.valueOf(prop.getProperty("range"));
            nTime = Integer.valueOf(prop.getProperty("time"));
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
