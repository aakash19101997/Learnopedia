package com.minorproject.minorproject.models;

public class Fb_form {
    String name, mobile, rating, comments, recommend;

    public Fb_form() {
    }

    public Fb_form(String name, String mobile, String rating, String comments, String recommend) {
        this.name = name;
        this.mobile = mobile;
        this.rating = rating;
        this.comments = comments;
        this.recommend = recommend;
    }
}
