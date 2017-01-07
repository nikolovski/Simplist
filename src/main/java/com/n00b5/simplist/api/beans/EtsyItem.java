package com.n00b5.simplist.api.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Louis on 1/4/2017.
 */
public class EtsyItem {

    @JsonProperty(value = "quantity")
    private int quantity;
    @JsonProperty(value = "title")
    private String title;
    @JsonProperty(value = "description")
    private String description;
    @JsonProperty(value = "price")
    private double price;
    @JsonProperty(value = "state")
    private String state; // needs to be enum
    @JsonProperty(value = "is_supply")
    private boolean is_supply;
    @JsonProperty(value = "who_made")
    private String who_made; // needs to be enum
    @JsonProperty(value = "when_made")
    private String when_made; // needs to be enum
    @JsonProperty(value = "shippingTemplate")
    private String shippingTemplate = "35750756683"; // needs to be shippingTemplate obj

    public EtsyItem() {
    }

    public EtsyItem(int quantity, String title, String description, double price, String state, boolean is_supply, String who_made, String when_made, String shippingTemplate) {
        this.quantity = quantity;
        this.title = title;
        this.description = description;
        this.price = price;
        this.state = state;
        this.is_supply = is_supply;
        this.who_made = who_made;
        this.when_made = when_made;
        this.shippingTemplate = shippingTemplate;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isIs_supply() {
        return is_supply;
    }

    public void setIs_supply(boolean is_supply) {
        this.is_supply = is_supply;
    }

    public String getWho_made() {
        return who_made;
    }

    public void setWho_made(String who_made) {
        this.who_made = who_made;
    }

    public String getWhen_made() {
        return when_made;
    }

    public void setWhen_made(String when_made) {
        this.when_made = when_made;
    }

    public String getShippingTemplate() {
        return shippingTemplate;
    }

    public void setShippingTemplate(String shippingTemplate) {
        this.shippingTemplate = shippingTemplate;
    }

    public String toString() {
        return
                "quantity="+this.quantity+"&"+
                "title="+this.title+"&"+
                "description="+this.description+"&"+
                "price="+this.price+"&"+
                "state="+this.state+"&"+
                "is_supply="+this.is_supply+"&"+
                "who_made="+this.who_made+"&"+
                "when_made="+this.when_made+"&"+
                "shipping_template_id="+this.shippingTemplate;
    }


}
