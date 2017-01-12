package com.n00b5.simplist.data;

import com.n00b5.simplist.api.Shopify.ShopifyItem;
import com.n00b5.simplist.api.ebay.EbayToken;
import com.n00b5.simplist.api.etsy.EtsyItem;
import com.n00b5.simplist.api.etsy.EtsyToken;
import com.n00b5.simplist.beans.SimplistItem;
import com.n00b5.simplist.beans.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class Facade {
    private UserDAO userDAO;
    private EtsyDAO etsyDAO;
    private ShopifyItemDAO shopifyDAO;
    private TokensDAO tokensDAO;
    private SimplestItemDAO simplestDAO;


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

    public void setSimplestDAO(SimplestItemDAO SimplestDAO) {
        simplestDAO = SimplestDAO;
    }

    public SimplestItemDAO getSimplestDAO() {
        return simplestDAO;
    }


    public void etsyAddItem(EtsyItem etsyItem) {
        System.out.println("IN ETSY FACADE");
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
    public User getUserByEmail(String email){
        return userDAO.getByEmail(email);
    }

    /**
     *
     * Shopify facade
     */
    public void setShopifyDAO(ShopifyItemDAO shopifyDAO) {
        this.shopifyDAO = shopifyDAO;
    }

    public ShopifyItemDAO getShopifyDAO() {
        return shopifyDAO;
    }


    //create
    public void addShopifyItem(ShopifyItem item) {
        shopifyDAO.addShopifyItem(item);
    }

    //read
    @Transactional()
    public ShopifyItem getById(String id){
        return shopifyDAO.getById(id);
    }

    //update
    @Transactional()
    public void shopifyUpdateItem(ShopifyItem shopifyItem, String id) {
        shopifyDAO.shopifyUpdateItem(shopifyItem,id);
    }

    //delete
    @Transactional()
    public void shopifyDeleteItem(String shopifyId) {
        shopifyDAO.shopifyDeleteItem(shopifyId);
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }
    public void insertEbayToken(EbayToken token) {
        tokensDAO.insertEbayToken(token);
    }

    public void setTokensDAO(TokensDAO tokensDAO) {
        this.tokensDAO = tokensDAO;
    }

    public TokensDAO getTokensDAO() {
        return tokensDAO;
    }

    public void insertEtsyToken(EtsyToken token) {
        tokensDAO.insertEtsyToken(token);
    }
    //create
    @Transactional()
    public void simpliestCreateItem(SimplistItem simplistItem) {
        System.out.println("IN THE SIMPLEST FACADE");
        simplestDAO.createItem(simplistItem);
    }

    //delete
    @Transactional()
    public void simpliestDeleteItem(int deleteId) {
        System.out.println("IN THE SIMPLEST FACADE");
        simplestDAO.deleteItem(deleteId);
    }

    //getById (simplest Item)
    @Transactional()
    public SimplistItem getSimplestItemById(int id) {
        return simplestDAO.getById(id);
    }

    //updateByid (simplest Item)
    @Transactional()
    public void updateSimplistItem(int id, EtsyItem item, ShopifyItem item2) {
        simplestDAO.updateSimplistItem(id,item,item2);
    }

    @Transactional()
    public List<SimplistItem> getItemsByUserID(User user) {
        return simplestDAO.getItemsByUserID(user);
    }
}
