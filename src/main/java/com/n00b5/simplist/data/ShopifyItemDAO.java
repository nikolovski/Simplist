package com.n00b5.simplist.data;

import com.n00b5.simplist.api.Shopify.ShopifyItem;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Shehar on 1/9/2017.
 */
public class ShopifyItemDAO {

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}



    //create
    @Transactional(	isolation= Isolation.READ_COMMITTED,
            propagation= Propagation.REQUIRED,
            rollbackFor=Exception.class)
    public void addShopifyItem(ShopifyItem item) {
        try {
            sessionFactory.getCurrentSession().save(item);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    //read
    @Transactional()
    public ShopifyItem getById(String id){
        return (ShopifyItem) sessionFactory.getCurrentSession().load(ShopifyItem.class,id);
    }

    //update
    @Transactional()
    public void shopifyUpdateItem(ShopifyItem shopifyItem, String id) {
        ShopifyItem itemToUpdate = (ShopifyItem) sessionFactory.getCurrentSession().load(ShopifyItem.class,id);
        itemToUpdate.setBody_html(shopifyItem.getBody_html());
        itemToUpdate.setProduct_type(shopifyItem.getProduct_type());
        itemToUpdate.setTags(shopifyItem.getTags());
        itemToUpdate.setTitle(shopifyItem.getTitle());
        itemToUpdate.setVariants(shopifyItem.getVariants());
        itemToUpdate.setVendor(shopifyItem.getVendor());
        sessionFactory.getCurrentSession().update(itemToUpdate);
    }

    //delete
    @Transactional()
    public void shopifyDeleteItem(String shopifyId) {
        ShopifyItem item = (ShopifyItem) sessionFactory.getCurrentSession().load(ShopifyItem.class,shopifyId);
        sessionFactory.getCurrentSession().delete(item);
    }



}
