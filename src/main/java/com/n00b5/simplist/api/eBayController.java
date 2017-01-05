package com.n00b5.simplist.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

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
        String getCodeURL = signInURL + "?client_id=" +
                clientID + "&response_type=" +
                responseType + "&redirect_uri=" +
                redirectURI + "&scope=" +
                URLEncoder.encode(scope, "UTF-8");
        return new ModelAndView("redirect:" + getCodeURL);
    }

    @RequestMapping(value = "/authorize", params = {"state", "code"})
    @ResponseBody
    public String getToken(String state, String code) throws IOException {
        URL obj = new URL(tokenUrl);
        String parameters = "grant_type=authorization_code&" +
                "&code=" + URLEncoder.encode(code, "UTF-8") +
                "&redirect_uri=" + redirectURI;
        HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Authorization", "Basic " + base64String);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(parameters);
        wr.flush();
        wr.close();
        BufferedReader in =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
