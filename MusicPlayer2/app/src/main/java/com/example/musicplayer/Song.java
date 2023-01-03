package com.example.musicplayer;

public class Song {

    private String title, artist;
    int image;

    public Song(String title, String artist, int image){
        this.title = title;
        this.artist = artist;
        this.image = image;
    }

    public String getTitle() {return title;}

    public String getArtist() {
        return artist;
    }

    public int getImage() {return image;}
}
