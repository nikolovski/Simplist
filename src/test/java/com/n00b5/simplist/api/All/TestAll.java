package com.n00b5.simplist.api.All;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.n00b5.simplist.api.Shopify.TokenAuthTest;
import com.n00b5.simplist.api.etsy.EtsyTest;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by louislopez on 1/10/17.
 */
public class TestAll {

    private String chromedriver = "/usr/local/bin/chromedriver";
    private WebDriver driver;
    OAuth1AccessToken etsyToken;
    String shopifyToken;
    SeleniumAll selenium;




    @Test
    @Before
    public void setUp() throws Exception {
//
//        shopifyToken = new TokenAuthTest().getToken();
//        System.out.println("shopify token " + shopifyToken);
//
//        etsyToken = new EtsyTest().setUp();
//        System.out.println("etsy token " + etsyToken);


        System.setProperty("webdriver.chrome.driver",
                chromedriver);
        driver = new ChromeDriver();
        selenium = new SeleniumAll(driver);


        selenium.getView("http://localhost:8080/pages/index.html");
        selenium.logIn();
    }



    @Test
    public void addItems(){
        selenium.getView("http://localhost:8080/pages/index.html");
        selenium.addItems();
    }


    @Test
    public void deleteItems(){
        selenium.getView("http://localhost:8080/pages/dashboard_test.html");
        selenium.deleteItems();
    }

    @Test
    public void updateItems(){
        selenium.getView("http://localhost:8080/pages/dashboard_test.html");
        selenium.updateItems();
    }
}
