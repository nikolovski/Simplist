package com.n00b5.simplist.data;

import com.n00b5.simplist.api.etsy.EtsyItem;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public class Facade {

    public DAO dao;
    public void setDao(DAO dao) { this.dao = dao;}


    @Transactional(isolation= Isolation.READ_COMMITTED,
            rollbackFor=Exception.class,
            propagation= Propagation.REQUIRES_NEW)
    public void etsyAddItem(EtsyItem etsyItem) {
            dao.etsyAddItem(etsyItem);
    }

    public void etsyDeleteItem(String listing_id) {
        System.out.println("IN FACADE DELETE");
        dao.etsyDeleteItem(listing_id);
    }

    public EtsyItem etsyGetById(String id) {
        System.out.println("IN FACADE GET BY ID");
        return dao.etsyGetById(id);
    }

    public void etsyUpdateItem(EtsyItem etsyItem, String listing_id) {
        System.out.println("IN FACADE UPDATE");
        dao.etsyUpdateItem(etsyItem,listing_id);
    }
}
