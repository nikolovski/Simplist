package com.n00b5.simplist.data;

import com.n00b5.simplist.api.Shopify.ShopifyItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Shehar on 1/10/2017.
 */
public class ShopifyItemDAOTest {
    private static ApplicationContext contxt;

    @Before
    public void setUp() throws Exception {
        contxt = new ClassPathXmlApplicationContext("application-context.xml");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Ignore
    @Test
    public void insertItem() throws Exception {
        contxt.getBean(ShopifyItemDAO.class).addShopifyItem(new ShopifyItem("12345","a","a","a","a","a"));
    }

    @Ignore
    @Test
    public void getById(){
        ShopifyItem item = contxt.getBean(ShopifyItemDAO.class).getById("12345");
//        System.out.println(item.getTitle());
    }

    @Ignore
    @Test
    public void updateItem(){
        ShopifyItem item = new ShopifyItem("12345","b","a","a","a","a");
        contxt.getBean(ShopifyItemDAO.class).shopifyUpdateItem(item,"12345");
    }

    @Test
    public void delete(){
        contxt.getBean(ShopifyItemDAO.class).shopifyDeleteItem("12345");
    }



}
