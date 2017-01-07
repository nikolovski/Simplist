package com.n00b5.simplist.data;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Shehar on 1/7/2017.
 */
public class DAO {

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}



    @Transactional(	isolation= Isolation.READ_COMMITTED,
            propagation= Propagation.REQUIRED,
            rollbackFor=Exception.class)
    public void insert(Object obj){
        sessionFactory.getCurrentSession().save(obj);
    }

}