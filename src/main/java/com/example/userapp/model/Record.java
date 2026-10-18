package com.example.userapp.model;

public class Record {
    private Integer id;
    private String title;
    private Integer year;
    private Double price;
    private Integer artistId;

    public Record() {}

    public Record(Integer id, String title, Integer year, Double price, Integer artistId) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.price = price;
        this.artistId = artistId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getArtistId() { return artistId; }
    public void setArtistId(Integer artistId) { this.artistId = artistId; }
}