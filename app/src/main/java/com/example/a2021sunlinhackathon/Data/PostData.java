package com.example.a2021sunlinhackathon.Data;

import java.util.ArrayList;

public class PostData {
    //public String profileImageUrl;
    public String name; //
    //public String userUploadImageUrl;
    public String post; //
    public String uid; // 사용자 고유값
    public String addars; // 없음?
    public String postid;
    public boolean isHeartPushed;
    public ArrayList<String> heart;
    public int count;

    public PostData(String name, String post, String uid, String addars, String postid, boolean isHeartPushed) {
        this.name = name;
        this.post = post;
        this.uid = uid;
        this.addars = addars;
        this.postid = postid;
        this.isHeartPushed = isHeartPushed;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<String> getHeart() {
        return heart;
    }

    public void setHeart(ArrayList<String> heart) {
        this.heart = heart;
    }

    public boolean isHeartPushed() {
        return isHeartPushed;
    }

    public void setHeartPushed(boolean heartPushed) {
        isHeartPushed = heartPushed;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getAddars() {
        return addars;
    }

    public void setAddars(String addars) {
        this.addars = addars;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /*
    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void setUserUploadImageUrl(String userUploadImageUrl) {
        this.userUploadImageUrl = userUploadImageUrl;
    }

    public String getUserUploadImageUrl() {
        return userUploadImageUrl;
    } */
}

