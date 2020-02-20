package domain;

import java.util.Date;

public class Review {
    private String rid;
    private String uid;
    private String isbn;
    private Date date;
    private String review;
    private int star;

    public String getRid() {
        return this.rid;
    }

    public String getUid() {
        return this.uid;
    }

    public String getIsbn() {
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

    public void setRid(String rid) {
        this.rid = rid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setIsbn(String isbn) {
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
