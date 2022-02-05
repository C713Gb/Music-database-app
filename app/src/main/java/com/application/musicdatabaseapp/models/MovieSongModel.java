package com.application.musicdatabaseapp.models;

import java.io.Serializable;

public class MovieSongModel implements Serializable {

    private String Mov_id;
    private String Art_id;
    private String Name;
    private int No_of_songs;
    private float duration;

    public String getMov_id() {
        return Mov_id;
    }

    public void setMov_id(String mov_id) {
        Mov_id = mov_id;
    }

    public String getArt_id() {
        return Art_id;
    }

    public void setArt_id(String art_id) {
        Art_id = art_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNo_of_songs() {
        return No_of_songs;
    }

    public void setNo_of_songs(int no_of_songs) {
        No_of_songs = no_of_songs;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
}
