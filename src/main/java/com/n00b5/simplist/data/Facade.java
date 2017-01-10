package com.n00b5.simplist.data;

import com.n00b5.simplist.api.etsy.EtsyItem;
import com.n00b5.simplist.beans.User;


public class Facade {
    private UserDAO userDAO;
    private EtsyDAO etsyDAO;

    public void setUser(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setEtsyDAO(EtsyDAO etsyDAO) {
        this.etsyDAO = etsyDAO;
    }

    public UserDAO getUser() {
        return userDAO;
    }

    public void insertUser(User user) {
        userDAO.insert(user);
    }

    public void setDao(EtsyDAO etsyDAO) {
        this.etsyDAO = etsyDAO;
    }


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
        etsyDAO.etsyUpdateItem(etsyItem, listing_id);
    }

    public void etsyGetAll() {
        System.out.println("IN FACADE GET ALL");
        etsyDAO.getAll();
    }

    public User getUserById(int id) throws InterruptedException {
        return userDAO.getById(id);
    }

}
