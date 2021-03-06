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
public class TimeDuration {
    @JsonProperty
    private String unit;
    @JsonProperty
    private int value;

    public String getUnit() {
        return unit;
    }

    /**
     * @param unit
     * @see TimeDuration
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "TimeDuration{" +
                "unit=" + unit +
                ", value=" + value +
                '}';
    }
}
