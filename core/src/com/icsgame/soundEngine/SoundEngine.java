package com.icsgame.soundEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.util.Random;

public class SoundEngine {

    Random random;

    // Volume
    float fVolMusic, fVolSound;

    // Background Music
    Music[] music;
    int[] arnMusicPlayed;
    int nSong;

    public SoundEngine() {
        random = new Random();

        // Load Background Music
        nSong = 0;
        music = new Music[2];
        arnMusicPlayed = new int[2];
        clearMusicHistory();

        music[0] = Gdx.audio.newMusic(Gdx.files.internal("audio/bobaBox.mp3"));
        music[1] = Gdx.audio.newMusic(Gdx.files.internal("audio/electroman.mp3"));
        //music[2] = Gdx.audio.newMusic(Gdx.files.internal("audio/"));

        // Set Volumes
        setMusicVolume(1.0f);
        fVolSound = 1.0f;
    }

    public void update() {
        if (!music[nSong].isPlaying()) {
            nextSong();
        }
    }

    public void setMusicVolume(float fVolMusic) {
        this.fVolMusic = fVolMusic;

        for (int i = 0; i < music.length; i++) {
            music[i].setVolume(this.fVolMusic);
        }
    }

    public void clearMusicHistory() {
        for (int i = 0; i < arnMusicPlayed.length; i++) {
            arnMusicPlayed[i] = 0;
        }
    }

    public void nextSong() {
        music[nSong].stop();
        int nRandom, nCount = 0;
        do {
            if (nCount > arnMusicPlayed.length+2) {
                clearMusicHistory();
            }
            nRandom = random.nextInt(arnMusicPlayed.length);
            nCount++;
        } while (arnMusicPlayed[nRandom] != 0);
        nSong = nRandom;
        music[nSong].play();
    }

    public float getVolMusic() { return fVolMusic; }

    public void setVolMusic(float fVolMusic) { this.fVolMusic = fVolMusic; }

    public float getVolSound() { return fVolSound; }

    public void setVolSound(float fVolSound) { this.fVolSound = fVolSound; }
}
