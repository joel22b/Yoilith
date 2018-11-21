package com.icsgame.game.weapons.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.icsgame.game.Player;
import com.icsgame.game.enemies.Enemy;
import com.icsgame.screens.ScrGame;

/* =========================== Bullet =============================
Extends Projectile
The bullet that flies across the map
Contains all the needed information for the bullet
================================================================ */

public class Bullet extends Projectile {

    int nDist;

    public Bullet(ScrGame game, Texture txt, Rectangle rect, Vector2 vVel, int nDamage, float fSpeed, int nDist, int nTeam){
        super(game, txt, rect, vVel, nDamage, fSpeed, nTeam);

        this.nDist = nDist;

        setX(rect.getX());
        setY(rect.getY());
    }

    @Override
    protected boolean shouldKill(){
        nDist--;
        if(nDist <= 0){
            return true;
        }

        // ================== Check Collision =======================
        // This is where collision detection is working perfectly
        // Check Teams
        if (nTeam == 0) {
            Enemy enemyTemp = game.collisionEnemies(rect);
            if (enemyTemp != null) {
                enemyTemp.decreaseHealth(nDamage);
                return true;
            }
        } else {
            Player playerTemp = game.collisionPlayer(rect);
            if (playerTemp != null) {
                playerTemp.decreaseHealth(nDamage);
                return true;
            }
        }

        return game.collisionMap(rect);

        // ==========================================================
    }
}
