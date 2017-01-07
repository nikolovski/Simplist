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
public class Dimension {
    @JsonProperty
    private int height;
    @JsonProperty
    private int length;
    @JsonProperty
    private String unit;
    @JsonProperty
    private int width;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    @Override
    public String toString() {
        return "Dimension{" +
                "height=" + height +
                ", length=" + length +
                ", unit='" + unit + '\'' +
                ", width=" + width +
                '}';
    }
}
