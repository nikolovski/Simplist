package com.n00b5.simplist.data;

import com.n00b5.simplist.beans.SimplistItem;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by louislopez on 1/11/17.
 */
public class SimplistItemDAO {


    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void createItem(SimplistItem item){
        System.out.println("IN THE SIMPLES DAO ... ADDING TO DB");
        sessionFactory.getCurrentSession().save(item);
    }

    @Transactional
    public void deleteItem(String deleteId) {
        System.out.println("IN DAO FOR DELETE " + deleteId);
       SimplistItem simplistItem = (SimplistItem) sessionFactory.getCurrentSession().load(SimplistItem.class, Integer.parseInt(deleteId));
//        System.out.println("SIMPLEST ITEM " + simplistItem.toString());
       sessionFactory.getCurrentSession().delete(simplistItem);
       System.out.println("DELETED SIMPLIST ITEM");
        //TODO get delete to work !
    }
}
