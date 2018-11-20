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
    int nRange, nTime;
    float fSpeed;

    public ExplosiveLauncher(ScrGame game, Player player){
        super();
        this.game = game;
        this.player = player;
        isPlayer = true;
    }

    @Override
    protected Projectile fireShot() {
        // Set Width and Height of Explosive
        rectProjectile.setWidth(60);
        rectProjectile.setHeight(60);

        // Create Explosive
        if (isPlayer) {
            return new Explosive(new Texture("extra/bomb.png"),
                    rectProjectile, vVelTemp, fSpeed, nDamage, nRange, nTime, 0);
        } else {
            return new Explosive(new Texture("extra/bomb.png"),
                    rectProjectile, vVelTemp, fSpeed, nDamage, nRange, nTime, 1);
        }
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
            nReload = Integer.valueOf("reload");
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
