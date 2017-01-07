package com.n00b5.simplist.api.beans.Shopify;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**#
 * Created by Shehar on 1/3/2017.
 */
public class ShopifyItem {


    @JsonProperty
    private String id;
    @JsonProperty
    private String title;
    @JsonProperty
    private String body_html;
    @JsonProperty
    private String vendor;
    @JsonProperty
    private String product_type;
    @JsonProperty
    private String tags;
    @JsonProperty
    private Variants[] variants;

    public ShopifyItem(){

    }

    public ShopifyItem(String title, String body_html, String vendor, String product_type, String tags) {
        this.title = title;
        this.body_html = body_html;
        this.vendor = vendor;
        this.product_type = product_type;
        this.tags = tags;

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

    public Variants[] getVariants() {
        return variants;
    }

    public void setVariants(Variants[] variants) {
        this.variants = variants;
    }

    public JSONObject getJSONItem() throws JSONException {
        System.out.println(variants[0].getPrice());
        JSONObject product = new JSONObject();
        JSONObject productTags = new JSONObject();
        JSONArray productVariants = new JSONArray();
        productTags.put("title",this.getTitle());
        productTags.put("body_html",this.getBody_html());
        productTags.put("vendor",this.getVendor());
        productTags.put("product_type",this.getProduct_type());
        productTags.put("tags",this.getTags());
        productTags.put("variants",productVariants);
        productVariants.put(0,new JSONObject().put("price",variants[0].getPrice()));
        product.put("product",productTags);
        System.out.println(product.toString());
        return product;
    }




    public JSONObject getUpdateJSONItem() throws JSONException {
        System.out.println(variants[0].getPrice());
        JSONObject product = new JSONObject();
        JSONObject productTags = new JSONObject();
        JSONArray productVariants = new JSONArray();
        productTags.put("id",this.getId());
        productTags.put("title",this.getTitle());
        productTags.put("body_html",this.getBody_html());
        productTags.put("vendor",this.getVendor());
        productTags.put("product_type",this.getProduct_type());
        productTags.put("tags",this.getTags());
        productTags.put("variants",productVariants);
        productVariants.put(0,new JSONObject().put("price",variants[0].getPrice()));
        product.put("product",productTags);
        System.out.println(product.toString());

/*
        JSONObject updateItem = new JSONObject("{\n  \"product\": " +
                "{\n    \"id\": \""+this.getId()+"\"," +
                "\n    \"title\": \""+this.getTitle()+"\"," +
                "\n    \"body_html\": \"<strong>"+this.getBody_html()+"<\\/strong>\"," +
                "\n    \"vendor\": \""+this.getVendor()+"\"," +
                "\n    \"product_type\": \""+this.getProduct_type()+"\"," +
                "\n    \"tags\": \""+this.getTags()+"\"\n  }\n}");
                */
        return product;
    }
}
