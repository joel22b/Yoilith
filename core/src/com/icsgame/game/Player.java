package com.icsgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.icsgame.game.utils.Animator;
import com.icsgame.game.weapons.ExplosiveLauncher;
import com.icsgame.game.weapons.Gun;
import com.icsgame.game.weapons.Melee;
import com.icsgame.screens.ScrGame;

/* ======================== Player ================================
This is the player
Holds all info in regards to the player
================================================================== */

public class Player {

    ScrGame game;

    // Player Info
    Melee melee;
    Gun gun;
    ExplosiveLauncher explosiveLauncher;
    int nHealthMax = 100, nHealth = nHealthMax, nBombsMax = 10, nBombs = nBombsMax;
    int nDir = 0;
    Texture[] txtPlayer, txtPlayerHead;
    Sprite sprPlayerTop;
    //Polygon polyPlayerTop;
    Rectangle rect;
    Vector2 vel, angleHead = new Vector2();
    Vector3 posMouse3D;
    int nActiveWeapon = 0, nWeaponNum = 2; // The Weapon that is currently in use
    Animator animator;

    public Player(ScrGame game, String playerFile, Rectangle rect, Vector2 vel){
        this.game = game;
        this.rect = rect;
        this.vel = vel;

        animator = new Animator(new Texture(playerFile+"/playerSheet.png"), 4, 4);
        animator.setPosition(getX(), getY());
        animator.setSize(getW(), getH());

        //txtPlayer = new Texture[2];
        //txtPlayer[0] = new Texture(playerFile+"/playerBody.png");
        //txtPlayer[1] = new Texture(playerFile+"/playerBodyBack.png");

        txtPlayerHead = new Texture[2];
        txtPlayerHead[0] = new Texture(playerFile+"/playerHeadWPistol.png");
        txtPlayerHead[1] = new Texture(playerFile+"/playerHeadBackWPistol.png");

        // Setup Head
        sprPlayerTop = new Sprite(txtPlayerHead[0], (int)(getX()+(getW()/2)-20), (int)(getY()+((getH()/4)*3)-20),
                (int)(getW()+40), (int)(getH()+40));
        sprPlayerTop.setRegion(txtPlayerHead[0]);

        /*polyPlayerTop = new Polygon(new float[]{sprPlayerTop.getX(), sprPlayerTop.getY(),
                sprPlayerTop.getX(), sprPlayerTop.getY()+sprPlayerTop.getHeight(),
                sprPlayerTop.getX()+sprPlayerTop.getWidth(), sprPlayerTop.getY()+sprPlayerTop.getHeight(),
                sprPlayerTop.getX()+sprPlayerTop.getWidth(), sprPlayerTop.getY()});*/

        // Create Weapons
        melee = new Melee(game, this);
        melee.loadType("sword");
        gun = new Gun(game, this);
        gun.loadType(game.getMain().scrSetup.getSbGunReturn());
        explosiveLauncher = new ExplosiveLauncher(game, this);
        explosiveLauncher.loadType("explosive_launcher");
    }

    public void render(SpriteBatch batch){
        animator.draw(batch, nDir, ((vel.x > 0.5f || vel.x < -0.5f)||(vel.y > 0.5f || vel.y < -0.5f)));

        batch.begin();

        // Player Bottom
        //batch.draw(txtPlayer[nDir], getX(), getY(), getW(), getH());

        // Player Top
        sprPlayerTop.draw(batch);

        batch.end();
    }

