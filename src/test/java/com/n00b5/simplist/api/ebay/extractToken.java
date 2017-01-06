package com.n00b5.simplist.api.ebay;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/4/17
 */
public class extractToken {
    ResourceBundle bundle = ResourceBundle.getBundle("ebay_login");
    String PATH_TO_CHROME_DRIVER=bundle.getString("chromedriver");
    WebDriver driver;
    String username, password, signinURL, accessToken;
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
//        driver.quit();
    }

    @Test
    public void extractToken() throws Exception {
        signinURL = new eBayAPI().generateURL(
                "MartinoN-Simplist-SBX-a45f6444c-5bd48c02",
                "Martino_Nikolov-MartinoN-Simpli-yxdhshy",
                "https://signin.sandbox.ebay.com/authorize",
                "https://api.ebay.com/oauth/api_scope https://api.ebay.com/oauth/api_scope/buy.order.readonly https://api.ebay.com/oauth/api_scope/buy.guest.order https://api.ebay.com/oauth/api_scope/sell.marketing.readonly https://api.ebay.com/oauth/api_scope/sell.marketing https://api.ebay.com/oauth/api_scope/sell.inventory.readonly https://api.ebay.com/oauth/api_scope/sell.inventory https://api.ebay.com/oauth/api_scope/sell.account.readonly https://api.ebay.com/oauth/api_scope/sell.account https://api.ebay.com/oauth/api_scope/sell.fulfillment.readonly https://api.ebay.com/oauth/api_scope/sell.fulfillment https://api.ebay.com/oauth/api_scope/sell.analytics.readonly",
                "code");
        assertNotNull(signinURL);
        LoginPage page = new LoginPage(driver,signinURL);
        assertEquals("Sign in or Register | eBay", page.getPageTitle());
        page.setUsername(username);
        page.setPassword(password);
        page.clickLoginButton();
        page.clickAgree();
    }

}