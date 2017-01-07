package com.n00b5.simplist.api.beans.Shopify;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Shehar on 1/6/2017.
 */
public class Variants {

    @JsonProperty
    private String price;



    public String getPrice() {
        return price;
    }


    public void setPrice(String price) {
        this.price = price;
    }



}
