package com.n00b5.simplist.api.ebay;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/4/17
 */
public class eBayAPI {
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
        URL obj = new URL(tokenUrl);
        String parameters = "grant_type=authorization_code&" +
                "&code=" + URLEncoder.encode(code, "UTF-8") +
                "&redirect_uri=" + redirectURI;
        TokenResponse tokenResponse = new ObjectMapper()
                .readValue(getResponse(obj,
                        parameters, "application/x-www-form-urlencoded",
                        "en-US",
                        "Basic " + base64String,
                        "POST").getInputStream(), TokenResponse.class);
        System.out.println(tokenResponse);
        return tokenResponse.getAccessToken();
    }

    int createOrReplaceInventoryItem(InventoryItem item, String token) throws IOException {
        URL url = new URL("https://api.sandbox.ebay.com/sell/inventory/v1/inventory_item/"+item.getSku());
        String itemJSON = new ObjectMapper().writeValueAsString(item);
        HttpsURLConnection response = getResponse(url,itemJSON,"application/json",
                "en-US", "Bearer "+token,"PUT"
                );
        return response.getResponseCode();
    }

    int getInventoryItem(String inventoryItemSKU, String token) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://api.sandbox.ebay.com/sell/inventory/v1/inventory_item/"+inventoryItemSKU);
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Authorization","Bearer "+token);
        request.addHeader("Accept","application/json");
        HttpResponse response = httpClient.execute(request);
        System.out.println(response.getStatusLine());
        BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String inputLine;
        StringBuffer stringBuffer = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            stringBuffer.append(inputLine);
        }
        in.close();
        System.out.println(stringBuffer);
        return 200;
    }



    HttpsURLConnection getResponse(URL url,
                                   String parameters,
                                   String contentType,
                                   String contentLanguage,
                                   String authorization,
                                   String method) throws IOException {
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", contentType);
        conn.setRequestProperty("Content-Language", contentLanguage);
        conn.setRequestProperty("Authorization", authorization);
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestMethod(method);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        if(parameters!=null) wr.writeBytes(parameters);
        wr.flush();
        wr.close();
        return conn;
    }
}
