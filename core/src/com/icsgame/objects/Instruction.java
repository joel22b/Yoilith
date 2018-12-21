package com.icsgame.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Instruction {

    Vector2 vPos, vSize;

    Texture txtBG;

    Sprite[] arTxts;

    String[] arFonts;
    Vector2[] arFontPos;

    public Instruction(int nX, int nY, int nW, int nH, String sType) {
        vPos = new Vector2(nX, nY);
        vSize = new Vector2(nW, nH);

        loadType(sType);
    }

    //public void

    public void loadType(String sType) {

        // Load File
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("instructions/" + sType + ".properties");

            // load a properties file
            prop.load(input);

            // Set up arrays
            arTxts = new Sprite[Integer.valueOf(prop.getProperty("txtNum"))];

            int nFontNum = Integer.valueOf(prop.getProperty("fontNum"));
            arFonts = new String[nFontNum];
            arFontPos = new Vector2[nFontNum];

            // Load Background
            txtBG = new Texture(String.valueOf(prop.getProperty("background")));

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
}