    public void update(){
        // Update nDir
        if (nDir == 0) {
            if (vel.angle() < 180) {
                nDir = 2;
            }
        } else {
            if (vel.angle() > 180) {
                nDir = 0;
            }
        }

        // Update animator
        animator.setPosition(getX(), getY());

        // Update Gun
        gun.update();

        // Update Bomber Launcher
        explosiveLauncher.update();

        // Slowing Down
        vel.set(vel.x*0.9f, vel.y*0.9f);

        // Update Position
        setX(getX()+vel.x);
        setY(getY()+vel.y);

        // Rotate Top
        posMouse3D = game.getCamera().unProject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        angleHead.set(posMouse3D.x-(sprPlayerTop.getX()+(sprPlayerTop.getWidth()/2)), posMouse3D.y-(sprPlayerTop.getY()+(sprPlayerTop.getHeight()/2)));
        sprPlayerTop.setRotation(angleHead.angle()+90);

        // Update Head Texture
        if (angleHead.angle() < 180) {
            sprPlayerTop.setTexture(txtPlayerHead[1]);
            sprPlayerTop.setRegion(txtPlayerHead[1]);
        } else {
            sprPlayerTop.setTexture(txtPlayerHead[0]);
            sprPlayerTop.setRegion(txtPlayerHead[0]);
        }
    }

    public void setupHealth(int nHealth, int nHealthMax){
        this.nHealth = nHealth;
        this.nHealthMax = nHealthMax;
    }

    public void kill(){
        gun = null;
        explosiveLauncher = null;
        sprPlayerTop = null;
        rect = null;
        vel = null;
        angleHead = null;
    }

    public float getX(){ return rect.x; }

    public float getY(){ return rect.y; }

    public float getW(){ return rect.width; }

    public float getH(){ return rect.height; }

    public void setX(float x){
        rect.setX(x);
        sprPlayerTop.setCenterX(x+(sprPlayerTop.getWidth()/2)-20);
    }

    public void setY(float y){
        rect.setY(y);
        sprPlayerTop.setCenterY(y+((sprPlayerTop.getHeight()/4)*3)-5);
    }

    public void setW(float w){ rect.setWidth(w); }

    public void setH(float h){ rect.setHeight(h); }

    public void addVel(Vector2 velAdd){
        vel.add(velAdd);
    }

    public void addVel(float vX, float vY){
        vel.add(vX, vY);
    }

    public Rectangle getRect() { return rect; }

    public Vector2 getVel() { return vel; }

    public Vector2 getPosition() { return new Vector2(rect.x, rect.y); }

    public int getHealth() { return nHealth; }

    public int getHealthMax() { return nHealthMax; }

    public Gun getGun() { return gun; }

    public ExplosiveLauncher getExplosiveLauncher() { return explosiveLauncher; }

    public Vector2 getAngleHead() { return angleHead; }

    public float getHeadX() { return sprPlayerTop.getX()+(sprPlayerTop.getWidth()/2); }

    public float getHeadY() { return sprPlayerTop.getY()+(sprPlayerTop.getHeight()/2); }

    public float getHeadSize() { return sprPlayerTop.getWidth(); }

    public int getBombs() { return nBombs; }

    public int getBombsMax() { return nBombsMax; }

    public int getActiveWeapon() { return nActiveWeapon; }

    public void nextWeapon() {
        if(nActiveWeapon >= nWeaponNum-1){
            nActiveWeapon = 0;
        } else {
            nActiveWeapon++;
        }
    }

    public void useWeapon() {
        if(nActiveWeapon == 0){ // Gun
            gun.fire();
        } else if(nActiveWeapon == 1){ // Bombs
            explosiveLauncher.fire();
        }
    }

    public void reloadWeapon() {
        if(nActiveWeapon == 0) { // Gun
            gun.reload();
        } else if(nActiveWeapon == 1) { // Bombs
            explosiveLauncher.reload();
        }
    }

    public void setAllWeaponStrength(float fStrength) {
        gun.scaleDamage(fStrength);
        explosiveLauncher.scaleDamage(fStrength);
    }

    public void useMelee() {
        melee.fire();
    }

    public void decreaseHealth(int decrease) {
        nHealth -= decrease;
    }

    public Vector2 getCenterPosition() {
        return new Vector2(getX()+(getW()/2), getY()+(getH()/2));
    }
}
