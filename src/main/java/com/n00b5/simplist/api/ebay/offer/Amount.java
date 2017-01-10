package com.n00b5.simplist.api.ebay.offer;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/7/17
 */
public class Amount {
    @JsonProperty
    private String currency;
    @JsonProperty
    private double value;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "currency='" + currency + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
