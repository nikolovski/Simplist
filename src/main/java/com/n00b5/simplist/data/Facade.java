package com.n00b5.simplist.data;

import com.n00b5.simplist.api.etsy.EtsyItem;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public class Facade {

    private EtsyDAO etsyDAO;

    public void setDao(EtsyDAO etsyDAO) { this.etsyDAO = etsyDAO;}


    @Transactional(isolation= Isolation.READ_COMMITTED,
            rollbackFor=Exception.class,
            propagation= Propagation.REQUIRES_NEW)
    public void etsyAddItem(EtsyItem etsyItem) {
        etsyDAO.etsyAddItem(etsyItem);
    }

    public void etsyDeleteItem(String listing_id) {
        System.out.println("IN FACADE DELETE");
        etsyDAO.etsyDeleteItem(listing_id);
    }

    public EtsyItem etsyGetById(String id) {
        System.out.println("IN FACADE GET BY ID");
        return etsyDAO.etsyGetById(id);
    }

    public void etsyUpdateItem(EtsyItem etsyItem, String listing_id) {
        System.out.println("IN FACADE UPDATE");
        etsyDAO.etsyUpdateItem(etsyItem,listing_id);
    }

    public void setEtsyDAO(EtsyDAO etsyDAO) {
        this.etsyDAO = etsyDAO;
    }

    public void etsyGetAll() {
        System.out.println("IN FACADE GET ALL");
        etsyDAO.getAll();
    }
}
