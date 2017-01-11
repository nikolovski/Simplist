package com.n00b5.simplist.beans;

import com.n00b5.simplist.api.Shopify.ShopifyItem;
import com.n00b5.simplist.api.etsy.EtsyItem;

import javax.persistence.*;

/**
 * Created by louislopez on 1/11/17.
 */

@Entity
@Table(name = "SIMPLIST_ITEM")
public class SimplistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private ShopifyItem shopifyItem;

    @OneToOne
    private EtsyItem etsyItem;

    @OneToOne
    private User user;


    public SimplistItem() {
    }

    public SimplistItem(ShopifyItem shopifyItem, EtsyItem etsyItem, User user) {
        this.shopifyItem = shopifyItem;
        this.etsyItem = etsyItem;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ShopifyItem getShopifyItem() {
        return shopifyItem;
    }

    public void setShopifyItem(ShopifyItem shopifyItem) {
        this.shopifyItem = shopifyItem;
    }

    public EtsyItem getEtsyItem() {
        return etsyItem;
    }

    public void setEtsyItem(EtsyItem etsyItem) {
        this.etsyItem = etsyItem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SimplistItem{" +
                "id=" + id +
                ", shopifyItem=" + shopifyItem +
                ", etsyItem=" + etsyItem +
                ", user=" + user +
                '}';
    }
}
