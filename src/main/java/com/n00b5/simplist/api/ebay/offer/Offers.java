package com.n00b5.simplist.api.ebay.offer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/7/17
 */
public class Offers {
    @JsonProperty
    private int total;
    @JsonProperty
    private int size;
    @JsonProperty
    private String href;
    @JsonProperty
    private int limit;
    @JsonProperty
    private Offer[] offers;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Offer[] getOffers() {
        return offers;
    }

    public void setOffers(Offer[] offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return "Offers{" +
                "total=" + total +
                ", size=" + size +
                ", href='" + href + '\'' +
                ", limit=" + limit +
                ", offers=" + Arrays.toString(offers) +
                '}';
    }
}
