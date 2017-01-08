package com.n00b5.simplist.api.ebay.offer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/7/17
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Offer {
    @JsonProperty
    private String offerId;
    @JsonProperty
    private String status;
    @JsonProperty
    private int availableQuantity;
    @JsonProperty
    private String categoryId;
    @JsonProperty
    private String format;
    @JsonProperty
    private String listingDescription;
    @JsonProperty
    private ListingPolicies listingPolicies;
    @JsonProperty
    private String marketplaceId;
    @JsonProperty
    private String merchantLocationKey;
    @JsonProperty
    private PricingSummary pricingSummary;
    @JsonProperty
    private int quantityLimitPerBuyer;
    @JsonProperty
    private String sku;
    @JsonProperty
    private String[] storeCategoryNames;
    @JsonProperty
    private Tax tax;

    public Offer() {
    }

    public Offer(int availableQuantity,
                 String categoryId,
                 String format,
                 String listingDescription,
                 ListingPolicies listingPolicies,
                 String marketplaceId,
                 String merchantLocationKey,
                 PricingSummary pricingSummary,
                 String sku) {
        this.availableQuantity = availableQuantity;
        this.categoryId = categoryId;
        this.format = format;
        this.listingDescription = listingDescription;
        this.listingPolicies = listingPolicies;
        this.marketplaceId = marketplaceId;
        this.merchantLocationKey = merchantLocationKey;
        this.pricingSummary = pricingSummary;
        this.sku = sku;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getListingDescription() {
        return listingDescription;
    }

    public void setListingDescription(String listingDescription) {
        this.listingDescription = listingDescription;
    }

    public ListingPolicies getListingPolicies() {
        return listingPolicies;
    }

    public void setListingPolicies(ListingPolicies listingPolicies) {
        this.listingPolicies = listingPolicies;
    }

    public String getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    public String getMerchantLocationKey() {
        return merchantLocationKey;
    }

    public void setMerchantLocationKey(String merchantLocationKey) {
        this.merchantLocationKey = merchantLocationKey;
    }

    public PricingSummary getPricingSummary() {
        return pricingSummary;
    }

    public void setPricingSummary(PricingSummary pricingSummary) {
        this.pricingSummary = pricingSummary;
    }

    public int getQuantityLimitPerBuyer() {
        return quantityLimitPerBuyer;
    }

    public void setQuantityLimitPerBuyer(int quantityLimitPerBuyer) {
        this.quantityLimitPerBuyer = quantityLimitPerBuyer;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String[] getStoreCategoryNames() {
        return storeCategoryNames;
    }

    public void setStoreCategoryNames(String[] storeCategoryNames) {
        this.storeCategoryNames = storeCategoryNames;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "offerId='" + offerId + '\'' +
                ", status='" + status + '\'' +
                ", availableQuantity=" + availableQuantity +
                ", categoryId='" + categoryId + '\'' +
                ", format='" + format + '\'' +
                ", listingDescription='" + listingDescription + '\'' +
                ", listingPolicies=" + listingPolicies +
                ", marketplaceId='" + marketplaceId + '\'' +
                ", merchantLocationKey='" + merchantLocationKey + '\'' +
                ", pricingSummary=" + pricingSummary +
                ", quantityLimitPerBuyer=" + quantityLimitPerBuyer +
                ", sku='" + sku + '\'' +
                ", storeCategoryNames=" + Arrays.toString(storeCategoryNames) +
                ", tax=" + tax +
                '}';
    }
}
