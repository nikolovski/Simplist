package com.n00b5.simplist.api.etsy;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Project: Simplist
 *
 * @author Martino Nikolovski
 * @date 1/7/17
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "ETSY_TOKEN")
public class EtsyToken {

    @Id
    @JsonProperty(value = "oauth_token")
    private String accessToken;

    @Column
    @JsonProperty(value= "oauth_token_secret")
    private String clientSecret;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public String toString() {
        return "EtsyToken{" +
                "accessToken='" + accessToken + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                '}';
    }
}
