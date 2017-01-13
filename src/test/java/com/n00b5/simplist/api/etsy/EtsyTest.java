package com.n00b5.simplist.api.etsy;

import com.github.scribejava.core.model.OAuth1AccessToken;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by louislopez on 1/5/17.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EtsyTest {


    private WebDriver driver;
    private String chromedriver = "/usr/local/bin/chromedriver";
    OAuth1AccessToken token;
    EtsySelinium etsy;

    public OAuth1AccessToken getToken() {
        return token;
    }


    @Before
    public OAuth1AccessToken setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
               chromedriver);
        driver = new ChromeDriver();
        etsy = new EtsySelinium(driver);
        return etsy.signin();
    }


    @Ignore
    @Test
    public void getAll() throws Exception {
        //etsy.getView("http://localhost:8080/etsy/getAll?"+urlEnd);
        etsy.getView("http://localhost:8080/pages/EtsyAPITest/etsy_index.html");
        etsy.getAllListings();
    }


    @Ignore
    @Test
    public void getById() throws Exception {
        etsy.getView("http://localhost:8080/pages/EtsyAPITest/etsy_index.html");
        etsy.getByID();
    }


    @Ignore
    @Test
    public void addItem() throws Exception {
        etsy.getView("http://localhost:8080/pages/EtsyAPITest/etsy_index.html");
        etsy.addItem();
    }

    @Ignore
    @Test
    public void deleteItem() throws Exception {
        etsy.getView("http://localhost:8080/pages/EtsyAPITest/etsy_index.html");
        etsy.deleteItem();
    }


    @Ignore
    @Test
    public void update() throws Exception {
        etsy.getView("http://localhost:8080/pages/EtsyAPITest/etsy_index.html");
        etsy.updateItem();
    }


    @Ignore
    @Test
    public void clientToController(){
        etsy.getView("http://localhost:8080/pages/EtsyAPITest/etsy_index.html");
    }


}