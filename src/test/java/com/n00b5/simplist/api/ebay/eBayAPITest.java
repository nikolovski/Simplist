package com.n00b5.simplist.api.ebay;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/4/17
 */
public class eBayAPITest {
    String PATH_TO_CHROME_DRIVER="/usr/local/Cellar/chromedriver/2.27/bin/chromedriver";
    WebDriver driver;
    String accessToken;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.quit();
    }

    @Test
    public void generateURL() throws Exception {
//        String url = eBayAPI.generateURL();
    }

    @Test
    public void getToken() throws Exception {

    }

    @Test
    public void getResponse() throws Exception {

    }

}