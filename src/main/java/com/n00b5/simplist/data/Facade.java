package com.n00b5.simplist.data;

import com.n00b5.simplist.beans.User;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project: Simplist
 *
 * @author Martino Nikolovski
 * @date 1/6/17
 */
public class Facade {
    private UserDAO userDAO;

    public void setUser(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDAO getUser() {
        return userDAO;
    }

    public void insertUser(User user) {
        userDAO.insert(user);
    }

    public User getUserById(int id) throws InterruptedException {
        return userDAO.getById(id);
    }

}
