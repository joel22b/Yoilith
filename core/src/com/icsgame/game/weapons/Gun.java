package com.icsgame.game.weapons;

import com.icsgame.screens.ScrGame;
import sun.misc.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Gun {

    ScrGame game;

    // Gun info
    String sGun;
    int nDamage, nAmmo, nAmmoMax;
    float fReload;

    public Gun(ScrGame game){
        this.game = game;
    }

    public void fire(){

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
            fReload = Float.valueOf(prop.getProperty("reload"));
            nAmmoMax = Integer.valueOf(prop.getProperty("ammoMax"));
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
