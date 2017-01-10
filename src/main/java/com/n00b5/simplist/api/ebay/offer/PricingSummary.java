package com.n00b5.simplist.api.ebay.offer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/7/17
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PricingSummary {
    @JsonProperty
    private Amount amount;
    @JsonProperty
    private String originallySoldForRetailPriceOn;
    @JsonProperty
    private Amount originalRetailPrice;
    @JsonProperty
    private Amount price;
    @JsonProperty
    private String pricingVisibility;


    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getOriginallySoldForRetailPriceOn() {
        return originallySoldForRetailPriceOn;
    }

    /**
     * @param originallySoldForRetailPriceOn
     * @see com.n00b5.simplist.api.ebay.enums.SoldOnEnum
     */
    public void setOriginallySoldForRetailPriceOn(String originallySoldForRetailPriceOn) {
        this.originallySoldForRetailPriceOn = originallySoldForRetailPriceOn;
    }

    public Amount getOriginalRetailPrice() {
        return originalRetailPrice;
    }

    public void setOriginalRetailPrice(Amount originalRetailPrice) {
        this.originalRetailPrice = originalRetailPrice;
    }

    public Amount getPrice() {
        return price;
    }

    public void setPrice(Amount price) {
        this.price = price;
    }

    public String getPricingVisibility() {
        return pricingVisibility;
    }

    /**
     * @param pricingVisibility
     * @see com.n00b5.simplist.api.ebay.enums.MinimumAdvertisedPriceHandlingEnum
     */
    public void setPricingVisibility(String pricingVisibility) {
        this.pricingVisibility = pricingVisibility;
    }

    @Override
    public String toString() {
        return "PricingSummary{" +
                "amount=" + amount +
                ", originallySoldForRetailPriceOn='" + originallySoldForRetailPriceOn + '\'' +
                ", originalRetailPrice=" + originalRetailPrice +
                ", price=" + price +
                ", pricingVisibility='" + pricingVisibility + '\'' +
                '}';
    }
}
