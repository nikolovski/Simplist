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
public class Weight {
    @JsonProperty
    private String unit;
    @JsonProperty
    private double value;
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "Weight{" +
                "unit='" + unit + '\'' +
                ", value=" + value +
                '}';
    }

}
