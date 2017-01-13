package com.n00b5.simplist.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n00b5.simplist.api.Shopify.ShopifyCRUD;
import com.n00b5.simplist.api.Shopify.ShopifyItem;
import com.n00b5.simplist.api.ebay.EbayItem;
import com.n00b5.simplist.api.ebay.EbayToken;
import com.github.scribejava.core.model.OAuth1AccessToken;
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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by louislopez on 1/11/17.
 */
@Controller
@RequestMapping(value = "/simplist")
public class SimplistController {

    @Autowired
    Facade facade;

    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public void add(@RequestBody SimplistItem simplistItem,@CookieValue("user") String user, @CookieValue("shopifyToken") String s,
                    @CookieValue("etsyToken") String e, @CookieValue("eBayToken") String eBayToken) throws URISyntaxException {


        try {
            User currentUser = new ObjectMapper().readValue(user,User.class);
            ShopifyToken shopifyToken = new ObjectMapper().readValue(s, ShopifyToken.class);
            System.out.println(shopifyToken.getAccessToken());

            System.out.println("SIMPLEST ITEM " + simplistItem.toString());
            ShopifyItem shopifyitem = simplistItem.getShopifyItem();

            EbayToken ebayToken = new ObjectMapper().readValue(eBayToken,EbayToken.class);

            ShopifyCRUD shopify = new ShopifyCRUD();
            ShopifyItem shopifyNewItem = shopify.createItem(shopifyitem,shopifyToken); // now API & DB
            facade.addShopifyItem(shopifyNewItem);
            System.out.println("NEW SHOPIFY ITEM " + shopifyNewItem.toString());


            System.out.println("ETSY TOKEN before" + e);
            EtsyToken etsyToken = new ObjectMapper().readValue(e, EtsyToken.class); // not token
            OAuth1AccessToken x = new OAuth1AccessToken(etsyToken.getAccessToken() , etsyToken.getClientSecret());
            System.out.println("ETSY TOKEN after "+ etsyToken);

            EtsyItem etsyItem = simplistItem.getEtsyItem();
            EtsyController etsy = new EtsyController();
            EtsyItem etsyNewItem = etsy.addItem(etsyItem,x); // now in DB & API
            facade.etsyAddItem(etsyNewItem);

            EbayItem ebayItem = simplistItem.getEbayItem();
            ebayItem.createSimpleList(ebayToken.getAccessToken());
            facade.insertEbayItem(ebayItem);

            facade.simplistCreateItem(new SimplistItem(shopifyNewItem,etsyNewItem, ebayItem, currentUser));


        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        }

        //item to delete 106


    @RequestMapping(value = "/delete" , method = RequestMethod.DELETE)
    public void delete(@RequestParam("itemID")String id,@CookieValue("shopifyToken") String s,
                       @CookieValue("etsyToken") String e) throws IOException {

        System.out.println(id + " is the id that will be passed ");
        SimplistItem item = facade.getSimplestItemById(Integer.parseInt(id));

        EtsyToken etsyToken = new ObjectMapper().readValue(e, EtsyToken.class); // not token
        OAuth1AccessToken x = new OAuth1AccessToken(etsyToken.getAccessToken() , etsyToken.getClientSecret());


        System.out.println(item.getId());
        String shopifyID = item.getShopifyItem().getShopifyId();
        String etsyID = item.getEtsyItem().getListing_id();
        System.out.println("DELETE SHOPIFY ITEM:" +shopifyID);
        EtsyController etsy = new EtsyController();
        ShopifyCRUD shopify = new ShopifyCRUD();
        try {
            ShopifyToken shopifyToken = new ObjectMapper().readValue(s, ShopifyToken.class);
            System.out.println(shopifyToken.getAccessToken());

            shopify.deleteItem("{\"id\":\""+shopifyID+"\"}",shopifyToken);
            etsy.deleteItem(etsyID,x);

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        System.out.println("ID IS " + id);
        facade.simpliestDeleteItem(Integer.parseInt(id));
    }


    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    public void update(@RequestBody SimplistItem simplistItem,@CookieValue("shopifyToken") String s,
                       @CookieValue("etsyToken") String e) {
        try {
            System.out.println(simplistItem.toString());
            int id = simplistItem.getId();//got the id
            SimplistItem sItem = facade.getSimplestItemById(id); //load the simpest item

            ShopifyToken shopifyToken = new ObjectMapper().readValue(s, ShopifyToken.class);
            System.out.println(shopifyToken.getAccessToken());

            EtsyToken etsyToken = new ObjectMapper().readValue(e, EtsyToken.class); // not token
            OAuth1AccessToken x = new OAuth1AccessToken(etsyToken.getAccessToken() , etsyToken.getClientSecret());


            ShopifyItem shopifyitem = sItem.getShopifyItem();
            shopifyitem = simplistItem.getShopifyItem();
            shopifyitem.setShopifyId(sItem.getShopifyItem().getShopifyId());
            sItem.setShopifyItem(shopifyitem);
            ShopifyCRUD shopify = new ShopifyCRUD();
            shopify.updateItem(shopifyitem,shopifyToken); // now API & DB
            facade.shopifyUpdateItem(shopifyitem,shopifyitem.getShopifyId());

            EtsyItem etsyItem = sItem.getEtsyItem();
            etsyItem = simplistItem.getEtsyItem();
            etsyItem.setListing_id(sItem.getEtsyItem().getListing_id());
            sItem.setEtsyItem(etsyItem);
            EtsyController eController = new EtsyController();
            eController.update(etsyItem,sItem.getEtsyItem().getListing_id(),x); // now API & DB
            facade.etsyUpdateItem(etsyItem,etsyItem.getListing_id());

            facade.updateSimplistItem(id,sItem.getEtsyItem(),sItem.getShopifyItem(),x);


            //facade.updateSimplistItem(id,etsyItem,shopifyitem);

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    @RequestMapping(value = "/getMyItems", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody String getMyItems(@CookieValue("user") String u,
                              @CookieValue("shopifyToken") String s,
                              @CookieValue("etsyToken") String e,
                              @CookieValue("eBayToken") String eBayToken) throws IOException, JSONException {

        //@CookieValue(value="user") User user
        //s System.out.println("USER COOKIE - > " + u);

        //OAuth1Converter converter = new ObjectMapper().readValue(x, OAuth1Converter.class);

        System.out.println("IN GET");



        User user = new ObjectMapper().readValue(u,User.class);

        EtsyToken etsyToken = new ObjectMapper().readValue(e, EtsyToken.class); // not token
        ShopifyToken shopifyToken = new ObjectMapper().readValue(s, ShopifyToken.class);
        EbayToken ebayToken = new ObjectMapper().readValue(eBayToken, EbayToken.class);


        List<SimplistItem> list = facade.getItemsByUserID(user);
//        System.out.println("LIST " + list.get(0).toString());
        for (SimplistItem item:list
             ) {
            System.out.println(item);
        }

        JSONObject obj = new JSONObject();
        JSONObject item = new JSONObject();

        for (int i = 0; i < list.size(); i++) {
            obj.put("id",list.get(i).getId());
            obj.put("title",list.get(i).getShopifyItem().getTitle());
            obj.put("description",list.get(i).getShopifyItem().getBody_html());
            obj.put("company",list.get(i).getShopifyItem().getVendor());
            obj.put("type",list.get(i).getShopifyItem().getProduct_type());
            obj.put("price",list.get(i).getEtsyItem().getPrice());
            item.put(String.valueOf(i),obj);
            obj = new JSONObject();
        }
//
//        JSONObject simplistItem1 = new JSONObject();
//        JSONObject simplistItem2 = new JSONObject();
//        for(int i=0;i<list.size();i++) {
//            JSONObject shopifyAtt = new JSONObject();
//            shopifyAtt.put("id", list.get(i).getShopifyItem().getShopifyId());
//            shopifyAtt.put("title", list.get(i).getShopifyItem().getTitle());
//            shopifyAtt.put("body_html", list.get(i).getShopifyItem().getBody_html());
//            shopifyAtt.put("vendor", list.get(i).getShopifyItem().getVendor());
//            shopifyAtt.put("product_type", list.get(i).getShopifyItem().getProduct_type());
//            shopifyAtt.put("tags", list.get(i).getShopifyItem().getTags());
//            shopifyAtt.put("variants", list.get(i).getShopifyItem().getVariants());
//            JSONObject shopify = new JSONObject();
//            shopify.put("shopifyItem", shopifyAtt);
//
//
//            JSONObject obj = new JSONObject();
//            obj.put("Title", list.get(i).getEtsyItem().getTitle());
//            obj.put("Description", list.get(i).getEtsyItem().getDescription());
//            obj.put("Price", list.get(i).getEtsyItem().getPrice());
//            obj.put("Quantity", list.get(i).getEtsyItem().getQuantity());
//            obj.put("Listing_Id", list.get(i).getEtsyItem().getListing_id());
//            obj.put("Listing_Id", list.get(i).getEtsyItem().getShippingTemplate());
//            JSONObject etsyItem = new JSONObject();
//            etsyItem.put("Etsy", obj);
//
//            simplistItem1.put("shopify",shopifyAtt);
//            simplistItem1.put("etsy", obj);
//
//            simplistItem2.put("item"+i,simplistItem1);
//        }

        return item.toString();

    }

}
