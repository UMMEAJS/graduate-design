package com.oncb.service;

import com.oncb.pojo.Page;
import com.oncb.pojo.Review;
import com.oncb.dao.ReviewDao;

public class ReviewService {
    private ReviewDao reviewDao = new ReviewDao();

    public void add(Review review) {
        reviewDao.add(review);
    }

    public void deleteById(String id) {
        reviewDao.deleteById(id);
    }

    public void deleteByIsbn(String id) {
        reviewDao.deleteByIsbn(id);
    }

    public void edit(Review review) {
        reviewDao.edit(review);
    }

    public Review find(String id) {
        return reviewDao.find(id);
    }

    public Page<Review> query(Review review, int currPage, int pageRecord) {
        return reviewDao.query(review, currPage, pageRecord);
    }
}
