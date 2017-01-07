package com.n00b5.simplist.api.ebay;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Project: Simplist
 *
 * @author Martino Nikolovski
 * @date 1/4/17
 */
public class LoginPage {
    private WebDriver driver;


    public LoginPage(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);
    }
    public String getPageTitle(){
        return driver.getTitle();
    }
    public void clickLoginButton(){
        driver.findElement(By.name("sgnBt")).click();
        implicitWait();
    }
    public void clickAgree(){
        driver.findElement(By.xpath("//*[@id=\"frmAuth\"]/div/div[2]/div[1]/input")).click();
        implicitWait();
    }

    public void setUsername(String username) {
        implicitWait();
        driver.findElement(By.xpath("//input[@type='text']"))
                .sendKeys(username);
    }

    public void setPassword(String password) {
        implicitWait();
        driver.findElement(By.xpath("//input[@type='password']"))
                .sendKeys(password);
    }
    private void implicitWait(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}
