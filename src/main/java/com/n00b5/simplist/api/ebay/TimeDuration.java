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
public class TimeDuration {
    @JsonProperty
    private String unit;
    @JsonProperty
    private int value;

    public String getUnit() {
        return unit;
    }

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
