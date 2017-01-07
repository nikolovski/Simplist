package com.n00b5.simplist.api.ebay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n00b5.simplist.api.ebay.inventory.InventoryItem;
import com.n00b5.simplist.api.ebay.inventory.InventoryItems;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.net.URLEncoder;

/**
 * Project: Simplist
 *
 * @author Martino Nikolovski
 * @date 1/4/17
 */
public class eBayAPI {
    private HttpClient httpClient;

    public eBayAPI() {
        httpClient = HttpClientBuilder.create().build();
    }

    String generateURL(String clientID,
                       String redirectURI,
                       String signInURL,
                       String scope,
                       String responseType) throws UnsupportedEncodingException {
        return signInURL + "?client_id=" +
                clientID + "&response_type=" +
                responseType + "&redirect_uri=" +
                redirectURI + "&scope=" +
                URLEncoder.encode(scope, "UTF-8");
    }

    String getToken(String tokenUrl, String redirectURI, String base64String, String code) throws IOException {
        String parameters = "grant_type=authorization_code&" +
                "&code=" + URLEncoder.encode(code, "UTF-8") +
                "&redirect_uri=" + redirectURI;
        EbayToken ebayToken = new ObjectMapper()
                .readValue(postRequest(tokenUrl,"application/x-www-form-urlencoded",
                        parameters, "Basic " + base64String).getEntity().getContent(), EbayToken.class);
        return ebayToken.getAccessToken();
    }

    HttpResponse createOrReplaceInventoryItem(InventoryItem item, String token) throws IOException {
        String url = "https://api.sandbox.ebay.com/sell/inventory/v1/inventory_item/" + item.getSku();
        String itemJSON = new ObjectMapper().writeValueAsString(item);
        return putRequest(url, itemJSON, "Bearer " + token);
    }

    InventoryItem getInventoryItem(String inventoryItemSKU, String token) throws IOException {
        String uri = "https://api.sandbox.ebay.com/sell/inventory/v1/inventory_item/" + inventoryItemSKU;
        HttpResponse response = getRequest(uri,"Bearer " + token);
        return new ObjectMapper().readValue(response.getEntity().getContent(),InventoryItem.class);
    }

    InventoryItems getAllInventoryItems(String token) throws IOException {
        String uri = "https://api.sandbox.ebay.com/sell/inventory/v1/inventory_item/";
        HttpResponse response = getRequest(uri,"Bearer " + token);
        return new ObjectMapper().readValue(response.getEntity().getContent(),InventoryItems.class);
    }

    HttpResponse deleteInventoryItem(String inventoryItemSKU, String token) throws IOException {
        String uri = "https://api.sandbox.ebay.com/sell/inventory/v1/inventory_item/" + inventoryItemSKU;
        return deleteRequest(uri,"Bearer " + token);
    }


    HttpResponse putRequest(String uri, String parameters, String authorization)
            throws IOException {
        HttpPut request = new HttpPut(uri);
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Content-Language", "en-US");
        request.addHeader("Accept", "application/json");
        request.addHeader("Authorization", authorization);
        request.setEntity(new StringEntity(parameters));
        return httpClient.execute(request);
    }

    HttpResponse postRequest(String uri, String contentType, String parameters, String authorization)
            throws IOException {
        HttpPost request = new HttpPost(uri);
        request.addHeader("Content-Type", contentType);
        request.addHeader("Content-Language", "en-US");
        request.addHeader("Accept", "application/json");
        request.addHeader("Authorization", authorization);
        request.setEntity(new StringEntity(parameters));
        return httpClient.execute(request);
    }
    HttpResponse getRequest(String uri, String authorization)
            throws IOException {
        HttpGet request = new HttpGet(uri);
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Content-Language", "en-US");
        request.addHeader("Accept", "application/json");
        request.addHeader("Authorization", authorization);
        return httpClient.execute(request);
    }

    HttpResponse deleteRequest(String uri, String authorization)
            throws IOException {
        HttpDelete request = new HttpDelete(uri);
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Content-Language", "en-US");
        request.addHeader("Accept", "application/json");
        request.addHeader("Authorization", authorization);
        return httpClient.execute(request);
    }

}
