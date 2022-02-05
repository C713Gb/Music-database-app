package com.application.musicdatabaseapp.models;

import java.io.Serializable;

public class UserModel implements Serializable {

    private String User_id;
    private String Name;
    private int Age;
    private String Sex;
    private long Phone;
    private String Address;

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

    public long getPhone() {
        return Phone;
    }

    public void setPhone(long phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "User_id='" + User_id + '\'' +
                ", Name='" + Name + '\'' +
                ", Age=" + Age +
                ", Sex='" + Sex + '\'' +
                ", Phone=" + Phone +
                ", Address='" + Address + '\'' +
                '}';
    }
}
