package com.oncb.service;

import com.oncb.pojo.Page;
import com.oncb.pojo.User;
import com.oncb.dao.UserDao;

public class UserService {
    private static final UserDao userDao = new UserDao();

    public void add(User user) {
        userDao.add(user);
    }

    public void delete(String email) {
        userDao.delete(email);
    }

    public void edit(User user) {
        userDao.edit(user);
    }

    public User find(String email) {
        return userDao.find(email);
    }

    public Page<User> query(User user, int currPage, int pageRecord) {
        return userDao.query(user, currPage, pageRecord);
    }

    public boolean isExist(User user) {
        return userDao.isExist(user);
    }

    public boolean  verify(User user) {
        return userDao.verify(user);
    }
}
