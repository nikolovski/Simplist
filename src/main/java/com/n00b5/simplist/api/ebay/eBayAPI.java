package com.n00b5.simplist.api.ebay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n00b5.simplist.api.ebay.inventory.InventoryItem;
import com.n00b5.simplist.api.ebay.inventory.InventoryItems;
import com.n00b5.simplist.api.ebay.location.InventoryLocation;
import com.n00b5.simplist.api.ebay.location.InventoryLocations;
import com.n00b5.simplist.api.ebay.offer.Offer;
import com.n00b5.simplist.api.ebay.offer.Offers;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
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
    private final String offerUri = resourceBundle.getString("offerUri");
    private final String tokenUrl = resourceBundle.getString("tokenUrl");
    private final String scope = resourceBundle.getString("scope");
    private final String signInURL = resourceBundle.getString("signInUrl");
    private final String redirectURI = resourceBundle.getString("RuName");
    private final String clientID = resourceBundle.getString("appId");
    private final String base64String = resourceBundle.getString("Base64String");

    public eBayAPI() {
        httpClient = HttpClientBuilder.create().build();
    }

    String generateURL() throws UnsupportedEncodingException {
        return signInURL + "?client_id=" +
                clientID + "&response_type=code" +
                "&redirect_uri=" + redirectURI +
                "&scope=" + URLEncoder.encode(scope, "UTF-8");
    }

    public EbayToken getToken(String code) throws IOException {
        String parameters = "grant_type=authorization_code" +
                "&code=" + URLEncoder.encode(code, "UTF-8") +
                "&redirect_uri=" + redirectURI;
        EbayToken ebayToken = new ObjectMapper()
                .readValue(postRequest(tokenUrl, "application/x-www-form-urlencoded",
                        parameters, "Basic " + base64String).getEntity().getContent(), EbayToken.class);
        return ebayToken;
    }

    public EbayToken tokenFromRefreshToken(EbayToken token) throws IOException, JSONException {
        String parameters = "grant_type=refresh_token" +
                "&refresh_token=" + token.getRefreshToken() +
                "&scope=" + URLEncoder.encode(scope, "UTF-8");
        HttpResponse response = postRequest(tokenUrl, "application/x-www-form-urlencoded", parameters,
                "Basic " + base64String);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null)
            stringBuilder.append(line);
        JSONObject listingJSON = new JSONObject(stringBuilder.toString());
        bufferedReader.close();
        token.setAccessToken((String) listingJSON.get("access_token"));
        return token;
    }

    HttpResponse createOrReplaceInventoryItem(InventoryItem item, String token) throws IOException {
        String itemJSON = new ObjectMapper().writeValueAsString(item);
        return putRequest(inventoryUri + item.getSku(), itemJSON, "Bearer " + token);
    }

    InventoryItem getInventoryItem(String inventoryItemSKU, String token) throws IOException, URISyntaxException {
        HttpResponse response = getRequest(inventoryUri + inventoryItemSKU, "Bearer " + token);
        return new ObjectMapper().readValue(response.getEntity().getContent(), InventoryItem.class);
    }

    InventoryItems getAllInventoryItems(String token) throws IOException, URISyntaxException {
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

    InventoryLocation getInventoryLocation(String merchantLocationKey, String token) throws IOException, URISyntaxException {
        HttpResponse response = getRequest(locationUri + merchantLocationKey, "Bearer " + token);
        return new ObjectMapper().readValue(response.getEntity().getContent(), InventoryLocation.class);
    }

    InventoryLocations getAllInventoryLocations(String token) throws IOException, URISyntaxException {
        HttpResponse response = getRequest(locationUri, "Bearer " + token);

        return new ObjectMapper().readValue(response.getEntity().getContent(), InventoryLocations.class);
    }

    HttpResponse deleteInventoryLocation(String merchantLocationKey, String token) throws IOException {
        return deleteRequest(locationUri + merchantLocationKey, "Bearer " + token);
    }

    HttpResponse updateInventoryLocation(InventoryLocation inventoryLocation,
                                         String token) throws IOException, JSONException {
        String locationJSON = new ObjectMapper().writeValueAsString(inventoryLocation);
        HttpResponse response = postRequest(
                locationUri + inventoryLocation.getMerchantLocationKey() + "/update_location_details",
                "application/json", locationJSON, "Bearer " + token);
        return response;
    }

    HttpResponse createOffer(Offer offer, String token) throws IOException {
        String offerJSON = new ObjectMapper().writeValueAsString(offer);
        return postRequest(offerUri, "application/json", offerJSON, "Bearer " + token);
    }

    Offers getOffers(String sku, String token) throws URISyntaxException, IOException {
        String url = offerUri.substring(0, offerUri.length() - 1) + "?sku=" + sku;
        HttpResponse response = getRequest(url, "Bearer " + token);
        return new ObjectMapper().readValue(response.getEntity().getContent(), Offers.class);
    }

    Offer getOffer(String offerId, String token) throws IOException, URISyntaxException {
        HttpResponse response = getRequest(offerUri + offerId, "Bearer " + token);
        Offer offer = new ObjectMapper().readValue(response.getEntity().getContent(), Offer.class);
        return offer;
    }

    HttpResponse deleteOffer(String offerId, String token) throws IOException {
        return deleteRequest(offerUri + offerId, "Bearer " + token);
    }

    String publishOffer(String offerId, String token) throws IOException, JSONException {
        HttpResponse response = postRequest(offerUri + offerId + "/publish", "application/json", null,
                "Bearer " + token);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null)
            stringBuilder.append(line);
        JSONObject listingJSON = new JSONObject(stringBuilder.toString());
        bufferedReader.close();
        return (String) listingJSON.get("listingId");
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
        if (parameters != null) request.setEntity(new StringEntity(parameters));
        return httpClient.execute(request);
    }

    HttpResponse getRequest(String url, String authorization)
            throws IOException, URISyntaxException {
        URI uri = new URI(url);
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
