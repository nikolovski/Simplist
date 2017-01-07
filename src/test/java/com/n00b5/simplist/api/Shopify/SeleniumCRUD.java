package com.n00b5.simplist.api.Shopify;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Shehar on 1/5/2017.
 */
public class SeleniumCRUD {

    private final String title = "Title";
    private final String Description = "Description";
    private final String Company = "Company";
    private final String Product_type = "Product Type";
    private final String tags = "tags";
    private final String price = "price";
    private final String addItem = "add";

    private final String productId = "id";
    private final String TitleU = "TitleU";
    private final String DescriptionU = "DescriptionU";
    private final String CompanyU = "CompanyU";
    private final String Product_typeU = "Product TypeU";
    private final String tagsU = "tagsU";
    private final String priceU = "priceU";
    private final String updateItem = "update";

    private final String delete = "deleteID";
    private final String deleteButton = "delete";

    private WebDriver driver;

    public SeleniumCRUD(WebDriver driver){
        this.driver = driver;
        driver.get("http://localhost:8080/pages/ShopifyApiTest/APITest.html");
    }

    public void setCreate(String titleP,String DescriptionP,String CompanyP, String ProductP, String tagsP,String Price){
        driver.findElement(By.name(title))
                .sendKeys(titleP);
        driver.findElement(By.name(Description))
                .sendKeys(DescriptionP);
        driver.findElement(By.name(Company))
                .sendKeys(CompanyP);
        driver.findElement(By.name(Product_type))
                .sendKeys(ProductP);
        driver.findElement(By.name(tags))
                .sendKeys(tagsP);
        driver.findElement(By.name(price))
                .sendKeys(Price);

    }

    public void setUpdate(String idUp,String titleUp,String DescriptionUp, String CompanyUp, String ProductUp, String tagsUp,String price){
        driver.findElement(By.name(productId))
                .sendKeys(idUp);
        driver.findElement(By.name(TitleU))
                .sendKeys(titleUp);
        driver.findElement(By.name(DescriptionU))
                .sendKeys(DescriptionUp);
        driver.findElement(By.name(CompanyU))
                .sendKeys(CompanyUp);
        driver.findElement(By.name(Product_typeU))
                .sendKeys(ProductUp);
        driver.findElement(By.name(tagsU))
                .sendKeys(tagsUp);
        driver.findElement(By.name(priceU))
                .sendKeys(price);
        implicitwait();
    }

    public void setDelete(String deleteProduct){
        driver.findElement(By.name(delete))
                .sendKeys(deleteProduct);
        implicitwait();
    }

    public FindTokenPage clickAddButton(){
        driver.findElement(By.name(addItem)).click();
        System.out.println("Add button Clicked");
        implicitwait();
        return new FindTokenPage(driver);
    }

    public FindTokenPage clickUpdateButton(){
        driver.findElement(By.name(updateItem)).click();
        System.out.println("update button Clicked");
        implicitwait();
        return new FindTokenPage(driver);
    }

    public FindTokenPage clickdeleteButton(){
        driver.findElement(By.name(deleteButton)).click();
        System.out.println("Delete button Clicked");
        implicitwait();
        return new FindTokenPage(driver);
    }

    private void implicitwait(){
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
    }


}
