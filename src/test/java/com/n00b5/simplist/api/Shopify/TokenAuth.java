package com.n00b5.simplist.api.Shopify;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Shehar on 1/5/2017.
 */
public class TokenAuth {

    private final String usernameBoxName = "login";
    private final String passwordBoxName = "password";
    private final String loginButtonName = "commit";

    private WebDriver driver;

    public TokenAuth(WebDriver driver){
        this.driver = driver;
        driver.get("http://paperss.myshopify.com/admin/oauth/authorize?client_id=9b3a07ad73728f8282ae69b8e7e41ae7&scope=read_products, write_products&redirect_uri=http://localhost:8080/shopify/authorize&state=1&grant_options[]=per-user");
    }

    public void setUsername(String username){
        driver.findElement(By.name(usernameBoxName))
                .sendKeys(username);
        implicitwait();
    }

    public void setPassword(String password){
        driver.findElement(By.name(passwordBoxName))
                .sendKeys(password);
        implicitwait();
    }

    public FindTokenPage clickLoginButton(){
        driver.findElement(By.name(loginButtonName)).click();
        implicitwait();
        return new FindTokenPage(driver);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    private void implicitwait(){
        driver.manage().timeouts().implicitlyWait(1000000, TimeUnit.SECONDS);
    }


}
