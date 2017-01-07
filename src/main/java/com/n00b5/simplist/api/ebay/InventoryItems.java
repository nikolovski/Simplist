package com.n00b5.simplist.api.ebay;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Project: Simplist
 *
 * @author Martino Nikolovski
 * @date 1/7/17
 */
public class InventoryItems {
    @JsonProperty
    private String href;
    @JsonProperty
    private InventoryItem[] inventoryItems;
    @JsonProperty
    private int limit;
    @JsonProperty
    private String next;
    @JsonProperty
    private String prev;
    @JsonProperty
    private int size;
    @JsonProperty
    private int total;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public InventoryItem[] getInventoryItems() {
        return inventoryItems;
    }

    public void setInventoryItems(InventoryItem[] inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "InventoryItems{" +
                "href='" + href + '\'' +
                ", inventoryItems=" + Arrays.toString(inventoryItems) +
                ", limit=" + limit +
                ", next='" + next + '\'' +
                ", prev='" + prev + '\'' +
                ", size=" + size +
                ", total=" + total +
                '}';
    }
}
