package com.n00b5.simplist.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty
    private int id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JsonProperty
    private ShopifyItem shopifyItem;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JsonProperty
    private EtsyItem etsyItem;



    public SimplistItem() {
    }

    public SimplistItem(ShopifyItem shopifyItem, EtsyItem etsyItem) {
        this.shopifyItem = shopifyItem;
        this.etsyItem = etsyItem;
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


    @Override
    public String toString() {
        return "SimplistItem{" +
                "id=" + id +
                ", shopifyItem=" + shopifyItem +
                ", etsyItem=" + etsyItem +
                '}';
    }
}
