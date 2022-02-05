package com.application.musicdatabaseapp.models;

import java.io.Serializable;

public class PlaylistModel implements Serializable {

    private String Playlist_id;
    private String User_id;
    private String Name;
    private int No_of_songs;
    private float duration;

    public String getPlaylist_id() {
        return Playlist_id;
    }

    public void setPlaylist_id(String playlist_id) {
        Playlist_id = playlist_id;
    }

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
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
        return "PlaylistModel{" +
                "Playlist_id='" + Playlist_id + '\'' +
                ", User_id='" + User_id + '\'' +
                ", Name='" + Name + '\'' +
                ", No_of_songs=" + No_of_songs +
                ", duration=" + duration +
                '}';
    }
}
