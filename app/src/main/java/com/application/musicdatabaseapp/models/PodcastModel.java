package com.application.musicdatabaseapp.models;

import java.io.Serializable;

public class PodcastModel implements Serializable {

    private String Podcasts_id;
    private String Podcaster_id;
    private String Name;
    private int No_of_episodes;

    public String getPodcasts_id() {
        return Podcasts_id;
    }

    public void setPodcasts_id(String podcasts_id) {
        Podcasts_id = podcasts_id;
    }

    public String getPodcaster_id() {
        return Podcaster_id;
    }

    public void setPodcaster_id(String podcaster_id) {
        Podcaster_id = podcaster_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNo_of_episodes() {
        return No_of_episodes;
    }

    public void setNo_of_episodes(int no_of_episodes) {
        No_of_episodes = no_of_episodes;
    }

    @Override
    public String toString() {
        return "PodcastModel{" +
                "Podcasts_id='" + Podcasts_id + '\'' +
                ", Podcaster_id='" + Podcaster_id + '\'' +
                ", Name='" + Name + '\'' +
                ", No_of_episodes=" + No_of_episodes +
                '}';
    }
}
