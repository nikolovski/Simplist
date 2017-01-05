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
public class PackageWeightAndSize {
    @JsonProperty
    private Dimension dimension;
    @JsonProperty
    private String packageType;
    @JsonProperty
    private Weight weight;

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }
    @Override
    public String toString() {
        return "PackageWeightAndSize{" +
                "dimension=" + dimension +
                ", packageType='" + packageType + '\'' +
                ", weight=" + weight +
                '}';
    }

}
