package com.example.traveldriverapp.ui.dashboard;

public class User {

    private String uid;
    private String name;
    private String image;
    private String phone;
    private String status;

    public User() {
    }

    public User(String uid, String name, String image, String phone, String status) {
        this.uid = uid;
        this.name = name;
        this.image = image;
        this.phone = phone;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
