package com.example.navigationexercice;

import android.media.MediaPlayer;

import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private int currentAudioIndex = 0;
    private MediaPlayer mediaPlayer;

    public int getCurrentAudioIndex() {
        return currentAudioIndex;
    }

    public void setCurrentAudioIndex(int index) {
        currentAudioIndex = index;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

}