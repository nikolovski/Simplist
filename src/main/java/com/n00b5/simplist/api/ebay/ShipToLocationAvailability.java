package com.n00b5.simplist.api.ebay;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Project: Simplist
 *
 * @author Martino Nikolovski
 * @date 1/5/17
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShipToLocationAvailability {
    @JsonProperty
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShipToLocationAvailability{" +
                "quantity=" + quantity +
                '}';
    }
}
