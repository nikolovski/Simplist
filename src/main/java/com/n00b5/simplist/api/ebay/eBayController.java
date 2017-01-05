package com.n00b5.simplist.api.ebay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;

/**
 * Created by d4k1d23 on 12/30/16.
 */
@Controller
@RequestMapping("/ebay")
@PropertySource("classpath:dev_ebay.properties")
public class eBayController {
    @Value("${appId}")
    private String clientID;
    @Value("${certId}")
    private String clientSecret;
    @Value("${RuName}")
    private String redirectURI;
    @Value("${signInUrl}")
    private String signInURL;
    @Value("${tokenUrl}")
    private String tokenUrl;
    @Value("${Base64String}")
    private String base64String;
    @Value("${scope}")
    private String scope;
    private String responseType = "code";

    @RequestMapping(value = "/oauth")
    public ModelAndView getCode() throws IOException {
        String url = eBayAPI.generateURL(clientID,redirectURI,signInURL,scope,responseType);
        return new ModelAndView("redirect:" + url);
    }

    @RequestMapping(value = "/authorize", params = {"state", "code"})
    @ResponseBody
    public String getToken(String state, String code) throws IOException {
        return eBayAPI.getToken(tokenUrl,redirectURI,base64String,code);
    }
}
