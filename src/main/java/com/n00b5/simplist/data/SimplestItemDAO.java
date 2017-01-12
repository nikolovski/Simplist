package com.n00b5.simplist.data;

import com.n00b5.simplist.beans.SimplistItem;
import org.hibernate.SessionFactory;

/**
 * Created by louislopez on 1/11/17.
 */
public class SimplestItemDAO {


    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void createItem(SimplistItem item){
        System.out.println("IN THE SIMPLES DAO ... ADDING TO DB");
        sessionFactory.getCurrentSession().save(item);
    }

}
