package com.n00b5.simplist.api.ebay.location;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Project: Simplist
 *
 * @author Martino Nikolovski
 * @date 1/7/17
 */
public class Location {
    @JsonProperty
    private String locationId;

    @JsonProperty
    private Address address;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Location{" +
                "address=" + address +
                '}';
    }
}
