package service;

import dao.ReviewDao;
import domain.Review;
import domain.Page;

public class ReviewService {
    private ReviewDao reviewDao = new ReviewDao();

    public void add(Review review) {
        reviewDao.add(review);
    }

    public void delete(String id) {
        reviewDao.delete(id);
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
