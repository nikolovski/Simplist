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
        driver.findElement(By.xpath("//*[@id=\"quantity\"]")).sendKeys("5");
        driver.findElement(By.xpath("//*[@id=\"title\"]")).sendKeys("MAMA222");
        driver.findElement(By.xpath("//*[@id=\"description\"]")).sendKeys("PAPA222");


        driver.findElement(By.xpath("//*[@id=\"company\"]")).sendKeys("Revature");
        driver.findElement(By.xpath("//*[@id=\"type\"]")).sendKeys("Typeyyyy");
        driver.findElement(By.xpath("//*[@id=\"tag\"]")).sendKeys("Taggy");

        driver.findElement(By.xpath("//*[@id=\"addSubmit\"]")).click();
    }

    public void deleteItems() {
        driver.findElement(By.xpath("//*[@id=\"deleteid\"]")).sendKeys("107");
        driver.findElement(By.xpath("//*[@id=\"deleteSubmit\"]")).click();
    }
}
