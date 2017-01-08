package com.n00b5.simplist.api.ebay.offer;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/7/17
 */
public class Tax {
    @JsonProperty
    private boolean applyTax;
    @JsonProperty
    private String thirdPartyTaxCategory;
    @JsonProperty
    private double vatPercentage;

    public boolean isApplyTax() {
        return applyTax;
    }

    public void setApplyTax(boolean applyTax) {
        this.applyTax = applyTax;
    }

    public String getThirdPartyTaxCategory() {
        return thirdPartyTaxCategory;
    }

    public void setThirdPartyTaxCategory(String thirdPartyTaxCategory) {
        this.thirdPartyTaxCategory = thirdPartyTaxCategory;
    }

    public double getVatPercentage() {
        return vatPercentage;
    }

    public void setVatPercentage(double vatPercentage) {
        this.vatPercentage = vatPercentage;
    }

    @Override
    public String toString() {
        return "Tax{" +
                "applyTax=" + applyTax +
                ", thirdPartyTaxCategory='" + thirdPartyTaxCategory + '\'' +
                ", vatPercentage=" + vatPercentage +
                '}';
    }
}
