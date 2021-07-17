package com.example.a2021sunlinhackathon.Data;

public class ProfilePostData {
    public String l_name;
    public String l_post;
    public String l_uid;
    public String l_addars;
    public String l_postid;
    public boolean l_isHeartPushed;

    public String r_name;
    public String r_post;
    public String r_uid;
    public String r_addars;
    public String r_postid;
    public boolean r_isHeartPushed;

    public ProfilePostData() {

    }
    public ProfilePostData(ProfilePostData pfPDt) {
        this.l_post = pfPDt.l_post;
        this.l_isHeartPushed = pfPDt.l_isHeartPushed;
        this.l_postid = pfPDt.l_postid;
        this.l_addars = pfPDt.l_addars;
        this.l_uid = pfPDt.l_uid;
        this.l_name = pfPDt.l_name;

        this.r_name = pfPDt.r_name;
        this.r_uid = pfPDt.r_uid;
        this.r_postid = pfPDt.r_postid;
        this.r_post = pfPDt.r_post;
        this.r_isHeartPushed = pfPDt.r_isHeartPushed;
        this.r_addars = pfPDt.r_addars;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public void clear() {
        this.l_name=null;
        this.l_post=null;
        this.l_uid=null;
        this.l_addars=null;
        this.l_postid=null;
        this.l_isHeartPushed=false;

        this.r_addars=null;
        this.r_isHeartPushed=false;
        this.r_post=null;
        this.r_postid="NODATA";
        this.r_uid = null;
        this.r_name = null;
    }

    public String getL_post() {
        return l_post;
    }

    public void setL_post(String l_post) {
        this.l_post = l_post;
    }

    public String getL_uid() {
        return l_uid;
    }

    public void setL_uid(String l_uid) {
        this.l_uid = l_uid;
    }

    public String getL_addars() {
        return l_addars;
    }

    public void setL_addars(String l_addars) {
        this.l_addars = l_addars;
    }

    public String getL_postid() {
        return l_postid;
    }

    public void setL_postid(String l_postid) {
        this.l_postid = l_postid;
    }

    public boolean isL_isHeartPushed() {
        return l_isHeartPushed;
    }

    public void setL_isHeartPushed(boolean l_isHeartPushed) {
        this.l_isHeartPushed = l_isHeartPushed;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getR_post() {
        return r_post;
    }

    public void setR_post(String r_post) {
        this.r_post = r_post;
    }

    public String getR_uid() {
        return r_uid;
    }

    public void setR_uid(String r_uid) {
        this.r_uid = r_uid;
    }

    public String getR_addars() {
        return r_addars;
    }

    public void setR_addars(String r_addars) {
        this.r_addars = r_addars;
    }

    public String getR_postid() {
        return r_postid;
    }

    public void setR_postid(String r_postid) {
        this.r_postid = r_postid;
    }

    public boolean isR_isHeartPushed() {
        return r_isHeartPushed;
    }

    public void setR_isHeartPushed(boolean r_isHeartPushed) {
        this.r_isHeartPushed = r_isHeartPushed;
    }


}
