package domain;

public class Textbook {
    private String isbn;
    private String name;
    private String genre;
    private int star;

    public String getISBN() {
        return this.isbn;
    }

    public String getName() {
        return this.name;
    }

    public String getGenre() {
        return this.genre;
    }

    public int getStar() {
        return this.star;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
