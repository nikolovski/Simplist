package com.n00b5.simplist.api.Shopify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Shehar on 1/5/2017.
 */
public class TokenAuthTest {
    private WebDriver driver;
    private String token;


    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        makeDriver();
        TokenAuth page = new TokenAuth(driver);
        page.setUsername("syar0052@gmail.com");
        page.setPassword("simplist");
        sleep();
        FindTokenPage findAuthPage = page.clickLoginButton();

        String token = "";
        List<WebElement> getToken = driver.findElements(By.tagName("body"));
        for (WebElement body : getToken) {
            token = body.getText();
            System.out.println(token);
        }
        token = token.substring(17,49);

        return token;
    }
    @Before
    public void makeDriver(){
        String PATH_TO_CHROME_DRIVER = "/usr/local/bin/chromedriver"; //"C:/selenium/chromedriver.exe"; //"C:/selenium/chromedriver.exe"; //"C:/selenium/chromedriver.exe";
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
        page.setUsername("syar0052@gmail.com");page.setPassword("simplist");
        sleep();
        FindTokenPage findAuthPage = page.clickLoginButton();

        String token = "";
        List<WebElement> getToken = driver.findElements(By.tagName("body"));
        for (WebElement body : getToken) {
            token = body.getText();
            System.out.println(token);
        }
        token = token.substring(17,49);
        System.out.println(token);
        setToken(token);
        driver.get("https://paperss.myshopify.com/admin/products");
        sleep();
        SeleniumCRUD crud = new SeleniumCRUD(driver);
        crud.setCreate("Test","Test","Test","Test","Test","12.99");
        crud.clickAddButton();
        sleep();
        driver.get("https://paperss.myshopify.com/admin/products");
        sleep();




    }


    @Test
    public void updateItem() throws Exception {
        TokenAuth page = new TokenAuth(driver);
        page.setUsername("syar0052@gmail.com");
        page.setPassword("simplist");
        sleep();
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
        crud.setUpdate(updateID,"UPDATE","UPDATE","UPDATE","UPDATE","UPDATE","11.99");
        crud.clickUpdateButton();
        sleep();
        driver.get("https://paperss.myshopify.com/admin/products");
        sleep();

    }

    @Test
    public void deleteItem() throws Exception {
        TokenAuth page = new TokenAuth(driver);
        page.setUsername("syar0052@gmail.com");
        page.setPassword("simplist");
        sleep();
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

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
