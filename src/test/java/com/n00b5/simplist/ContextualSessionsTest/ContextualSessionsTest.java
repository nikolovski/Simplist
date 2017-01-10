package com.n00b5.simplist.ContextualSessionsTest;

import com.n00b5.simplist.beans.User;
import com.n00b5.simplist.data.EtsyDAO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Shehar on 1/7/2017.
 */
public class ContextualSessionsTest {

    private static ApplicationContext contxt;
    @BeforeClass
    public static void setup(){
        contxt = new ClassPathXmlApplicationContext("beans.xml");
    }


    @Test
    public void create(){
        contxt.getBean(EtsyDAO.class).insert(new User("Shehar","Yar","syar0052@gmail.com","hmmmmm"));
        System.out.println("Success!");
    }

}
