package com.icsgame.game.weapons;

import com.icsgame.game.Player;
import com.icsgame.game.weapons.projectiles.Projectile;
import com.icsgame.screens.ScrGame;

import java.util.Random;

/* =========================== Weapon =============================
Abstract class that contains the need basics for any weapon
Extended in every weapon

Method:             Type:           Mandate:
update();           void            updates gun, update nCooldown
loadType(sType);    void            sets up weapon from .properties file
fire();             void            fires gun if you can
fireShot();         Projectile      configures the Projectile
reload();           void            reloads
getAmmo();          int             return nAmmo
getAmmoMax();       int             return nAmmoMax
================================================================ */

public abstract class Weapon {

    protected ScrGame game;
    protected Player player;
    protected Random ranGen = new Random();

    protected int nDamage; // The damage inflicted by this weapon

    protected int nAmmo; // Current ammo count
    protected int nAmmoMax; // Maximum ammo

    protected boolean bCanFire; // True means gun can fire
    protected int nCooldown; // The number of ticks until the weapon can be fired again
    protected int nTickCount; // The current count in the cooldown

    protected int nShotsPerFire; // The number of shots for every time you fire

    protected String sType; // The name of the .properties file

    public void update() {
        if(!bCanFire){
            if(nTickCount >= nCooldown){
                nTickCount = 0;
                bCanFire = true;
            } else {
                nTickCount++;
            }
        }
    }

    public void fire() {
        if(bCanFire) {
            if (nAmmo > 0) {
                // Can Fire
                // Decrease nAmmo by 1
                nAmmo--;

                // Make it so you can't fire until cooldown is done
                bCanFire = false;

                // Fire the number of shots required
                for (int i = 0; i < nShotsPerFire; i++) {
                    game.getProjectiles().add(fireShot());
                }
            }
        }
    }

    protected abstract Projectile fireShot();

    public abstract void loadType(String sType);

    public void reload() { nAmmo = nAmmoMax; }

    public int getAmmo() { return nAmmo; }

    public int getAmmoMax() { return nAmmoMax; }
}
