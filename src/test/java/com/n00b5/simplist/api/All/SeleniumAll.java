package com.n00b5.simplist.api.All;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by louislopez on 1/10/17.
 */
public class SeleniumAll {

    private WebDriver driver;
    public SeleniumAll(WebDriver driver){
        this.driver = driver;

    }

    public void getView(String URL){
        driver.get(URL);
    }

    public void addItems() {
        driver.findElement(By.xpath("//*[@id=\"quantity\"]")).sendKeys("10");
        driver.findElement(By.xpath("//*[@id=\"title\"]")).sendKeys("ADDED IN INDEX");
        driver.findElement(By.xpath("//*[@id=\"description\"]")).sendKeys("IN INDEX");


        driver.findElement(By.xpath("//*[@id=\"company\"]")).sendKeys("DASRP");
        driver.findElement(By.xpath("//*[@id=\"type\"]")).sendKeys("TYPE");
        driver.findElement(By.xpath("//*[@id=\"tag\"]")).sendKeys("TAAAFSAAG");

        driver.findElement(By.xpath("//*[@id=\"addSubmit\"]")).click();
    }

    public void deleteItems() {
        driver.findElement(By.xpath("//*[@id=\"deleteid\"]")).sendKeys("123");
        driver.findElement(By.xpath("//*[@id=\"deleteSubmit\"]")).click();
    }

    public void updateItems() {
        driver.findElement(By.xpath("//*[@id=\"itemid\"]")).sendKeys("120");
        driver.findElement(By.xpath("//*[@id=\"quantityid\"]")).sendKeys("5");
        driver.findElement(By.xpath("//*[@id=\"titleid\"]")).sendKeys("RARA222");
        driver.findElement(By.xpath("//*[@id=\"descriptionid\"]")).sendKeys("PAPA222");


        driver.findElement(By.xpath("//*[@id=\"companyid\"]")).sendKeys("Revature");
        driver.findElement(By.xpath("//*[@id=\"typeid\"]")).sendKeys("Typeyyyy");
        driver.findElement(By.xpath("//*[@id=\"tagid\"]")).sendKeys("Taggy");

        driver.findElement(By.xpath("//*[@id=\"addSubmitid\"]")).click();
    }

    public void logIn() {
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("test@test.com");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("test");
        driver.findElement(By.xpath("//*[@id=\"login_btn\"]")).click();

    }
}
