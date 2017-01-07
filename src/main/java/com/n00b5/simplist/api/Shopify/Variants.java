package com.n00b5.simplist.api.Shopify;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Shehar on 1/6/2017.
 */
@Entity
@Table(name="SHOPIFY_ITEM_VARIANTS")
public class Variants {

    @Id
    @Column(name = "ITEM_ID")
    private String id;

    @Column(name="PRICE")
    @JsonProperty
    private String price;


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



}
