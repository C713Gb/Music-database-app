package com.application.musicdatabaseapp.models;

import java.io.Serializable;

public class AlbumSongModel implements Serializable {

    private String Alb_id;
    private String Art_id;
    private String Name;
    private int No_of_songs;
    private float duration;

    public String getAlb_id() {
        return Alb_id;
    }

    public void setAlb_id(String alb_id) {
        Alb_id = alb_id;
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

    @Override
    public String toString() {
        return "AlbumSongModel{" +
                "Alb_id='" + Alb_id + '\'' +
                ", Art_id='" + Art_id + '\'' +
                ", Name='" + Name + '\'' +
                ", No_of_songs=" + No_of_songs +
                ", duration=" + duration +
                '}';
    }
}
