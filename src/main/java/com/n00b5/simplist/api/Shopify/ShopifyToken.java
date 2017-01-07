package com.n00b5.simplist.api.Shopify;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Project: Simplist
 *
 * @author Martino Nikolovski
 * @date 1/6/17
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "SHOPIFY_TOKEN")
public class ShopifyToken {
    @Id
    @JsonProperty
    private String accessToken;
    @Column
    @JsonProperty
    private String scope;

    @Column
    @JsonProperty
    private long expiresIn;

    @Column
    @JsonProperty
    private String associatedUserScope;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JsonProperty
    private ShopifyUser shopifyUser;

    @Override
    public String toString() {
        return "ShopifyToken{" +
                "accessToken='" + accessToken + '\'' +
                ", scope='" + scope + '\'' +
                ", expiresIn=" + expiresIn +
                ", associatedUserScope='" + associatedUserScope + '\'' +
                ", shopifyUser=" + shopifyUser +
                '}';
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getAssociatedUserScope() {
        return associatedUserScope;
    }

    public void setAssociatedUserScope(String associatedUserScope) {
        this.associatedUserScope = associatedUserScope;
    }

    public ShopifyUser getShopifyUser() {
        return shopifyUser;
    }

    public void setShopifyUser(ShopifyUser shopifyUser) {
        this.shopifyUser = shopifyUser;
    }
}
