package com.application.musicdatabaseapp.models;

import java.io.Serializable;

public class PodcasterModel implements Serializable {

    private String Pod_caster_id;
    private String Name;
    private int Age;
    private String Sex;
    private String language;

    public String getPod_caster_id() {
        return Pod_caster_id;
    }

    public void setPod_caster_id(String pod_caster_id) {
        Pod_caster_id = pod_caster_id;
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
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "PodcasterModel{" +
                "Pod_caster_id='" + Pod_caster_id + '\'' +
                ", Name='" + Name + '\'' +
                ", Age=" + Age +
                ", Sex='" + Sex + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
