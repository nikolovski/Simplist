package com.n00b5.simplist.api.etsy;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.OAuth1RequestToken;


/**
 * Created by Louis on 12/31/2016.
 * This is a custom Etsy API class
 */

public class EtsyApi extends DefaultApi10a {

    protected EtsyApi() {
    }

    //Singleton
    private static class InstanceHolder {
        private static final EtsyApi INSTANCE = new EtsyApi();
    }

    public static EtsyApi instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getRequestTokenEndpoint() {
        return "https://openapi.etsy.com/v2/oauth/request_token";
    }

    @Override
    public String getAuthorizationUrl(OAuth1RequestToken requestToken) {
        return "http://www.etsy.com/oauth/signin?oauth_token=" + requestToken.getToken();
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://openapi.etsy.com/v2/oauth/access_token";
    }


}