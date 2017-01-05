package com.n00b5.simplist.api.ebay;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/5/17
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryItem {
    @JsonProperty
    private Availability availability;
    @JsonProperty
    private String condition;
    @JsonProperty
    private String conditionDescription;
    @JsonProperty
    private PackageWeightAndSize packageWeightAndSize;
    @JsonProperty
    private Product product;
    @JsonProperty
    private String sku;

    public InventoryItem() {}

    public InventoryItem(Availability availability,
                         String condition,
                         String conditionDescription,
                         PackageWeightAndSize packageWeightAndSize,
                         Product product,
                         String sku) {
        this.availability = availability;
        this.condition = condition;
        this.conditionDescription = conditionDescription;
        this.packageWeightAndSize = packageWeightAndSize;
        this.product = product;
        this.sku = sku;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getConditionDescription() {
        return conditionDescription;
    }

    public void setConditionDescription(String conditionDescription) {
        this.conditionDescription = conditionDescription;
    }

    public PackageWeightAndSize getPackageWeightAndSize() {
        return packageWeightAndSize;
    }

    public void setPackageWeightAndSize(PackageWeightAndSize packageWeightAndSize) {
        this.packageWeightAndSize = packageWeightAndSize;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
    @Override
    public String toString() {
        return "InventoryItem{" +
                "availability=" + availability +
                ", condition='" + condition + '\'' +
                ", conditionDescription='" + conditionDescription + '\'' +
                ", packageWeightAndSize=" + packageWeightAndSize +
                ", product=" + product +
                ", sku='" + sku + '\'' +
                '}';
    }
}
