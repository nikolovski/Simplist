package com.n00b5.simplist.api.ebay;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

/**
 * Project: Simplist
 *
 * @author Martino Nikolovski
 * @date 1/5/17
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Availability {
    @JsonProperty
    private PickupAtLocationAvailability [] pickupAtLocationAvailability;
    @JsonProperty
    private ShipToLocationAvailability shipToLocationAvailability;

    public PickupAtLocationAvailability[] getPickupAtLocationAvailability() {
        return pickupAtLocationAvailability;
    }

    public void setPickupAtLocationAvailability(PickupAtLocationAvailability[] pickupAtLocationAvailability) {
        this.pickupAtLocationAvailability = pickupAtLocationAvailability;
    }

    public ShipToLocationAvailability getShipToLocationAvailability() {
        return shipToLocationAvailability;
    }

    public void setShipToLocationAvailability(ShipToLocationAvailability shipToLocationAvailability) {
        this.shipToLocationAvailability = shipToLocationAvailability;
    }
    @Override
    public String toString() {
        return "Availability{" +
                "pickupAtLocationAvailability=" + pickupAtLocationAvailability +
                ", shipToLocationAvailability=" + shipToLocationAvailability +
                '}';
    }

}
