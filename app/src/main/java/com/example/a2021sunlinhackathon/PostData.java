package com.example.a2021sunlinhackathon;

public class PostData {
    //public String profileImageUrl;
    public String name; //
    //public String userUploadImageUrl;
    public String post; //
    public String uid; // 사용자 고유값
    public String addars; // 없음?

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

