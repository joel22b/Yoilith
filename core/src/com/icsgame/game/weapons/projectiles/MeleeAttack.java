package com.icsgame.game.weapons.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MeleeAttack extends Projectile {

    public MeleeAttack( Rectangle rect, int nDamage, int nTeam){
        super(new Texture("Blank.png"), rect, new Vector2(0,0), nDamage, 0, nTeam);

        setX(rect.getX());
        setY(rect.getY());

        System.out.println("Created MeleeAttack");
    }

    @Override
    protected boolean shouldKill(){
        System.out.println("Killed MeleeAttack");
        return true;
    }
}
