package com.n00b5.simplist.data;

import com.n00b5.simplist.Authentication;
import com.n00b5.simplist.beans.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/9/17
 */
public class UserDAOTest {
    private static ApplicationContext contxt;
    private int userId;

    @Before
    public void setUp() throws Exception {
        contxt = new ClassPathXmlApplicationContext("application-context.xml");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void insertUser() throws Exception {
        String password = Authentication.hash("123");
        contxt.getBean(UserDAO.class).insert(new User("Louis", "Lopez", "louis@simplist.com", password,null,null,null,null));
    }

    @Test
    public void updateUser() throws Exception {
        User user = contxt.getBean(UserDAO.class).getByEmail("nikolovski@simplist.com");
        user.setLastName("Nikolovskiskiski");
        contxt.getBean(UserDAO.class).update(user);
    }

    @Test
    public void getUserById() throws Exception {
        User user = contxt.getBean(UserDAO.class).getById(35);
        System.out.println(user);
        assertNotNull(user);
    }

    @Test
    public void getUser() throws Exception {
        User user = contxt.getBean(UserDAO.class).getByEmail("nikolovski@simplist.com");
        assertNotNull(user);
        assertTrue(Authentication.validatePassword("test123", user.getPassword()));
        assertFalse(Authentication.validatePassword("test346", user.getPassword()));
    }

    @Test
    public void deleteUserById() throws Exception {
        assertTrue(contxt.getBean(UserDAO.class).deleteById(35));
    }

    @Test
    public void deleteUserByEmail() throws Exception {
        assertTrue(contxt.getBean(UserDAO.class).deleteByEmail("nikolovski@simplist.com"));
    }

}