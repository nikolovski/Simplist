package com.n00b5.simplist.api.etsy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth1AccessToken;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by louislopez on 1/5/17.
 */
public class EtsySelinium {

    private WebDriver driver;


    public EtsySelinium(WebDriver driver){
        this.driver = driver;
        driver.get("http://localhost:8080/etsy/oauth");

    }

    public OAuth1AccessToken signin() throws IOException {
        driver.findElement(By.xpath("//*[@id=\"username-existing\"]")).sendKeys("louisalopez93@gmail.com"); //email
        driver.findElement(By.xpath("//*[@id=\"password-existing\"]")).sendKeys("123rev321"); //password
        driver.findElement(By.xpath("//*[@id=\"signin_button\"]")).click(); // sign in
        driver.findElement(By.xpath("//*[@id=\"oauth-submit\"]/span/input")).click(); //allow access
        String x = driver.findElement(By.xpath("/html/body")).getText();

        //CONVERT JSON to OAUTH
        OAuth1Converter converter = new ObjectMapper().readValue(x, OAuth1Converter.class);
        OAuth1AccessToken token = new OAuth1AccessToken(converter.getOauthToken(), converter.getOauthTokenSecret());

        return token;
    }

    public void getView(String URL){
        driver.get(URL);
    }

    public void addItem(){
        driver.findElement(By.xpath("//*[@id=\"quantity\"]")).sendKeys("10");
        driver.findElement(By.xpath("//*[@id=\"title\"]")).sendKeys("BOOKSSSS");
        driver.findElement(By.xpath("//*[@id=\"description\"]")).sendKeys("ELSALVADOR");
        driver.findElement(By.xpath("//*[@id=\"addSubmit\"]")).click();
    }

    public void updateItem(){
        driver.findElement(By.xpath("//*[@id=\"updateItemId\"]")).sendKeys("490109298");
        driver.findElement(By.xpath("//*[@id=\"updateQuantity\"]")).sendKeys("2");
        driver.findElement(By.xpath("//*[@id=\"updateTitle\"]")).sendKeys("Tester2");
        driver.findElement(By.xpath("//*[@id=\"updateDescription\"]")).sendKeys("ThisLitTwoTimes");
        driver.findElement(By.xpath("//*[@id=\"updateSubmit\"]")).click();
    }


    public void deleteItem(){
        driver.findElement(By.xpath("//*[@id=\"deleteId\"]")).sendKeys("489447826");
        driver.findElement(By.xpath("//*[@id=\"deleteSubmit\"]")).click();
    }


    public void getAllListings() {
        driver.findElement(By.xpath("//*[@id=\"getAllListings\"]")).click();
    }

    public void getByID(){
        driver.findElement(By.xpath("//*[@id=\"getByID\"]")).sendKeys("488901146");
        driver.findElement(By.xpath("//*[@id=\"getByIDSubmit\"]")).click();
    }
}
