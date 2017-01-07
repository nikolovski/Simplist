package com.n00b5.simplist.api.ebay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n00b5.simplist.api.ebay.inventory.InventoryItem;
import com.n00b5.simplist.api.ebay.inventory.InventoryItems;
import com.n00b5.simplist.api.ebay.location.InventoryLocation;
import com.n00b5.simplist.api.ebay.location.InventoryLocations;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URLEncoder;
import java.util.ResourceBundle;

/**
 * Project: Simplist
 *
 * @author Martino Nikolovski
 * @date 1/4/17
 */
public class eBayAPI {
    private HttpClient httpClient;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("dev_ebay");
    private final String inventoryUri = resourceBundle.getString("inventoryUri");
    private final String locationUri = resourceBundle.getString("locationUri");

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
                .readValue(postRequest(tokenUrl, "application/x-www-form-urlencoded",
                        parameters, "Basic " + base64String).getEntity().getContent(), EbayToken.class);
        return ebayToken.getAccessToken();
    }

    HttpResponse createOrReplaceInventoryItem(InventoryItem item, String token) throws IOException {
        String itemJSON = new ObjectMapper().writeValueAsString(item);
        return putRequest(inventoryUri + item.getSku(), itemJSON, "Bearer " + token);
    }

    InventoryItem getInventoryItem(String inventoryItemSKU, String token) throws IOException {
        HttpResponse response = getRequest(inventoryUri + inventoryItemSKU, "Bearer " + token);
        return new ObjectMapper().readValue(response.getEntity().getContent(), InventoryItem.class);
    }

    InventoryItems getAllInventoryItems(String token) throws IOException {
        HttpResponse response = getRequest(inventoryUri, "Bearer " + token);
        return new ObjectMapper().readValue(response.getEntity().getContent(), InventoryItems.class);
    }

    HttpResponse deleteInventoryItem(String inventoryItemSKU, String token) throws IOException {
        return deleteRequest(inventoryUri + inventoryItemSKU, "Bearer " + token);
    }


    HttpResponse createInventoryLocation(InventoryLocation location, String token) throws IOException {
        String itemJSON = new ObjectMapper().writeValueAsString(location);
        return postRequest(locationUri + location.getMerchantLocationKey(), "application/json", itemJSON, "Bearer " + token);
    }

    InventoryLocation getInventoryLocation(String merchantLocationKey, String token) throws IOException {
        HttpResponse response = getRequest(locationUri + merchantLocationKey, "Bearer " + token);
        return new ObjectMapper().readValue(response.getEntity().getContent(), InventoryLocation.class);
    }

    InventoryLocations getAllInventoryLocations(String token) throws IOException {
        HttpResponse response = getRequest(locationUri, "Bearer " + token);

        return new ObjectMapper().readValue(response.getEntity().getContent(), InventoryLocations.class);
    }

    HttpResponse deleteInventoryLocation(String merchantLocationKey, String token) throws IOException {
        return deleteRequest(locationUri + merchantLocationKey, "Bearer " + token);
    }

    HttpResponse updateInventoryLocation(String merchantLocationKey,
                                         String locationAdditionalInformation,
                                         String locationInstructions,
                                         String locationWebUrl,
                                         String name,
                                         String phone,
                                         String token) throws IOException, JSONException {
        JSONObject updatedLocation = new JSONObject();
        updatedLocation.put("locationAdditionalInformation", locationAdditionalInformation);
        updatedLocation.put("locationInstructions", locationInstructions);
        updatedLocation.put("locationWebUrl", locationWebUrl);
        updatedLocation.put("name", name);
        updatedLocation.put("phone", phone);
        HttpResponse response = postRequest(
                locationUri + merchantLocationKey + "/update_location_details",
                "application/json", updatedLocation.toString(), "Bearer " + token);
        return response;
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
