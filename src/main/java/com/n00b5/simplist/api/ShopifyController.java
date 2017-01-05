package com.n00b5.simplist.api;

import com.n00b5.simplist.api.beans.ShopifyItem;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
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

/**#
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
    @Value("${tokenURL}")
    private String tokenURL;
    @Value("${accessToken}")
    private String accessTokenURL;

    private String tokenKey;


    @RequestMapping(value = "/shopify/oauth",method= RequestMethod.GET)
    public ModelAndView openAuth() throws IOException {
        System.out.println("in openAuth");
        String getCodeURL = shop+signInURL+
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
        return new ModelAndView("redirect:" + tokenURL);
    }

    @ResponseBody
    @RequestMapping(value="shopify/getAll", method=RequestMethod.GET)
    public ModelAndView getAll(){


        return new ModelAndView("redirect:https://paperss.myshopify.com/admin/products.json");
    }

    @RequestMapping(value="/shopify/token")
    @ResponseBody
    public String getToken() throws IOException { // BResult holds errors
        System.out.println("In token");
        String tokenURL = accessTokenURL;
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
        tokenKey = response.substring(17,response.indexOf(",")-1);
        System.out.println(tokenKey);
        System.out.println(response.toString());

        return response.toString();
    }


    @RequestMapping(value="/shopify/createItem",method=RequestMethod.POST)
    public @ResponseBody ShopifyItem createItem(@RequestBody ShopifyItem item) throws IOException, JSONException {
        String uri = "https://paperss.myshopify.com/admin/products.json?access_token="+tokenKey;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost(uri);
            StringEntity params = new StringEntity(item.getJSONItem().toString());
            request.addHeader("Content-Type", "application/json;; charset=UTF-8");request.addHeader("Accept", "application/json;; charset=UTF-8");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            System.out.println(response);
        } catch (Exception ex) {
            // handle exception here
        } finally {
            httpClient.close();
        }
        return new ShopifyItem();
    }

    @ResponseBody
    @RequestMapping(value = "shopify/update/", method = RequestMethod.POST)
    public void updateItem(@RequestBody ShopifyItem item){
        System.out.println("IN Update ITEM " + item.getId());
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpPut putRequest = new HttpPut("https://paperss.myshopify.com/admin/products/"+item.getId()+".json?access_token="+tokenKey);
            StringEntity params = new StringEntity(item.getUpdateJSONItem().toString());
            putRequest.addHeader("Content-Type", "application/json;; charset=UTF-8");putRequest.addHeader("Accept", "application/json;; charset=UTF-8");
            putRequest.setEntity(params);
            HttpResponse response = httpClient.execute(putRequest);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @ResponseBody
    @RequestMapping(value = "shopify/delete/", method = RequestMethod.POST)
    public void deleteItem(@RequestBody String id) throws JSONException {
        JSONObject deleteID = new JSONObject(id);
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpDelete deleteRequest = new HttpDelete("https://paperss.myshopify.com/admin/products/"+deleteID.getString("id")+".json?access_token="+tokenKey);
            HttpResponse response = httpClient.execute(deleteRequest);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}