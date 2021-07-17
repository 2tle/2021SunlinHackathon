package com.example.a2021sunlinhackathon;

public class PostData {
    public String profileImageUrl;
    public String name;
    public String userUploadImageUrl;
    public String text;
    public String uid;
    public String userid;
    public String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getUserUploadImageUrl() {
        return userUploadImageUrl;
    }

    public void setUserUploadImageUrl(String userUploadImageUrl) {
        this.userUploadImageUrl = userUploadImageUrl;
    }
}

