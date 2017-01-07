package com.n00b5.simplist.api.etsy;

/**
 * Created by louislopez on 1/6/17.
 */
public class ShippingTemplate {


    private String shippingTemplateTitle;
    private int getOrigin_country_id;
    private double primary_cost;

    public ShippingTemplate() {
    }

    public ShippingTemplate(String shippingTemplateTitle, int getOrigin_country_id, double primary_cost) {
        this.shippingTemplateTitle = shippingTemplateTitle;
        this.getOrigin_country_id = getOrigin_country_id;
        this.primary_cost = primary_cost;
    }

    public String getShippingTemplateTitle() {
        return shippingTemplateTitle;
    }

    public void setShippingTemplateTitle(String shippingTemplateTitle) {
        this.shippingTemplateTitle = shippingTemplateTitle;
    }

    public int getGetOrigin_country_id() {
        return getOrigin_country_id;
    }

    public void setGetOrigin_country_id(int getOrigin_country_id) {
        this.getOrigin_country_id = getOrigin_country_id;
    }

    public double getPrimary_cost() {
        return primary_cost;
    }

    public void setPrimary_cost(double primary_cost) {
        this.primary_cost = primary_cost;
    }


}
