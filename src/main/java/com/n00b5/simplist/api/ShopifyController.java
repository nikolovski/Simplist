package com.n00b5.simplist.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Shehar on 12/30/16.
 */
@Controller
@PropertySource("classpath:dev_shopify.properties")
public class ShopifyController{

    private String code;
    @Value("${secret}")
    private String secret;
    @Value("${shop}")
    private String shop;
    @Value("${api_key}")
    private String api_key;
    @Value("${scopes}")
    private String scopes;
    @Value("${redirect_uri}")
    private String redirect_uri;
    @Value("${nonce}")
    private String nonce;
    @Value("${option}")
    private String option;
    @Value("${signInURL}")
    private String signInURL;


    @RequestMapping(value = "/shopify/oauth",method= RequestMethod.GET)
    public ModelAndView openAuth() throws IOException {
        System.out.println("in openAuth");
        String getCodeURL = "http://"+shop+signInURL+
                "client_id="+api_key+
                "&scope="+scopes+
                "&redirect_uri="+redirect_uri+
                "&state="+nonce+
                "&grant_options[]="+option;
        return new ModelAndView("redirect:"+getCodeURL);
    }


    @ResponseBody
    @RequestMapping(value="/shopify/authorize",
            method=RequestMethod.GET
    )
    public ModelAndView authorize(@RequestParam(value="code") String code,@RequestParam(value="hmac") String hmac,@RequestParam(value="timestamp") String timestamp){
        System.out.println("in authorize");
        this.code = code;
        System.out.println("CODE" + this.code);
        return new ModelAndView("redirect:https://localhost:8443/shopify/token");
    }


    @ResponseBody
    @RequestMapping(value = "shopify/delete/{id}", method = RequestMethod.GET)
    public void delete(@RequestParam(value="id") String id){
        System.out.println("ItemDeleted:" +  id);
        // return new ModelAndView("redirect:https://"+shop+".myshopify.com/admin/products/"+itemId+".json");
    }

    @ResponseBody
    @RequestMapping(value="shopify/getAll", method=RequestMethod.GET)
    public ModelAndView getAll(){


        return new ModelAndView("redirect:https://"+shop+".myshopify.com/admin/products.json");
    }





    @RequestMapping(value="/shopify/token")
    public void getToken() throws IOException { // BResult holds errors
        System.out.println("In token");
        String tokenURL = "https://"+shop+".myshopify.com/admin/oauth/access_token";
        URL obj = new URL(tokenURL);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "client_id="+api_key+"&"+"client_secret="+secret+"&"+"code="+code;
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + tokenURL);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}