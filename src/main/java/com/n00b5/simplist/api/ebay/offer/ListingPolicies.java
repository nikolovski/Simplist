package com.n00b5.simplist.api.ebay.offer;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/7/17
 */
public class ListingPolicies {
    @JsonProperty
    private String fulfillmentPolicyId;
    @JsonProperty
    private String paymentPolicyId;
    @JsonProperty
    private String returnPolicyId;

    public String getFulfillmentPolicyId() {
        return fulfillmentPolicyId;
    }

    public void setFulfillmentPolicyId(String fulfillmentPolicyId) {
        this.fulfillmentPolicyId = fulfillmentPolicyId;
    }

    public String getPaymentPolicyId() {
        return paymentPolicyId;
    }

    public void setPaymentPolicyId(String paymentPolicyId) {
        this.paymentPolicyId = paymentPolicyId;
    }

    public String getReturnPolicyId() {
        return returnPolicyId;
    }

    public void setReturnPolicyId(String returnPolicyId) {
        this.returnPolicyId = returnPolicyId;
    }

    @Override
    public String toString() {
        return "ListingPolicies{" +
                "fulfillmentPolicyId='" + fulfillmentPolicyId + '\'' +
                ", paymentPolicyId='" + paymentPolicyId + '\'' +
                ", returnPolicyId='" + returnPolicyId + '\'' +
                '}';
    }
}
