package com.n00b5.simplist.api.etsy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.n00b5.simplist.data.Facade;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Louis on 1/1/2017.
 * This uses a OAuth 1.0 library which extends OAuthService
 */

@Controller
@PropertySource("classpath:dev_etsy.properties")
public class EtsyController {

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

    private OAuth10aService service;
    private OAuth1RequestToken requestToken;
    OAuth1AccessToken accessToken;
    @Autowired
    private Facade facade;


    @RequestMapping(value="/etsy/oauth")
    public ModelAndView etsy() throws IOException {

            service  = new ServiceBuilder()
                .apiKey(keystring)
                .apiSecret(sharedsecret)
                .callback(redirect_uri_etsy)
                .build(EtsyApi.instance());

        //step 1 - Getting request token --> [request_token] [token_secret] [callback_confirmed]
        //{oauth_token=7a45b7fd57e7398e30958c7bf1fb13, oauth_token_secret=b634fb575e, oauth_callback_confirmed=true}
        requestToken = service.getRequestToken();

        //step 2 Getting the AUTHORIZATION url
        //http://www.etsy.com/oauth/signin?oauth_token=9d2f2f82a390e01ee886bd421cf290
        String authUrl = service.getAuthorizationUrl(requestToken);

        return new ModelAndView("redirect:"+authUrl);
    }


    @RequestMapping(value="/etsyToken")
    @ResponseBody
    public String auth(@RequestParam(value="oauth_token") String token,
                       @RequestParam(value="oauth_verifier")String verifier,
                        HttpServletResponse response) throws IOException {

        accessToken = service.getAccessToken(requestToken,verifier);
        OAuth1Converter converter = new OAuth1Converter(accessToken.getToken(),accessToken.getTokenSecret());
        String acToken = new ObjectMapper().writeValueAsString(converter);
        response.addCookie(new Cookie("etsyToken", acToken));
        System.out.println("IN authorize " + acToken);
        return acToken;
    }


    @RequestMapping(value="/etsy/add",method= RequestMethod.POST)
    public void addItem(@RequestBody String json) throws IOException, JSONException {

        /*STEPS
            1. Add to Etsy
            2. If successful add to DB
                else rollback
         */

        EtsyItem etsyItem = new ObjectMapper().readValue(json, EtsyItem.class);

        OAuthRequest request = new OAuthRequest(Verb.POST, "https://openapi.etsy.com/v2/listings?" + etsyItem.toURL(), service);
        service.signRequest(accessToken, request); // the access token from step 4
        Response response = request.send();
        System.out.println("STATUS CODE " +response.getCode());
        System.out.println("Added " + response.getBody());


        JSONObject obj = new JSONObject(response.getBody().substring(22, response.getBody().lastIndexOf("]")));
        etsyItem.setListing_id(obj.getString("listing_id"));

        facade.etsyAddItem(etsyItem);

    }

    @RequestMapping(value="/etsy/delete")
    public void deleteItem(@RequestParam("itemID")String id)throws IOException {

        OAuthRequest request = new OAuthRequest(Verb.DELETE, "https://openapi.etsy.com/v2/listings/"+id, service);
        service.signRequest(accessToken, request); // the access token from step 4
        Response response = request.send();
        System.out.println("STATUS CODE " +response.getCode());
        System.out.println("Deleted " +response.getBody());

        facade.etsyDeleteItem(id);

        System.out.println("ALL DONE");


    }

    @RequestMapping(value="/etsy/getById")
    public @ResponseBody
    String getById(@RequestParam("itemID")String id) throws IOException {

        // --> THIS IS MY ONLY ACTIVE LISTING .... itemID = 488901146
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://openapi.etsy.com/v2/listings/" + id + scope, service);
        service.signRequest(accessToken, request); // the access token from step 4
        Response response = request.send();
        System.out.println("STATUS CODE " + response.getCode());
        System.out.println("Item by ID " +response.getBody());

        EtsyItem etsyItem = facade.etsyGetById(id);
        System.out.println("RETURNING FROM DB - > " + etsyItem.toString());
        return response.getBody().toString();
    }

    @RequestMapping(value="/etsy/getAll")
    public @ResponseBody
    String getAllListings() throws IOException {

        //This gets my listings
        ///shops/100857342/listings/draft
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://openapi.etsy.com/v2/shops/"+shop_id+"/listings/draft", service);
        service.signRequest(accessToken, request);
        Response response = request.send();
        System.out.println("STATUS CODE" +response.getCode());
        System.out.println("All draft items " +response.getBody());

        facade.etsyGetAll();

        return response.getBody();

    }

    @RequestMapping(value="/etsy/update")
    public void update(@RequestBody String json, @RequestParam("itemID")String id) throws IOException {

        EtsyItem etsyItem = new ObjectMapper().readValue(json,EtsyItem.class);
        etsyItem.setListing_id(id);
        OAuthRequest request = new OAuthRequest(Verb.PUT, "https://openapi.etsy.com/v2/listings/" + id + "?" + etsyItem.toURL(), service);
        service.signRequest(accessToken, request); // the access token from step 4
        Response response = request.send();
        System.out.println("STATUS CODE " +response.getCode());
        System.out.println("Updated item " + response.getBody());

        facade.etsyUpdateItem(etsyItem, etsyItem.getListing_id());
        System.out.println("ALL DONE");
    }

    public void createShippingTemplate() throws IOException {

        String shippingTemplateTitle = "Standard_Shipping";
        int origin_country_id = 105;//105 = UK , 209 = US
        double primary_cost = 15;
        double secondary_cost = 5;

        String shippingItems =  "title="+shippingTemplateTitle+"&"+
                "origin_country_id="+origin_country_id+"&"+
                "primary_cost="+primary_cost+"&"+
                "secondary_cost="+secondary_cost;

        OAuthRequest request = new OAuthRequest(Verb.POST, "https://openapi.etsy.com/v2/shipping/templates?" + shippingItems, service);
        service.signRequest(accessToken, request); // the access token from step 4
        Response response = request.send();
        System.out.println("HTTP CODE " +response.getCode());
        System.out.println(response.getBody());

    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public OAuth1AccessToken getAccessToken() {
        return accessToken;
    }


    public void setFacade(Facade facade) {
        this.facade = facade;
    }

    public Facade getFacade() {
        return facade;
    }
}
