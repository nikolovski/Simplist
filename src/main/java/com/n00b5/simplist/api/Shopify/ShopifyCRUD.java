package com.n00b5.simplist.api.Shopify;

import com.n00b5.simplist.data.Facade;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**#
 * Created by Shehar on 12/30/16.
 */
@Controller
@PropertySource("classpath:dev_shopify.properties")
public class ShopifyCRUD{

    @Autowired
    Facade facade;


    public String productID(String response){
        String id = response;
        String[] output = id.split("admin/products/");
        int comma = output[1].indexOf(',');
        id = output[1].substring(0,comma);
        return id;
    }

    @RequestMapping(value="/shopify/createItem",method=RequestMethod.POST)
    public @ResponseBody ShopifyItem createItem(@RequestBody ShopifyItem item) throws IOException, JSONException {
        String uri = "https://paperss.myshopify.com/admin/products.json?access_token="+ ShopifyAPI.getTokenKey();
        //CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpClient httpClient = new DefaultHttpClient();
        try {
            System.out.println("IN SHOP FINALLy");
            HttpPost request = new HttpPost(uri);
            StringEntity params = new StringEntity(item.getJSONItem().toString());
            request.addHeader("Content-Type", "application/json;; charset=UTF-8");request.addHeader("Accept", "application/json;; charset=UTF-8");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            item.setShopifyId(productID(response.toString()));

            System.out.println("IN SHOP NEW SHOP " + item);
           // facade.addShopifyItem(item);
            System.out.println("added.....");
            return item;
        } catch (Exception ex) {
            // handle exception here
        }
// finally {
//
//
//            httpClient.close();
//        }
        return null;
    }

    public void asda(){


    }


    @ResponseBody
    @RequestMapping(value = "shopify/update/", method = RequestMethod.POST)
    public void updateItem(@RequestBody ShopifyItem item){
        System.out.println("IN Update ITEM " + item.getShopifyId());
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpPut putRequest = new HttpPut("https://paperss.myshopify.com/admin/products/"+item.getShopifyId()+".json?access_token="+ ShopifyAPI.getTokenKey());
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
            HttpDelete deleteRequest = new HttpDelete("https://paperss.myshopify.com/admin/products/"+deleteID.getString("id")+".json?access_token=" + ShopifyAPI.getTokenKey());
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