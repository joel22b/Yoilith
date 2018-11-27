package com.icsgame.game.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.icsgame.game.weapons.ExplosiveLauncher;
import com.icsgame.game.weapons.Gun;
import com.icsgame.screens.ScrGame;

public class Bomber extends Enemy {

    ExplosiveLauncher explosiveLauncher;

    public Bomber(ScrGame game, Texture txt, int w, int h, float fStrength) {
        super(game, txt, w, h, 2, 50, 50, fStrength);
        explosiveLauncher = new ExplosiveLauncher(game, this);
        explosiveLauncher.loadType("explosive_launcher");
        explosiveLauncher.scaleDamage(fStrength);
        weapon = explosiveLauncher;
    }
}
