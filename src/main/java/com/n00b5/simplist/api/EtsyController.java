package com.n00b5.simplist.api;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;
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



    //etsy/oauth
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

       OAuth1AccessToken accessToken = service.getAccessToken(requestToken,verifier);
       //System.out.println("ACCESS TOKEN IS " + accessToken);


        // THIS ADDS AN ITEM
        int quantity = 1;
        String title = "Sponge_bob_blanket";
        String description = "Sponge_bob_blanket";
        double price = 0.20; // change to float
        String state = "draft";
        String who_made = "i_did"; // need to make enum
        boolean is_supply = true;
        String when_made = "made_to_order"; // needs to be enum
        String shipping_template_id = "35750756683";    //USER MUST CREATE AT LEAST ONE


        String addedItems =
                "quantity="+quantity+"&"+
                        "title="+title+"&"+
                        "description="+description+"&"+
                        "price="+price+"&"+
                        "state="+state+"&"+
                        "is_supply="+is_supply+"&"+
                        "who_made="+who_made+"&"+
                        "when_made="+when_made+"&"+
                        "shipping_template_id="+shipping_template_id;


        //HARD-CODED FOR NOW . . .
        final OAuthRequest request = new OAuthRequest(Verb.POST, "https://openapi.etsy.com/v2/listings?"+addedItems, service);
        service.signRequest(accessToken, request); // the access token from step 4
        final Response response = request.send();
        System.out.println(response.getBody());
        System.out.println("HTTP CODE " +response.getCode());
        System.out.println("HTTP MESSAGE " +response.getMessage());
        System.out.println("HTTP HEADER " +response.getHeaders());
        System.out.println(response.getStream());
        System.out.println(response.isSuccessful());


        return response.getBody().toString();

    }


    //Hard coded for the moment
    @RequestMapping(value="/add", method= RequestMethod.POST)
    public void addItem(OAuth1AccessToken accessToken) throws IOException {

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

        // THIS ADDS AN ITEM
        int quantity = 1;
        String title = "Sponge_bob_blanket";
        String description = "Sponge_bob_blanket";
        double price = 0.20; // change to float
        String state = "draft";
        String who_made = "i_did"; // need to make enum
        boolean is_supply = true;
        String when_made = "made_to_order"; // needs to be enum
        String shipping_template_id = "35750756683";    //USER MUST CREATE AT LEAST ONE


        String addedItems =
                "quantity="+quantity+"&"+
                        "title="+title+"&"+
                        "description="+description+"&"+
                        "price="+price+"&"+
                        "state="+state+"&"+
                        "is_supply="+is_supply+"&"+
                        "who_made="+who_made+"&"+
                        "when_made="+when_made+"&"+
                        "shipping_template_id="+shipping_template_id;


        //HARD-CODED FOR NOW . . .
        final OAuthRequest request = new OAuthRequest(Verb.POST, "https://openapi.etsy.com/v2/listings?"+addedItems, service);
        service.signRequest(accessToken, request); // the access token from step 4
        final Response response = request.send();
        System.out.println(response.getBody());
        System.out.println("HTTP CODE " +response.getCode());
        System.out.println("HTTP MESSAGE " +response.getMessage());
        System.out.println("HTTP HEADER " +response.getHeaders());
        System.out.println(response.getStream());
        System.out.println(response.isSuccessful());
    }

    @RequestMapping(value="/delete", method = RequestMethod.DELETE)
    public void deleteItem(OAuth1AccessToken accessToken,String itemNumber) throws IOException {
        //HARD-CODED FOR NOW . . .
        final OAuthRequest request = new OAuthRequest(Verb.DELETE, "https://openapi.etsy.com/v2/listings/"+itemNumber, service);
        service.signRequest(accessToken, request); // the access token from step 4
        final Response response = request.send();
        System.out.println(response.getBody());

    }


    @RequestMapping(value="/getAll", method=RequestMethod.GET)
    public void getAllListings(OAuth1AccessToken accessToken) throws IOException {

        //This gets my listings
        ///shops/100857342/listings/draft
        final OAuthRequest request = new OAuthRequest(Verb.GET, "https://openapi.etsy.com/v2/shops/"+shop_id+"/listings/draft", service);
        System.out.println("PRINTING OUt REQUEST "+ request);
        System.out.println("PRINTING OUT SERVICE "+service);
        service.signRequest(accessToken, request); // the access token from step 4
        final Response response = request.send();
        System.out.println("DRAFTS " +response.getBody());
    }

    @RequestMapping(value="update", method=RequestMethod.PUT)
    public void update(OAuth1AccessToken accessToken) throws IOException {
        //This updates an item, fields can be added
        //488901146 <--- draft listing id
        //HARD-CODED FOR NOW . . .
        final OAuthRequest request = new OAuthRequest(Verb.PUT, "https://openapi.etsy.com/v2/listings/488901146?quantity=4", service);
        service.signRequest(accessToken, request); // the access token from step 4
        final Response response = request.send();
        System.out.println(response.getBody());
        System.out.println("Code " +response.getCode());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    public enum who_made {
        i_did, collective, someone_else;
    }

    public enum when_made {
       // made_to_order, 2010_2017, 2000_2009, 1998_1999, before_1998, 1990_1997, 1980s, 1970s, 1960s, 1950s, 1940s, 1930s, 1920s, 1910s, 1900s, 1800s, 1700s, before_1700;
    }

}
