package com.n00b5.simplist.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by d4k1d23 on 12/30/16.
 */
@Controller
@PropertySource("classpath:dev_ebay.properties")
public class eBayController{
    @Value("${appID}")
    private String clientID;
    @Value("${redirectURL}")
    private String redirectURL;
    @Value("${signInURL}")
    private String signInURL;
    @Value("${scope}")
    private String scope;
    private String responseType = "code";

    @RequestMapping(value = "/oAuth/eBay", method = RequestMethod.GET)
    @ResponseBody
    public void getToken() throws IOException {
        String code = null;
        String getCodeURL = signInURL+"client_id="+
                            clientID+"&response_type="+
                            responseType+"&redirect_url="+
                            redirectURL+"&scope=";
        scope = URLEncoder.encode(scope,"UTF-8");
        URL obj = new URL(getCodeURL+scope);
        HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.flush();
        wr.close();
    }
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
