package com.n00b5.simplist.api.etsy;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by louislopez on 1/13/17.
 */
@PropertySource("classpath:dev_etsy.properties")
public class EtsyService {

    @Value("${keystring}")
    private String keystring;
    @Value("${sharedsecret}")
    private String sharedsecret;
    @Value("${redirect_uri_etsy}")
    private String redirect_uri_etsy;
    @Value("${shop_id}")
    private String shop_id;
    @Value("${scope}")
    private String scope;

    public OAuth10aService service;

    public OAuth10aService getService() {
        System.out.println(keystring);
        System.out.println(sharedsecret);
        System.out.println(redirect_uri_etsy);


       return new ServiceBuilder()
                .apiKey("hihs60bf4t5ugifau2mau8n5")
                .apiSecret("klfzgx9oz9")
                .callback("http://localhost:8080/etsyToken")
                .build(EtsyApi.instance());
    }
}
