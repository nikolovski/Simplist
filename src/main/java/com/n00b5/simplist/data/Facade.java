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
    private DAO dao;
    public void setDao(DAO dao) {this.dao = dao;}

    @Transactional(isolation= Isolation.READ_COMMITTED,
            rollbackFor=Exception.class,
            propagation= Propagation.REQUIRES_NEW)

    public void insertUser(User user) throws InterruptedException{
            dao.insert(user);
            System.out.println("user inserted.. (uncommited)");

    }
}
