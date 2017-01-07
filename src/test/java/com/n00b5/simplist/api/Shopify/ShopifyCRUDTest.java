package com.n00b5.simplist.api.Shopify;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

/**
 * Created by Shehar on 1/6/2017.
 */
public class ShopifyCRUDTest {




    @Test
    public void createItem() throws Exception {
        String uri = "https://paperss.myshopify.com/admin/products.json?access_token="+ ShopifyAPI.getTokenKey();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        ShopifyItem unitTestItem = new ShopifyItem("Junit","Junit","Junit","Junit","Junit");
        HttpResponse response = null;
        try {
            HttpPost request = new HttpPost(uri);
            StringEntity params = new StringEntity(unitTestItem.getJSONItem().toString());
            request.addHeader("Content-Type", "application/json;; charset=UTF-8");
            request.addHeader("Accept", "application/json;; charset=UTF-8");
            request.setEntity(params);
            response = httpClient.execute(request);

        } catch (Exception ex) {
            // handle exception here
        } finally {
            System.out.println(response);
            httpClient.close();
        }
    }

    @Test
    public void updateItem() throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        ShopifyItem unitTestItem = new ShopifyItem("Junit","Junit","Junit","Junit","Junit");
        try {
            HttpPut putRequest = new HttpPut("https://paperss.myshopify.com/admin/products/"+ "9999" +".json?access_token="+ ShopifyAPI.getTokenKey());
            StringEntity params = new StringEntity(unitTestItem.getUpdateJSONItem().toString());
            putRequest.addHeader("Content-Type", "application/json;; charset=UTF-8");putRequest.addHeader("Accept", "application/json;; charset=UTF-8");
            putRequest.setEntity(params);
            HttpResponse response = httpClient.execute(putRequest);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deleteItem() throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpDelete deleteRequest = new HttpDelete("https://paperss.myshopify.com/admin/products/"+"99999"+".json?access_token=" + ShopifyAPI.getTokenKey());
            HttpResponse response = httpClient.execute(deleteRequest);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}