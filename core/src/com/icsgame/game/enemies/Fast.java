package com.icsgame.game.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.icsgame.game.weapons.Gun;
import com.icsgame.screens.ScrGame;

public class Fast extends Enemy {

    Gun gun;

    public Fast(ScrGame game, Texture txt, int w, int h, float fStrength) {
        super(game, txt, w, h, 4, 10, 10, fStrength/2);
        gun = new Gun(game, this);
        gun.loadType("pistol");
        gun.scaleDamage(fStrength);
        weapon = gun;
    }
}
