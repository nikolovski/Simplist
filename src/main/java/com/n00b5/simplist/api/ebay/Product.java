package com.n00b5.simplist.api.ebay;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Set;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/5/17
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    @JsonProperty
    private Map<String,String> aspects;
    @JsonProperty
    private String brand;
    @JsonProperty
    private String description;
    @JsonProperty
    private Set<String> ean;
    @JsonProperty
    private Set<String> imageUrls;
    @JsonProperty
    private Map<String,String> isbn;
    @JsonProperty
    private Map<String,String> upc;
    @JsonProperty
    private String mpn;
    @JsonProperty
    private String subtitle;
    @JsonProperty
    private String title;

    public Product() {
    }

    public Map<String,String> getAspects() {
        return aspects;
    }

    public void setAspects(Map<String,String> aspects) {
        this.aspects = aspects;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getEan() {
        return ean;
    }

    public void setEan(Set<String> ean) {
        this.ean = ean;
    }

    public Set<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(Set<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public Map<String,String> getIsbn() {
        return isbn;
    }

    public void setIsbn(Map<String,String> isbn) {
        this.isbn = isbn;
    }

    public Map<String,String> getUpc() {
        return upc;
    }

    public void setUpc(Map<String,String> upc) {
        this.upc = upc;
    }

    public String getMpn() {
        return mpn;
    }

    public void setMpn(String mpn) {
        this.mpn = mpn;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Product{" +
                "aspects=" + aspects +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", ean=" + ean +
                ", imageUrls=" + imageUrls +
                ", isbn=" + isbn +
                ", upc=" + upc +
                ", mpn='" + mpn + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
