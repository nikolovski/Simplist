package com.n00b5.simplist.data;

import com.n00b5.simplist.api.etsy.EtsyItem;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class EtsyDAO {


    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional()
    public EtsyItem etsyGetById(String id) {
        System.out.println("IN DAO GET BY ID");
        EtsyItem etsyItem = (EtsyItem) sessionFactory.getCurrentSession().load(EtsyItem.class, id);
        System.out.println("ITEM IN DAO -> " + etsyItem);
        return etsyItem;
    }


    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)

    public void etsyAddItem(EtsyItem etsyItem) {
        System.out.println("Saving this to the db -> " + etsyItem.toString());
        sessionFactory.getCurrentSession().save(etsyItem);
        System.out.println("Added to db");
    }

    @Transactional()
    public void etsyDeleteItem(String listing_id) {

        System.out.println("IN DAO DELETE");

        EtsyItem etsyItem = (EtsyItem) sessionFactory.getCurrentSession().load(EtsyItem.class, listing_id);
        sessionFactory.getCurrentSession().delete(etsyItem);

        System.out.println("deleted from db");

    }

    @Transactional()
    public void etsyUpdateItem(EtsyItem updatedEtsyItem, String listing_id) {
        System.out.println("IN DAO UPDATE");
        EtsyItem dbEtsyItem = (EtsyItem) sessionFactory.getCurrentSession().load(EtsyItem.class, listing_id);
        System.out.println("DB ITEM IS -> " + dbEtsyItem);

        dbEtsyItem.setTitle(updatedEtsyItem.getTitle());
        dbEtsyItem.setDescription(updatedEtsyItem.getDescription());
        dbEtsyItem.setQuantity(updatedEtsyItem.getQuantity());

        System.out.println("ABOUT TO UPDATE THE DB");
        sessionFactory.getCurrentSession().update(dbEtsyItem);
        System.out.println("ADDED TO THE DB");
    }


    @Transactional()
    public void getAll() {
        List<EtsyItem> list = sessionFactory.getCurrentSession().createCriteria(EtsyItem.class).list();


    }
}

