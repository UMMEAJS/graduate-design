package service;

import domain.User;
import domain.Page;
import dao.UserDao;

public class UserService {
    private UserDao userDao = new UserDao();

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
}
