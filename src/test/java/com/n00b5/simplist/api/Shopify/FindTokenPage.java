package com.n00b5.simplist.api.Shopify;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Shehar on 1/5/2017.
 */
public class FindTokenPage {
    private WebDriver driver;

    public FindTokenPage(WebDriver driver){
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void implicitwait(){
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
}
