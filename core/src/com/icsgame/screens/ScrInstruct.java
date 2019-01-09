package com.icsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.icsgame.Main;
import com.icsgame.objects.Button;
import com.icsgame.objects.Instruction;

public class ScrInstruct implements Screen {

    Main main;
    SpriteBatch batch;
    Texture txtBG;
    Sprite sprBG;
    Button btnBack, btnLeft, btnRight;
    BitmapFont font;

    Instruction[] arInstructions;
    int nInstructionIndex;

    public ScrInstruct(Main main) {
        this.main = main;
        batch = new SpriteBatch();
        txtBG = new Texture("backgroundBlackBoards.jpg");
        sprBG = new Sprite(txtBG, 0, 0, main.nWidth, main.nHeight);

        // Create Buttons
        btnBack = new Button((main.nWidth/2)-150, 60, 300, 108, "btnBack.png",
                "btnBackPressed.png", "Blank.png");
        btnLeft = new Button(100, 60, 100, 100, "selectionButtonLeft.png",
                "selectionButtonLeft.png", "selectionButtonLeft.png");
        btnRight = new Button(main.nWidth-200, 60, 100, 100, "selectionButtonRight.png",
                "selectionButtonRight.png", "selectionButtonRight.png");

        // Create Font
        font = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        font.setColor(Color.YELLOW);
        font.getData().setScale(1.5f);

        // Set up Instructons
        arInstructions = new Instruction[4];
        nInstructionIndex = 0;

        arInstructions[0] = new Instruction(100, 100, 100, 100, "movement");
        arInstructions[1] = new Instruction(100, 100, 100, 100, "weapons");
        arInstructions[2] = new Instruction(100, 100, 100, 100, "enemies");
        arInstructions[3] = new Instruction(100, 100, 100, 100, "score");
    }

    @Override
    public void render(float delta) {
        batch.begin();

        // Draw Background
        sprBG.draw(batch);
        font.draw( batch,"Instructions:", 60, main.nHeight-100);

        // Render Instructions
        arInstructions[nInstructionIndex].render(batch);

        batch.end();

        // Update buttons
        btnBack.update(batch);
        btnLeft.update(batch);
        btnRight.update(batch);

        // Checks if buttons are pressed
        checkButtons();
    }

    private void checkButtons(){ // Checks if Buttons are pressed
        if(btnBack.justClicked()){
            main.changeScreen(0);
        }
        if(btnLeft.justClicked()){
            nextInstruction(false);
        }
        if(btnRight.justClicked()){
            nextInstruction(true);
        }
    }

    private void nextInstruction(boolean bIsRight) {
        if (bIsRight) {
            if (nInstructionIndex+1 < arInstructions.length) {
                nInstructionIndex++;
            } else {
                nInstructionIndex = 0;
            }
        } else {
            if (nInstructionIndex > 0) {
                nInstructionIndex--;
            } else {
                nInstructionIndex = arInstructions.length-1;
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
