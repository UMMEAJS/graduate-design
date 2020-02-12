package domain;

import java.util.Date;

public class Review {
    private String rid;
    private String uid;
    private String isbn;
    private Date date;
    private String review;
    private int star;

    public String getRID() {
        return this.rid;
    }

    public String getUID() {
        return this.uid;
    }

    public String getISBN() {
        return this.isbn;
    }

    public Date getDate() {
        return this.date;
    }

    public String getReview() {
        return this.review;
    }

    public int getStar() {
        return this.star;
    }

    public void setRID(String rid) {
        this.rid = rid;
    }

    public void setUID(String uid) {
        this.uid = uid;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
