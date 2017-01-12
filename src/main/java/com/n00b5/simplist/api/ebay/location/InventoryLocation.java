package com.n00b5.simplist.api.ebay.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Project: Simplist
 *
 * @author Martino Nikolovski
 * @date 1/7/17
 */
public class InventoryLocation {

    @JsonProperty
    private String merchantLocationKey;

    @JsonProperty
    private Location location;

    @JsonProperty
    private String locationInstructions;

    @JsonProperty
    private String locationAdditionalInformation;

    @JsonProperty
    private String[] locationTypes;

    @JsonProperty
    private String locationWebUrl;

    @JsonProperty
    private String merchantLocationStatus;

    @JsonProperty
    private String name;

    @JsonProperty
    private String phone;

    public String getLocationAdditionalInformation() {
        return locationAdditionalInformation;
    }

    public void setLocationAdditionalInformation(String locationAdditionalInformation) {
        this.locationAdditionalInformation = locationAdditionalInformation;
    }

    public String getMerchantLocationKey() {
        return merchantLocationKey;
    }

    public void setMerchantLocationKey(String merchantLocationKey) {
        this.merchantLocationKey = merchantLocationKey;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLocationInstructions() {
        return locationInstructions;
    }

    public void setLocationInstructions(String locationInstructions) {
        this.locationInstructions = locationInstructions;
    }

    public String[] getLocationTypes() {
        return locationTypes;
    }

    /**
     * @param locationTypes
     * @see com.n00b5.simplist.api.ebay.enums.StoreTypeEnum
     */
    public void setLocationTypes(String[] locationTypes) {
        this.locationTypes = locationTypes;
    }

    public String getLocationWebUrl() {
        return locationWebUrl;
    }

    public void setLocationWebUrl(String locationWebUrl) {
        this.locationWebUrl = locationWebUrl;
    }

    public String getMerchantLocationStatus() {
        return merchantLocationStatus;
    }

    /**
     * @param merchantLocationStatus
     * @see com.n00b5.simplist.api.ebay.enums.StatusEnum
     */
    public void setMerchantLocationStatus(String merchantLocationStatus) {
        this.merchantLocationStatus = merchantLocationStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "InventoryLocation{" +
                "merchantLocationKey='" + merchantLocationKey + '\'' +
                ", location=" + location +
                ", locationInstructions='" + locationInstructions + '\'' +
                ", locationTypes=" + Arrays.toString(locationTypes) +
                ", locationWebUrl='" + locationWebUrl + '\'' +
                ", merchantLocationStatus='" + merchantLocationStatus + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
