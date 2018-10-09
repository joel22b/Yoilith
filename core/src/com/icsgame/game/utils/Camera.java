package com.icsgame.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.icsgame.screens.ScrGame;

public class Camera {

    ScrGame game;
    OrthographicCamera camera;
    StretchViewport viewport;
    int nFollowW, nFollowH;
    float fSpeed;
    Vector2 vel = new Vector2(0,0);

    public Camera(ScrGame _game, int w, int h){
        game = _game;

        // Setup Camera
        // From Ameer
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new StretchViewport(w, h, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        resize(w, h);
    }

    public void setFollowBox(int nW, int nH){
        nFollowW = nW;
        nFollowH = nH;
    }

    public void update(SpriteBatch batch){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    public void follow(float nX, float nY, int nW, int nH){
        // Calculate Velocity
        fSpeed = (float)Math.sqrt(Math.pow((nX+(nW/2))-camera.position.x, 2)+Math.pow((nY+(nH/2))-camera.position.y, 2))/50;

        vel.set((nX+(nW/2))-camera.position.x, (nY+(nH/2))-camera.position.y);
        vel.nor();
        vel.set(vel.x*fSpeed, vel.y*fSpeed);

        // Move Camera
        if(boundaryUp()) {
            move();
        }
        if(boundaryDown()) {
            move();
        }
        if(boundaryLeft()) {
            move();
        }
        if(boundaryRight()) {
            move();
        }
    }

    public void resize(int w, int h){
        viewport.update(w, h);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    public void move(){ // Moves camera
        camera.translate(vel);
    }

    private boolean boundaryUp(){
        if(camera.position.y+(camera.viewportHeight/2) <= (game.nH*game.nTileSize)+game.nY){
            return true;
        } else {
            return false;
        }
    }

    private boolean boundaryDown(){
        if(camera.position.y-(camera.viewportHeight/2) >= game.nY){
            return true;
        } else {
            return false;
        }
    }

    private boolean boundaryLeft(){
        if(camera.position.x-(camera.viewportWidth/2) >= game.nX){
            return true;
        } else {
            return false;
        }
    }

    private boolean boundaryRight(){
        if(camera.position.x+(camera.viewportWidth/2) <= (game.nW*game.nTileSize)+game.nX){
            return true;
        } else {
            return false;
        }
    }

    public void setPosition(Vector2 pos) {
        camera.translate(pos.x - camera.position.x, pos.y - camera.position.y);
    }

    public float getX() { return camera.position.x; }

    public float getY() { return camera.position.y; }

    public float getW() { return camera.viewportWidth; }

    public float getH() { return camera.viewportHeight; }
}
