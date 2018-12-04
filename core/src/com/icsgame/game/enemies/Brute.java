package com.icsgame.game.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.icsgame.game.weapons.Gun;
import com.icsgame.screens.ScrGame;

public class Brute extends Enemy {

    Gun gun;

    public Brute(ScrGame game, Texture txt, int w, int h, float fStrength, int nScoreIncrease) {
        super(game, txt, w, h, 1, 100, 100, fStrength/2, nScoreIncrease);
        gun = new Gun(game, this);
        gun.loadType("machine_gun");
        gun.scaleDamage(fStrength);
        weapon = gun;
    }
}
