package com.n00b5.simplist.api.Shopify;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;

/**#
 * Created by Shehar on 1/3/2017.
 */
@Entity
@Table(name="SHOPIFY_ITEM")
public class ShopifyItem {


    @Id
    @Column(name = "ITEM_ID")
    @JsonProperty(value = "id")
    private String shopifyId;

    @Column(name = "TITLE")
    @JsonProperty
    private String title;

    @Column(name = "DESCRIPTION")
    @JsonProperty
    private String body_html;

    @Column(name = "VENDOR")
    @JsonProperty
    private String vendor;

    @Column(name = "TYPE")
    @JsonProperty
    private String product_type;

    @Column(name = "TAGS")
    @JsonProperty
    private String tags;


    @Transient
    @JsonProperty
    private Variants[] variants;

    public ShopifyItem(){

    }

    public ShopifyItem(String shopifyId, String title, String body_html, String vendor, String product_type, String tags) {
        this.shopifyId = shopifyId;
        this.title = title;
        this.body_html = body_html;
        this.vendor = vendor;
        this.product_type = product_type;
        this.tags = tags;
    }

    public String getShopifyId() {
        return shopifyId;
    }

    public void setShopifyId(String shopifyId) {
        this.shopifyId = shopifyId;
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
        productTags.put("shopifyId",this.getShopifyId());
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
