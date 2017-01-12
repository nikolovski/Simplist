package com.n00b5.simplist.data;

import com.n00b5.simplist.api.Shopify.ShopifyItem;
import com.n00b5.simplist.api.etsy.EtsyItem;
import com.n00b5.simplist.beans.SimplistItem;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by louislopez on 1/11/17.
 */
public class SimplestItemDAO {


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
    public void deleteItem(int deleteId) {
        System.out.println("IN DAO FOR DELETE " + deleteId);
       SimplistItem simplistItem = (SimplistItem) sessionFactory.getCurrentSession().load(SimplistItem.class, deleteId);
//        System.out.println("SIMPLEST ITEM " + simplistItem.toString());
       sessionFactory.getCurrentSession().delete(simplistItem);
       System.out.println("DELETED SIMPLIST ITEM");
        //TODO get delete to work !
    }

    @Transactional()
    public SimplistItem getById(int id) {
        System.out.println("in my DAO for getBYId");
        SimplistItem item = (SimplistItem) sessionFactory.getCurrentSession().get(SimplistItem.class, id);
        return item;
    }

    @Transactional()
    public void updateSimplistItem(int id, EtsyItem etsyItem, ShopifyItem shopifyItem) {
        SimplistItem item = (SimplistItem) sessionFactory.getCurrentSession().load(SimplistItem.class,id);

        item.setShopifyItem(shopifyItem);
        item.setEtsyItem(etsyItem);

        System.out.println("ABOUT TO UPDATE THE DB");
        sessionFactory.getCurrentSession().update(item);
        System.out.println("Simoplist Item: " + item.toString());
        System.out.println("ADDED TO THE DB SIMPLIST ITEM UPDATED");
    }


}
