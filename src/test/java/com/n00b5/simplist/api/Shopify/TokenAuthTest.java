package com.n00b5.simplist.api.Shopify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Shehar on 1/5/2017.
 */
public class TokenAuthTest {
    private WebDriver driver;

    @Before
    public void makeDriver(){
        String PATH_TO_CHROME_DRIVER = "C:/selenium/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",
                PATH_TO_CHROME_DRIVER);
        driver = new ChromeDriver();
    }

    public void sleep(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createItem(){
        TokenAuth page = new TokenAuth(driver);
        page.setUsername("syar0052@gmail.com");
        page.setPassword("Aa0607645");
        FindTokenPage findAuthPage = page.clickLoginButton();
        //findAuthPage = page.clickLoginButton();
        //driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        org.junit.Assert.assertEquals("",
                findAuthPage.getPageTitle());
        String token = "";
        List<WebElement> getToken = driver.findElements(By.tagName("body"));
        for (WebElement body : getToken) {
            token = body.getText();
            System.out.println(token);
        }
        token = token.substring(17,49);
        System.out.println(token);
        driver.get("https://paperss.myshopify.com/admin/products");
        sleep();
        SeleniumCRUD crud = new SeleniumCRUD(driver);
        crud.setCreate("Test","Test","Test","Test","Test");
        crud.clickAddButton();
        sleep();
        driver.get("https://paperss.myshopify.com/admin/products");
        sleep();




    }


    @Test
    public void updateItem() throws Exception {
        TokenAuth page = new TokenAuth(driver);
        page.setUsername("syar0052@gmail.com");
        page.setPassword("Aa0607645");
        FindTokenPage findAuthPage = page.clickLoginButton();
        String token = "";String updateID = "";
        List<WebElement> getToken = driver.findElements(By.tagName("body"));
        for (WebElement body : getToken) {
            token = body.getText();
            System.out.println(token);
        }
        token = token.substring(17,49);
        System.out.println(token);
        driver.get("https://paperss.myshopify.com/admin/products");
        sleep();
        driver.get("https://paperss.myshopify.com/admin/products.json");
        List<WebElement> getID = driver.findElements(By.tagName("body"));
        for (WebElement body : getID) {
            updateID = body.getText();
            System.out.println(updateID);
        }
        updateID = updateID.substring(19,29);
        System.out.println(updateID);
        SeleniumCRUD crud = new SeleniumCRUD(driver);
        crud.setUpdate(updateID,"UPDATE","UPDATE","UPDATE","UPDATE","UPDATE");
        crud.clickUpdateButton();
        sleep();
        driver.get("https://paperss.myshopify.com/admin/products");
        sleep();

    }

    @Test
    public void deleteItem() throws Exception {
        TokenAuth page = new TokenAuth(driver);
        page.setUsername("syar0052@gmail.com");
        page.setPassword("Aa0607645");
        FindTokenPage findAuthPage = page.clickLoginButton();
        String token = "";String updateID = "";
        List<WebElement> getToken = driver.findElements(By.tagName("body"));
        for (WebElement body : getToken) {
            token = body.getText();
            System.out.println(token);
        }
        token = token.substring(17,49);
        System.out.println(token);
        driver.get("https://paperss.myshopify.com/admin/products");
        sleep();
        driver.get("https://paperss.myshopify.com/admin/products.json");
        List<WebElement> getID = driver.findElements(By.tagName("body"));
        for (WebElement body : getID) {
            updateID = body.getText();
            System.out.println(updateID);
        }
        updateID = updateID.substring(19,29);
        System.out.println(updateID);
        SeleniumCRUD crud = new SeleniumCRUD(driver);
        crud.setDelete(updateID);
        crud.clickdeleteButton();
        sleep();
        driver.get("https://paperss.myshopify.com/admin/products");
        sleep();

    }

    private void implicitwait(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void quit(){
        driver.quit();
    }
}
