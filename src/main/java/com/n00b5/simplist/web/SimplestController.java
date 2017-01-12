package com.n00b5.simplist.web;

import com.n00b5.simplist.api.Shopify.ShopifyCRUD;
import com.n00b5.simplist.api.Shopify.ShopifyItem;
import com.n00b5.simplist.api.etsy.EtsyController;
import com.n00b5.simplist.api.etsy.EtsyItem;
import com.n00b5.simplist.beans.SimplistItem;
import com.n00b5.simplist.data.Facade;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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



            ShopifyCRUD shopify = new ShopifyCRUD();
            ShopifyItem shopifyNewItem = shopify.createItem(shopifyitem); // now API & DB
            System.out.println("NEW SHOPIFY ITEM " + shopifyNewItem.toString());
            facade.addShopifyItem(shopifyNewItem);
            System.out.println("added");


            EtsyController etsy = new EtsyController();
            EtsyItem etsyNewItem = etsy.addItem(etsyItem); // now in DB & API
            System.out.println("new etsy item " + etsyNewItem.toString());
            facade.etsyAddItem(etsyNewItem);
            System.out.println("added etsy");





            System.out.println("SHOPIFY ITEM ID" + shopifyNewItem.getShopifyId());

            //facade.simpliestCreateItem(new SimplistItem(shopifyNewItem,etsyNewItem));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        }


}
