package com.github.ddekanski.spiceannotations.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FacebookPage  {

    private String name;
    private String about;
    private String phone;
    private String website;
    private int likes;
    private String category;

    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public int getLikes() {
        return likes;
    }

    public String getCategory() {
        return category;
    }
}