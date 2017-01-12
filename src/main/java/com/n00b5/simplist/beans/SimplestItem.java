package com.n00b5.simplist.beans;

import com.n00b5.simplist.api.Shopify.ShopifyItem;

import javax.persistence.*;

/**
 * Created by Shehar on 1/11/2017.
 */
@Entity
@Table(name="SIMPLEST_ITEM")
@SuppressWarnings("JpaDataSourceORMInspection")
public class SimplestItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @OneToOne(cascade = CascadeType.ALL)
    private ShopifyItem shopify;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public ShopifyItem getShopify() {
        return shopify;
    }

    public void setShopify(ShopifyItem shopify) {
        this.shopify = shopify;
    }


    public SimplestItem( ShopifyItem shopify, User user) {
        this.shopify = shopify;
    }
    public SimplestItem(){

    }

    @Override
    public String toString() {
        return "SimplestItem{" +
                "id=" + id +
                ", shopify=" + shopify +
                '}';
    }
}
