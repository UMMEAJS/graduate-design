package com.oncb.pojo;

public class Textbook {
    private String isbn;
    private String name;
    private String genre;
    private int count;
    private double star;

    public String getIsbn() {
        return this.isbn;
    }

    public String getName() {
        return this.name;
    }

    public String getGenre() {
        return this.genre;
    }

    public int getCount() {
        return this.count;
    }

    public double getStar() {
        return this.star;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setStar(double star) {
        this.star = star;
    }
}
