package com.n00b5.simplist.api.Shopify;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Shehar on 1/7/2017.
 */
public class ShopifyProductImage {

    @JsonProperty
    private String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
