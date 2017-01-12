package com.n00b5.simplist.data;

import com.n00b5.simplist.api.ebay.EbayItem;
import com.n00b5.simplist.api.ebay.EbayToken;
import com.n00b5.simplist.api.ebay.eBayAPI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Project: simplist
 *
 * @author d4k1d23
 * @date 1/12/17
 */
public class EbayDAOTest {
    String token;
    ResourceBundle bundle = ResourceBundle.getBundle("ebay_login");
    EbayItem ebayItem;
    private ApplicationContext contxt;
    @Before
    public void setUp() throws Exception {
        contxt = new ClassPathXmlApplicationContext("application-context.xml");
        EbayToken reftoken = new EbayToken();
        reftoken.setRefreshToken(bundle.getString("refresh_token"));
        reftoken = new eBayAPI().tokenFromRefreshToken(reftoken);
        token = reftoken.getAccessToken();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createEbayItem() throws Exception {
        ebayItem = new EbayItem();
        String imageUrls[] = {"https://s-media-cache-ak0.pinimg.com/originals/36/5b/96/365b96a06def26baa23b84b6af47b322.jpg",
                "http://nord.imgix.net/Zoom/19/_12140679.jpg"};
        ebayItem.setProductTitle("Test very nice bracelet");
        ebayItem.setProductDescription("Bracelet description");
        ebayItem.setProductImageUrls(imageUrls);
        ebayItem.setProductBrand("David Yurman");
        ebayItem.setProductMpn("DY123");
        ebayItem.setSku("DY3456");
        ebayItem.setCurrencyValue(2300);
        ebayItem.setListingDescription("Some listing description about the bracelet");
        ebayItem.createSimpleList(token);
        contxt.getBean(EbayDAO.class).createEbayItem(ebayItem);
    }

    @Test
    public void deleteItem() throws Exception {
        EbayItem ebayItem = contxt.getBean(EbayDAO.class).getItemById("110189046518");
        assertNotNull(ebayItem);
        ebayItem.deleteOffer(token);
        contxt.getBean(EbayDAO.class).deleteItemById(ebayItem.getListingId());

    }

    @Test
    public void deleteItemById() throws Exception {

    }

    @Test
    public void getItemById() throws Exception {
        System.out.println(ebayItem);
    }

}