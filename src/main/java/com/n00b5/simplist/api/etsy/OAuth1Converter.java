package com.n00b5.simplist.api.etsy;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by louislopez on 1/5/17.
 */
public class OAuth1Converter {
    @JsonProperty(value = "oauth_token")
    private String oauthToken;
    @JsonProperty(value = "oauth_token_secret")
    private String oauthTokenSecret;

    public OAuth1Converter() {
    }

    public OAuth1Converter(String oauthToken, String oauthTokenSecret) {
        this.oauthToken = oauthToken;
        this.oauthTokenSecret = oauthTokenSecret;
    }

    public String getOauthToken() {
        return oauthToken;
    }

    public void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    public String getOauthTokenSecret() {
        return oauthTokenSecret;
    }

    public void setOauthTokenSecret(String oauthTokenSecret) {
        this.oauthTokenSecret = oauthTokenSecret;
    }
}
