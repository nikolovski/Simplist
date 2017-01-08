package com.n00b5.simplist.api.ebay.inventory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Project: Simplist
 *
 * @author Martino Nikolovski
 * @date 1/5/17
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PickupAtLocationAvailability {
    @JsonProperty
    private String availabilityType;
    @JsonProperty
    private TimeDuration fulfillmentTime;
    @JsonProperty
    private String merchantLocationKey;
    @JsonProperty
    private int quantity;

    public String getAvailabilityType() {
        return availabilityType;
    }

    public void setAvailabilityType(String availabilityType) {
        this.availabilityType = availabilityType;
    }

    public TimeDuration getFulfillmentTime() {
        return fulfillmentTime;
    }

    public void setFulfillmentTime(TimeDuration fulfillmentTime) {
        this.fulfillmentTime = fulfillmentTime;
    }

    public String getMerchantLocationKey() {
        return merchantLocationKey;
    }

    public void setMerchantLocationKey(String merchantLocationKey) {
        this.merchantLocationKey = merchantLocationKey;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PickupAtLocationAvailability{" +
                "availabilityType=" + availabilityType +
                ", fulfillmentTime=" + fulfillmentTime +
                ", merchantLocationKey='" + merchantLocationKey + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
