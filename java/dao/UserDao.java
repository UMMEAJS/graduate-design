package dao;

import domain.User;
import domain.Page;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.QueryRunner;

public class UserDao {
    private QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());

    public void add(User user) {
        try {
            String sql = "insert into user(id,name,password,email) values(?,?,?,?)";
            Object[] params = {user.getId(), user.getName(), user.getPassword(), user.getEmail()};
            runner.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String id) {
        try {
            String sql = "delete from user where id=?";
            runner.update(sql, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void edit(User user) {
        try {
            String sql = "update user set name=?,password=?,email=? where id=?";
            Object[] params = {user.getName(), user.getPassword(), user.getEmail(), user.getId()};
            runner.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User find(String id) {
        try {
            String sql = "select * from user where id=?";
            return runner.query(sql, new BeanHandler<>(User.class), id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Page<User> query(User user, int currPage, int pageRecord) {
        try {
            Page<User> page = new Page<>();
            page.setCurrPage(currPage);

            StringBuilder cntSql = new StringBuilder("select count(*) from user");
            StringBuilder whereSql = new StringBuilder(" where 1=1 ");
            List<Object> params = new ArrayList<>();

            String name = user.getName();
            if (name != null && !name.trim().isEmpty()) {
                whereSql.append(" and name like ? ");
                params.add("%" + name + "%");
            }

            Number number = runner.query(cntSql.append(whereSql).toString(), new ScalarHandler<>(), params.toArray());
            int totalRecord = number.intValue();
            page.setTotalPage((int)Math.ceil((double)totalRecord / pageRecord));

            StringBuilder recordSql = new StringBuilder("select * from user");
            String limitSql = " order by id limit ?,?";
            params.add((currPage - 1) * pageRecord);
            params.add(pageRecord);
            List<User> users = runner.query(recordSql.append(whereSql).append(limitSql).toString(), new BeanListHandler<>(User.class), params.toArray());
            page.setBeanList(users);

            return page;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isExist(User user) {
        try {
            StringBuilder cntSql = new StringBuilder("select count(*) from user");
            StringBuilder whereSql = new StringBuilder(" where 1=1 ");
            List<Object> params = new ArrayList<>();

            String email = user.getEmail();
            String password = user.getPassword();

            if (email == null || password == null) {
                return false;
            }

            if (email != null && !email.trim().isEmpty()) {
                whereSql.append(" and email=? ");
                params.add(email);
            }

            if (password != null && !password.trim().isEmpty()) {
                whereSql.append(" and password=? ");
                params.add(password);
            }

            Number number = runner.query(cntSql.append(whereSql).toString(), new ScalarHandler<>(), params.toArray());
            int totalRecord = number.intValue();

            return totalRecord > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
