package com.n00b5.simplist.api.ebay;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
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
                        "POST"), TokenResponse.class);
        System.out.println(tokenResponse);
        return tokenResponse.getAccessToken();
    }

    String createOrReplaceInventoryItem(InventoryItem item, String token) throws IOException {
        URL url = new URL("https://api.sandbox.ebay.com/sell/inventory/v1/inventory_item/"+item.getSku());
        String itemJSON = new ObjectMapper().writeValueAsString(item);
        System.err.println(itemJSON);
        InputStream response = getResponse(url,itemJSON,"application/json",
                "en-US", "Bearer "+token,"PUT"
                );
        BufferedReader in = new BufferedReader(
                new InputStreamReader(response));
        String inputLine;
        StringBuffer buffer = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            buffer.append(inputLine);
        }
        in.close();
        return inputLine;
    }



    InputStream getResponse(URL url,
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
        wr.writeBytes(parameters);
        wr.flush();
        wr.close();
        return conn.getInputStream();
    }
}
