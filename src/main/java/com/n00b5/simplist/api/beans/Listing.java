package com.n00b5.simplist.api.beans;

/**
 * Created by Shehar on 1/5/2017.
 */
public class Listing {
    private int id;
    private int userId;
    private int ebayId;
    private int etsyId;
    private int shopifyId;

    public Listing(int id, int userId, int ebayId, int etsyId, int shopifyId) {
        this.id = id;
        this.userId = userId;
        this.ebayId = ebayId;
        this.etsyId = etsyId;
        this.shopifyId = shopifyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEbayId() {
        return ebayId;
    }

    public void setEbayId(int ebayId) {
        this.ebayId = ebayId;
    }

    public int getEtsyId() {
        return etsyId;
    }

    public void setEtsyId(int etsyId) {
        this.etsyId = etsyId;
    }

    public int getShopifyId() {
        return shopifyId;
    }

    public void setShopifyId(int shopifyId) {
        this.shopifyId = shopifyId;
    }
}
