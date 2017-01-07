package com.n00b5.simplist.api.ebay.location;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/7/17
 */
public class InventoryLocations {
    @JsonProperty
    private String href;
    @JsonProperty
    private int limit;
    @JsonProperty
    private InventoryLocation[] locations;
    @JsonProperty
    private String next;
    @JsonProperty
    private String prev;
    @JsonProperty
    private int offset;
    @JsonProperty
    private int total;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public InventoryLocation[] getLocations() {
        return locations;
    }

    public void setLocations(InventoryLocation[] locations) {
        this.locations = locations;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "InventoryLocations{" +
                "href='" + href + '\'' +
                ", limit=" + limit +
                ", locations=" + Arrays.toString(locations) +
                ", next='" + next + '\'' +
                ", prev='" + prev + '\'' +
                ", offset=" + offset +
                ", total=" + total +
                '}';
    }
}
