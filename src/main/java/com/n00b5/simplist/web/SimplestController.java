package com.n00b5.simplist.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n00b5.simplist.api.Shopify.ShopifyCRUD;
import com.n00b5.simplist.api.Shopify.ShopifyItem;
import com.n00b5.simplist.api.Shopify.ShopifyToken;
import com.n00b5.simplist.api.etsy.EtsyController;
import com.n00b5.simplist.api.etsy.EtsyItem;
import com.n00b5.simplist.api.etsy.EtsyToken;
import com.n00b5.simplist.beans.SimplistItem;
import com.n00b5.simplist.beans.User;
import com.n00b5.simplist.data.Facade;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by louislopez on 1/11/17.
 */
@Controller
@RequestMapping(value = "/simplist")
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
        System.out.println(id + " is the id that will be passed ");
        SimplistItem item = facade.getSimplestItemById(Integer.parseInt(id));
        System.out.println(item.getId());
        String shopifyID = item.getShopifyItem().getShopifyId();
        String etsyID = item.getEtsyItem().getListing_id();
        System.out.println("DELETE SHOPIFY ITEM:" +shopifyID);
        EtsyController etsy = new EtsyController();
        ShopifyCRUD shopify = new ShopifyCRUD();
        try {

            shopify.deleteItem("{\"id\":\""+shopifyID+"\"}");
            etsy.deleteItem(etsyID);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("ID IS " + id);
        facade.simpliestDeleteItem(Integer.parseInt(id));
    }


    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    public void update(@RequestBody SimplistItem simplistItem) {
        try {
            int id = simplistItem.getId();//got the id
            SimplistItem sItem = facade.getSimplestItemById(id); //load the simpest item

            ShopifyItem shopifyitem = sItem.getShopifyItem();
            shopifyitem = simplistItem.getShopifyItem();
            shopifyitem.setShopifyId(sItem.getShopifyItem().getShopifyId());
            sItem.setShopifyItem(shopifyitem);
            ShopifyCRUD shopify = new ShopifyCRUD();
            shopify.updateItem(shopifyitem); // now API & DB
            facade.shopifyUpdateItem(shopifyitem,shopifyitem.getShopifyId());

            EtsyItem etsyItem = sItem.getEtsyItem();
            etsyItem = simplistItem.getEtsyItem();
            etsyItem.setListing_id(sItem.getEtsyItem().getListing_id());
            sItem.setEtsyItem(etsyItem);
            EtsyController eController = new EtsyController();
            eController.update(etsyItem,sItem.getEtsyItem().getListing_id()); // now API & DB
            facade.etsyUpdateItem(etsyItem,etsyItem.getListing_id());

            facade.updateSimplistItem(id,sItem.getEtsyItem(),sItem.getShopifyItem());


            //facade.updateSimplistItem(id,etsyItem,shopifyitem);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


    @RequestMapping(value = "/getMyItems", method = RequestMethod.GET)
    public @ResponseBody List<SimplistItem> getMyItems(@CookieValue("user") String u,
                           @CookieValue("shopifyToken") String s,
                           @CookieValue("etsyToken") String e) throws IOException, JSONException {


        System.out.println("IN CONTROLLER");

        User user = new ObjectMapper().readValue(u,User.class);

        EtsyToken etsyToken = new ObjectMapper().readValue(e, EtsyToken.class); // not token
        ShopifyToken shopifyToken = new ObjectMapper().readValue(s, ShopifyToken.class);



        return facade.getItemsByUserID(user);

    }





}
