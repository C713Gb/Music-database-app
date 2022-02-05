package com.application.musicdatabaseapp.models;

import java.io.Serializable;

public class ArtistModel implements Serializable {

    private String Art_id;
    private String Name;
    private int Age;
    private String Sex;
    private String Language;
    private int No_of_songs_composed;

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

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public int getNo_of_songs_composed() {
        return No_of_songs_composed;
    }

    public void setNo_of_songs_composed(int no_of_songs_composed) {
        No_of_songs_composed = no_of_songs_composed;
    }


}
