package org.example.models;

import java.util.List;

public class BookModels {
    private String id;
    private String title;
    private String author;
    private int size;
    private String  img;
    private List<String> genres;
    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public BookModels() {
    }

    public BookModels(String id, String title, String author, int size, String img, List<String> genres, float price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.size = size;
        this.img = img;
        this.genres = genres;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
