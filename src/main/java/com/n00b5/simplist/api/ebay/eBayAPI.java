package com.n00b5.simplist.api.ebay;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/4/17
 */
public abstract class eBayAPI {
    static String generateURL(String clientID,
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

    static String getToken(String tokenUrl, String redirectURI, String base64String, String code) throws IOException {
        URL obj = new URL(tokenUrl);
        String parameters = "grant_type=authorization_code&" +
                "&code=" + URLEncoder.encode(code, "UTF-8") +
                "&redirect_uri=" + redirectURI;
        TokenResponse tokenResponse = new ObjectMapper()
                .readValue(getResponse(obj,
                        parameters, "application/x-www-form-urlencoded",
                        "Basic " + base64String,
                        "POST"), TokenResponse.class);
        System.out.println(tokenResponse);
        return tokenResponse.getAccessToken();
    }

    static InputStream getResponse(URL url,
                                   String parameters,
                                   String contentType,
                                   String authorization,
                                   String method) throws IOException {
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", contentType);
        conn.setRequestProperty("Authorization", authorization);
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
