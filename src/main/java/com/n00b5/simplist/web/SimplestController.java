package com.n00b5.simplist.web;

import com.n00b5.simplist.api.Shopify.ShopifyAPI;
import com.n00b5.simplist.api.Shopify.ShopifyCRUD;
import com.n00b5.simplist.api.Shopify.ShopifyItem;
import com.n00b5.simplist.api.etsy.EtsyController;
import com.n00b5.simplist.api.etsy.EtsyItem;
import com.n00b5.simplist.beans.SimplistItem;
import com.n00b5.simplist.data.Facade;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by louislopez on 1/11/17.
 */
@Controller
@RequestMapping(value = "/simplest")
public class SimplestController {

    @Autowired
    Facade facade;

    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public void add(@RequestBody SimplistItem simplistItem) {


        try {

            System.out.println("SIMPLEST ITEM " + simplistItem.toString());

            ShopifyItem shopifyitem = simplistItem.getShopifyItem();
            EtsyItem etsyItem = simplistItem.getEtsyItem();

            System.out.println("Shopify ITem is " + shopifyitem);
            System.out.println("Etsy ITem is " + etsyItem);




            EtsyController etsy = new EtsyController();
            EtsyItem etsyNewItem = etsy.addItem(etsyItem); // now in DB & API
            facade.etsyAddItem(etsyNewItem);

            System.out.println("added");

            ShopifyCRUD shopify = new ShopifyCRUD();
            ShopifyItem shopifyNewItem = shopify.createItem(shopifyitem); // now API & DB
            facade.addShopifyItem(shopifyNewItem);
            System.out.println("NEW SHOPIFY ITEM " + shopifyNewItem.toString());

            facade.simpliestCreateItem(new SimplistItem(shopifyNewItem,etsyNewItem));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        }

        //item to delete 106


    @RequestMapping(value = "/delete" , method = RequestMethod.DELETE)
    public void add(@RequestParam("itemID")String id) {
        System.out.println("ID IS " + id);
        facade.simpliestDeleteItem(id);
    }



}
