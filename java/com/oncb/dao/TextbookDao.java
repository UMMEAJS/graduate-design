package com.oncb.dao;

import com.oncb.pojo.Page;
import com.oncb.pojo.Textbook;
import com.oncb.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.ArrayList;
import java.util.List;

public class TextbookDao {
    private QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());

    public void add(Textbook textbook) {
        try {
            String sql = "insert into textbook(isbn,name,genre,count,star) values(?,?,?,?,?)";
            Object[] params = {textbook.getIsbn(), textbook.getName(), textbook.getGenre(), textbook.getCount(), textbook.getStar()};
            runner.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String isbn) {
        try {
            String sql = "delete from textbook where isbn=?";
            runner.update(sql, isbn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void edit(Textbook textbook) {
        try {
            String sql = "update textbook set name=?,genre=?,count=?,star=? where isbn=?";
            Object[] params = {textbook.getName(), textbook.getGenre(), textbook.getCount(), textbook.getStar(), textbook.getIsbn()};
            runner.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Textbook find(String isbn) {
        try {
            String sql = "select * from textbook where isbn=?";
            return runner.query(sql, new BeanHandler<>(Textbook.class), isbn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Page<Textbook> query(Textbook textbook, int currPage, int pageRecord) {
        try {
            Page<Textbook> page = new Page<>();
            page.setCurrPage(currPage);

            StringBuilder cntSql = new StringBuilder("select count(*) from textbook");
            StringBuilder whereSql = new StringBuilder(" where 1=1 ");
            List<Object> params = new ArrayList<>();

            String name = textbook.getName();
            if (name != null && !name.trim().isEmpty()) {
                whereSql.append(" and name like ? ");
                params.add("%" + name + "%");
            }

            String genre = textbook.getGenre();
            if (genre != null && !genre.trim().isEmpty()) {
                whereSql.append(" and genre=? ");
                params.add(genre);
            }

            Number number = runner.query(cntSql.append(whereSql).toString(), new ScalarHandler<>(), params.toArray());
            int totalRecord = number.intValue();
            page.setTotalPage((int)Math.ceil((double)totalRecord / pageRecord));

            StringBuilder recordSql = new StringBuilder("select * from textbook");
            String limitSql = " order by isbn limit ?,?";
            params.add((currPage - 1) * pageRecord);
            params.add(pageRecord);
            List<Textbook> textbooks = runner.query(recordSql.append(whereSql).append(limitSql).toString(), new BeanListHandler<>(Textbook.class), params.toArray());
            page.setBeanList(textbooks);

            return page;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
