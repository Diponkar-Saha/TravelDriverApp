package com.example.traveldriverapp.ui.dashboard.model;

public class DriverModel {
    String email,uid,name,phone,location,image,cover;

    public DriverModel() {
    }

    public DriverModel(String email, String uid, String name, String phone, String location, String image, String cover) {
        this.email = email;
        this.uid = uid;
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.image = image;
        this.cover = cover;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
