package com.example.navigationexercice;

public class songModel {
    private String songName;
    private String authorName;
    private String audioUrl;
    private int album;

    public songModel(String songName, String authorName, String audioUrl, int album) {
        this.songName = songName;
        this.authorName = authorName;
        this.audioUrl = audioUrl;
        this.album = album;
    }

    public String getSongName() {
        return songName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public int getAlbum() {
        return album;
    }
}
