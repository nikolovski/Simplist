package com.n00b5.simplist.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.n00b5.simplist.api.beans.EtsyItem;
import com.n00b5.simplist.api.etsy.OAuth1Converter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


/**
 * Created by Louis on 1/1/2017.
 * This uses a OAuth 1.0 library which extends OAuthService
 */

@Controller
@RequestMapping(value ="/etsy")
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

    private OAuth10aService service;
    private OAuth1RequestToken requestToken;
    OAuth1AccessToken accessToken;


    @RequestMapping(value="/oauth")
    public ModelAndView etsy() throws IOException {

            service  = new ServiceBuilder()
                .apiKey(keystring)
                .apiSecret(sharedsecret)
                .callback(redirect_uri_etsy)
                .build(EtsyApi.instance());

        //step 1 - Getting request token --> [request_token] [token_secret] [callback_confirmed]
        //{oauth_token=7a45b7fd57e7398e30958c7bf1fb13, oauth_token_secret=b634fb575e, oauth_callback_confirmed=true}
        requestToken = service.getRequestToken();
        //System.out.println("REQUEST TOKEN " +requestToken);


        //step 2 Getting the AUTHORIZATION url
        //http://www.etsy.com/oauth/signin?oauth_token=9d2f2f82a390e01ee886bd421cf290
        String authUrl = service.getAuthorizationUrl(requestToken);
       // System.out.println("AUTH URL " + authUrl);


        return new ModelAndView("redirect:"+authUrl);
    }

    //etsy/authorize
    @RequestMapping(value="/authorize")
    @ResponseBody
    public String auth(@RequestParam(value="oauth_token") String token, @RequestParam(value="oauth_verifier")String verifier ) throws IOException {

       // System.out.println("In Auth");

        accessToken = service.getAccessToken(requestToken,verifier);


        //System.out.println("THIS IS MY ACCESS TOKEN "+ accessToken);
        OAuth1Converter converter = new OAuth1Converter(accessToken.getToken(),accessToken.getTokenSecret());
        String acToken = new ObjectMapper().writeValueAsString(converter);
        System.out.println("IN authorize " + acToken);
        return acToken;


       //return addItem(accessToken); //Create an item

        //return getById(accessToken,488901146); //Return ONE item

        //getAllListings(); //Returns ALL items

        //return update(accessToken); //Update an item

        //return delete(accessToken,idNumber); //Deletes item


    }


    @RequestMapping(value="/add",method= RequestMethod.POST)
    public void addItem(@RequestBody String json) throws IOException {

        EtsyItem etsyItem = new ObjectMapper().readValue(json, EtsyItem.class);
        OAuthRequest request = new OAuthRequest(Verb.POST, "https://openapi.etsy.com/v2/listings?"+etsyItem.toString(), service);
        service.signRequest(accessToken, request); // the access token from step 4
        Response response = request.send();
        System.out.println(response.getBody());
        System.out.println("STATUS CODE " +response.getCode());

    }

    @RequestMapping(value="/delete")
    public void deleteItem(@RequestParam("itemID")String id)throws IOException {

        OAuthRequest request = new OAuthRequest(Verb.DELETE, "https://openapi.etsy.com/v2/listings/"+id, service);
        service.signRequest(accessToken, request); // the access token from step 4
        Response response = request.send();
        System.out.println("STATUS CODE " +response.getCode());
        System.out.println(response.getBody());

    }

    @RequestMapping(value="/getById")
    public String getById(@RequestParam(value="item_id") int itemId, @RequestParam(value="oauth_token") String oauth_token, @RequestParam(value="oauth_token_secret")String oauth_token_secret) throws IOException {

        //This gets my listings
        // --> THIS IS MY ONLY ACTIVE LISTING .... itemID = 488901146

        OAuth1AccessToken accessToken = new OAuth1AccessToken(oauth_token,oauth_token_secret);
        final OAuthRequest request = new OAuthRequest(Verb.GET, "https://openapi.etsy.com/v2/listings/"+itemId, service);
        System.out.println("PRINTING OUT REQUEST "+ request);
        service.signRequest(accessToken, request); // the access token from step 4
        final Response response = request.send();
        System.out.println("STATUS CODE" +response.getCode());
        System.out.println("Active listing -->" +response.getBody());

        return response.getBody().toString();
    }

    @RequestMapping(value="/getAll")
    public @ResponseBody
    String getAllListings() throws IOException {

        //This gets my listings
        ///shops/100857342/listings/draft
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://openapi.etsy.com/v2/shops/"+shop_id+"/listings/draft", service);
        service.signRequest(accessToken, request);
        Response response = request.send();
        System.out.println("All draft items " +response.getBody());
        System.out.println("STATUS CODE" +response.getCode());


        return response.getBody();

    }

    @RequestMapping(value="/update")
    public void update(@RequestBody String json, @RequestParam("itemID")String id) throws IOException {

        EtsyItem etsyItem = new ObjectMapper().readValue(json,EtsyItem.class);
        OAuthRequest request = new OAuthRequest(Verb.PUT, "https://openapi.etsy.com/v2/listings/"+id+"?"+etsyItem.toString(), service);
        service.signRequest(accessToken, request); // the access token from step 4
        Response response = request.send();
        System.out.println("STATUS CODE " +response.getCode());
        System.out.println(response.getBody());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public OAuth1AccessToken getAccessToken() {
        return accessToken;
    }


    public void createShippingTemplate(){
         /*
    //FIRST I MUST CREATE A SHIPPING TEMPLATE
        //THEN I CAN RETRIEVE THE ID AND STORE IT IN SHIPPING_TEMPLATE_ID THEN I CAN ADD A NEW ITEM
       // - > POST  /shipping/templates
        String shippingTemplateTitle = "For_Pillows";
        int origin_country_id = 105;//105 = UK , 209 = US
        double primary_cost = 20;
        double secondary_cost = 40;

        String shippingItems =  "title="+shippingTemplateTitle+"&"+
                "origin_country_id="+origin_country_id+"&"+
                "primary_cost="+primary_cost+"&"+
                "secondary_cost="+secondary_cost;


        final OAuthRequest request = new OAuthRequest(Verb.POST, "https://openapi.etsy.com/v2/shipping/templates?"+shippingItems , service);
        service.signRequest(accessToken, request); // the access token from step 4
        final Response response = request.send();
        System.out.println(response.getBody());
        System.out.println("HTTP CODE " +response.getCode());
        System.out.println("HTTP MESSAGE " +response.getMessage());
        System.out.println("HTTP HEADER " +response.getHeaders());
        System.out.println(response.getStream());
        System.out.println(response.isSuccessful());


        SHIPPING TEMPLATE WHICH WAS CREATED

{
   "count": 1,
   "results": [
      {
         "shipping_template_id": 35750756683,
         "title": "For_Pillows",
         "user_id": 100857342,
         "min_processing_days": null,
         "max_processing_days": null,
         "processing_days_display_label": "0 weeks",
         "origin_country_id": 105,
         "Entries": [
            {
               "shipping_template_entry_id": 13663172772,
               "shipping_template_id": 35750756683,
               "currency_code": "USD",
               "origin_country_id": 105,
               "destination_country_id": null,
               "destination_region_id": null,
               "primary_cost": "20.00",
               "secondary_cost": "40.00"
            }
         ]
      }
   ],
   "params": {
      "title": "For_Pillows",
      "origin_country_id": "105",
      "destination_country_id": null,
      "primary_cost": "20.0",
      "secondary_cost": "40.0",
      "destination_region_id": null,
      "min_processing_days": null,
      "max_processing_days": null
   },
   "type": "ShippingTemplate",
   "pagination": {}
}

     */
    }

}
