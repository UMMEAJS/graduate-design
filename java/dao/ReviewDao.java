package dao;

import domain.Review;
import domain.Page;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.QueryRunner;

public class ReviewDao {
    private QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());

    public void add(Review review) {
        try {
            String sql = "insert into review(id,email,isbn,date,review,star) values(?,?,?,?,?,?)";
            Object[] params = {review.getId(), review.getEmail(), review.getIsbn(), review.getDate(), review.getReview(), review.getStar()};
            runner.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(String id) {
        try {
            String sql = "delete from review where id=?";
            runner.update(sql, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteByIsbn(String isbn) {
        try {
            String sql = "delete from review where isbn=?";
            runner.update(sql, isbn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void edit(Review review) {
        try {
            String sql = "update review set date=?,review=?,star=? where id=?";
            Object[] params = {review.getDate(), review.getReview(), review.getStar(), review.getId()};
            runner.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Review find(String id) {
        try {
            String sql = "select * from review where id=?";
            return runner.query(sql, new BeanHandler<>(Review.class), id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Page<Review> query(Review review, int currPage, int pageRecord) {
        try {
            Page<Review> page = new Page<>();
            page.setCurrPage(currPage);

            StringBuilder cntSql = new StringBuilder("select count(*) from review");
            StringBuilder whereSql = new StringBuilder(" where 1=1 ");
            List<Object> params = new ArrayList<>();

            String str = review.getReview();
            if (str != null && !str.trim().isEmpty()) {
                whereSql.append(" and review like ? ");
                params.add("%" + str + "%");
            }

            String isbn = review.getIsbn();
            if (isbn != null && !isbn.trim().isEmpty()) {
                whereSql.append(" and isbn=? ");
                params.add(isbn);
            }

            Number number = runner.query(cntSql.append(whereSql).toString(), new ScalarHandler<>(), params.toArray());
            int totalRecord = number.intValue();
            page.setTotalPage((int)Math.ceil((double)totalRecord / pageRecord));

            StringBuilder recordSql = new StringBuilder("select * from review");
            String limitSql = " order by id limit ?,?";
            params.add((currPage - 1) * pageRecord);
            params.add(pageRecord);
            List<Review> reviews = runner.query(recordSql.append(whereSql).append(limitSql).toString(), new BeanListHandler<>(Review.class), params.toArray());
            page.setBeanList(reviews);

            return page;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
