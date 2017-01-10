package com.n00b5.simplist.api.ebay;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Project: Simplist
 *
 * @author Martino Nikolovski
 * @date 1/4/17
 */
public class extractToken {
    ResourceBundle bundle = ResourceBundle.getBundle("ebay_login");
    String PATH_TO_CHROME_DRIVER=bundle.getString("chromedriver");
    WebDriver driver;
    String username, password, signinURL;
    @Before
    public void setUp() throws Exception {
        username = bundle.getString("username");
        password = bundle.getString("password");
        System.setProperty("webdriver.chrome.driver",PATH_TO_CHROME_DRIVER);
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void extractToken() throws Exception {
        signinURL = new eBayAPI().generateURL();
        assertNotNull(signinURL);
        LoginPage page = new LoginPage(driver,signinURL);
        assertEquals("Sign in or Register | eBay", page.getPageTitle());
        page.setUsername(username);
        page.setPassword(password);
        page.clickLoginButton();
        page.clickAgree();
    }

    @Test
    public void getTokenFromRefreshTokenTest() throws IOException, JSONException {
        EbayToken token = new EbayToken();
        token.setRefreshToken(bundle.getString("refresh_token"));
        System.out.println(new eBayAPI().tokenFromRefreshToken(token));
    }

}