package com.n00b5.simplist.api;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;

/**
 * Created by Louis on 1/1/2017.
 * This uses a OAuth 1.0 library which extends OAuthService
 */
@Controller
public class EtsyController {

    private String keystring = "hihs60bf4t5ugifau2mau8n5";
    private String sharedsecret = "klfzgx9oz9";

    OAuth10aService service;
    OAuth1RequestToken requestToken;

    @RequestMapping(value="/oAuth/Etsy")
    public ModelAndView etsy() throws IOException {

            service  = new ServiceBuilder()
                .apiKey(keystring)
                .apiSecret(sharedsecret)
                .callback("http://localhost:8080/authorize/Etsy")
                .build(EtsyApi.instance());

        //step 1 - Getting request token --> [request_token] [token_secret] [callback_confirmed]
        //{oauth_token=7a45b7fd57e7398e30958c7bf1fb13, oauth_token_secret=b634fb575e, oauth_callback_confirmed=true}
        requestToken = service.getRequestToken();
        System.out.println("REQUEST TOKEN " +requestToken);


        //step 2 Getting the AUTHORIZATION url
        //http://www.etsy.com/oauth/signin?oauth_token=9d2f2f82a390e01ee886bd421cf290
        String authUrl = service.getAuthorizationUrl(requestToken);
        System.out.println("AUTH URL " + authUrl);


        return new ModelAndView("redirect:"+authUrl);
    }

    @RequestMapping(value="/authorize/Etsy")
    public void auth(@RequestParam(value="oauth_token") String token, @RequestParam(value="oauth_verifier")String verifier ) throws IOException {

        System.out.println("In Auth");

       OAuth1AccessToken accessToken = service.getAccessToken(requestToken,verifier);
       System.out.println("ACCESS TOKEN IS " + accessToken);

        /* THIS DELETES AN ITEM

                                                                    //HARD-CODED FOR NOW . . .
        final OAuthRequest request = new OAuthRequest(Verb.DELETE, "https://openapi.etsy.com/v2/listings/488172910", service);
        service.signRequest(accessToken, request); // the access token from step 4
        final Response response = request.send();
        System.out.println(response.getBody());
        */

        /*

              // THIS ADDS AN ITEM
        int quantity = 5;
        String title = "Braclet";
        String description = "This a test  . . . . . :) :-)";
        float price = 0.20f;
        String enuma = "i_did"; // need to make enum
        boolean is_supply = true;
        String when_made = "2016"; // needs to be enum



                                                                    //HARD-CODED FOR NOW . . .
        final OAuthRequest request = new OAuthRequest(Verb.POST, "https://openapi.etsy.com/v2/listings", service);
        service.signRequest(accessToken, request); // the access token from step 4
        final Response response = request.send();
        System.out.println(response.getBody());

        */
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}