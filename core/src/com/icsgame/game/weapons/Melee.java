package com.icsgame.game.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.icsgame.game.Player;
import com.icsgame.game.enemies.Enemy;
import com.icsgame.game.weapons.projectiles.Bullet;
import com.icsgame.game.weapons.projectiles.MeleeAttack;
import com.icsgame.game.weapons.projectiles.Projectile;
import com.icsgame.screens.ScrGame;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Melee extends Weapon {

    int nRange; // The range of the weapon

    public Melee(ScrGame game, Player player){
        super();
        this.game = game;
        this.player = player;
        isPlayer = true;

        nSpray = 0;
        nShotsPerFire = 1;
        nAmmo = 1;
        nAmmoMax = nAmmo;
    }

    public Melee(ScrGame game, Enemy enemy){
        super();
        this.game = game;
        this.enemy = enemy;
        isPlayer = false;

        nSpray = 0;
        nShotsPerFire = 1;
        nAmmo = 1;
        nAmmoMax = nAmmo;
    }

    @Override
    protected Projectile fireShot() {
        nAmmo = 1;

        System.out.println("Firing Melee shot");

        // Create MeleeAttack
        if (isPlayer) {
            // Setup rect
            rectProjectile.setPosition(rectProjectile.getX()-nRange, rectProjectile.getY()-nRange);
            rectProjectile.setWidth(nRange*2);
            rectProjectile.setHeight(nRange*2);

            return new MeleeAttack(game, rectProjectile, nDamage, 0);
        } else {
            // Setup rect
            rectProjectile.setPosition(rectProjectile.getX()-(nRange+enemy.getRect().getWidth()),
                    rectProjectile.getY()-(nRange+enemy.getRect().getHeight()));
            rectProjectile.setWidth((nRange+enemy.getRect().getWidth())*2);
            rectProjectile.setHeight((nRange+enemy.getRect().getHeight())*2);

            return new MeleeAttack(game, rectProjectile, nDamage, 1);
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
            nRange = Integer.valueOf(prop.getProperty("range"));

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
