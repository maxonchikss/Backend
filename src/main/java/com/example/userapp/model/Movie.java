package com.example.userapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {
    private Integer id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("userId")
    private Integer userId;

    public Movie() {}

    public Movie(Integer id, String title, Integer userId) {
        this.id = id;
        this.title = title;
        this.userId = userId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
}