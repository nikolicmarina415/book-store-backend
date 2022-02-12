package com.sofija.bookstore.data;

public class BookData {

    private int id;
    private GenreData genreData;
    private AuthorData authorData;
    private String imagePath;
    private String title;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GenreData getGenreData() {
        return genreData;
    }

    public void setGenreData(GenreData genreData) {
        this.genreData = genreData;
    }

    public AuthorData getAuthorData() {
        return authorData;
    }

    public void setAuthorData(AuthorData authorData) {
        this.authorData = authorData;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
