package com.n00b5.simplist.api.etsy;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by Louis on 1/4/2017.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name="ETSY_ITEM")
public class EtsyItem {


    @Id
    @Column(name="LISTING_ID")
    private String listing_id;
    @Column(name="QUANTITY")
    @JsonProperty(value = "quantity")
    private int quantity;
    @Column(name="TITLE")
    @JsonProperty(value = "title")
    private String title;
    @Column(name="DESCRPTIONS")
    @JsonProperty(value = "description")
    private String description;
    @Column(name="PRICE")
    @JsonProperty(value = "price")
    private double price;
    @Column(name="STATE")
    @JsonProperty(value = "state")
    private String state; // needs to be enum

    @Column(name="IS_SUPPLY")
    private boolean is_supply = true;
    @Column(name="WHO_MADE")
    private String who_made = "i_did"; // needs to be enum
    @Column(name="WHEN_MADE")
    private String when_made = "made_to_order"; // needs to be enum

    @Column(name="SHIPPING_TEMPLATE_ID")
    @JsonProperty(value = "shipping_template_id")
    private String shippingTemplate; // needs to be shippingTemplate obj


    public EtsyItem() {
    }

    public EtsyItem(int quantity, String title, String description, double price, String state, String shippingTemplate) {
        this.quantity = quantity;
        this.title = title;
        this.description = description;
        this.price = price;
        this.state = state;
        this.shippingTemplate = shippingTemplate;
    }

    public String getListing_id() {
        return listing_id;
    }

    public void setListing_id(String listing_id) {
        this.listing_id = listing_id;
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



    public String toURL(){
        return
                "quantity="+this.quantity+"&"+
                        "title="+ this.title.replace(" ","%20")+"&"+
                        "description="+ this.description.replace(" ","%20")+"&"+
                        "price="+this.price+"&"+
                        "state="+this.state+"&"+
                        "is_supply="+this.is_supply+"&"+
                        "who_made="+this.who_made+"&"+
                        "when_made="+this.when_made+"&"+
                        "shipping_template_id="+this.shippingTemplate;
    }

    @Override
    public String toString() {
        return "EtsyItem{" +
                "listing_id='" + listing_id + '\'' +
                ", quantity=" + quantity +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", state='" + state + '\'' +
                ", is_supply=" + is_supply +
                ", who_made='" + who_made + '\'' +
                ", when_made='" + when_made + '\'' +
                ", shippingTemplate='" + shippingTemplate + '\'' +
                '}';
    }
}
