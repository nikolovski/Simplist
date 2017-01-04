package com.n00b5.simplist.api.beans;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Shehar on 1/3/2017.
 */
public class ShopifyItem {



    private String id;
    private String title;
    private String body_html;
    private String vendor;
    private String product_type;
    private String tags;

    public ShopifyItem(){

    }

    public ShopifyItem(String title, String body_html, String vendor, String product_type, String tags) {
        this.title = title;
        this.body_html = body_html;
        this.vendor = vendor;
        this.product_type = product_type;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "ShopifyItem{" +
                "title='" + title + '\'' +
                ", body_html='" + body_html + '\'' +
                ", vendor='" + vendor + '\'' +
                ", product_type='" + product_type + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody_html() {
        return body_html;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public JSONObject getJSONItem() throws JSONException {
        JSONObject test = new JSONObject("{\n  \"product\": {\n    \"title\": \""+this.getTitle()+"\",\n    \"body_html\": \"<strong>"+this.getBody_html()+"<\\/strong>\",\n    \"vendor\": \""+this.getVendor()+"\",\n    \"product_type\": \""+this.getProduct_type()+"\",\n    \"tags\": \""+this.getTags()+"\"\n  }\n}");
        return test;
    }

}
