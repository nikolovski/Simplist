package com.n00b5.simplist.api.ebay;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Project: simplist
 *
 * @author d4k1d23
 * @date 1/12/17
 */
public class EbayItemTest {
    String token;
    ResourceBundle bundle = ResourceBundle.getBundle("ebay_login");
    @Before
    public void setUp() throws Exception {
        EbayToken reftoken = new EbayToken();
        reftoken.setRefreshToken(bundle.getString("refresh_token"));
        reftoken = new eBayAPI().tokenFromRefreshToken(reftoken);
        token = reftoken.getAccessToken();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createSimpleList() throws Exception {
        EbayItem ebayItem = new EbayItem();
        String imageUrls[] = {"https://s-media-cache-ak0.pinimg.com/originals/36/5b/96/365b96a06def26baa23b84b6af47b322.jpg",
                "http://nord.imgix.net/Zoom/19/_12140679.jpg"};
        ebayItem.setProductTitle("Test Bracelet Bery Nice LisitngItem 3");
        ebayItem.setProductDescription("Bracelet description");
        ebayItem.setProductImageUrls(imageUrls);
        ebayItem.setProductBrand("Nrrrrd");
        ebayItem.setProductMpn("BRCMPN");
        ebayItem.setSku("BRAC12356");
        ebayItem.setCurrencyValue(0.20);
        ebayItem.setListingDescription("Some listing description");
        ebayItem.createSimpleList(token);

    }

}