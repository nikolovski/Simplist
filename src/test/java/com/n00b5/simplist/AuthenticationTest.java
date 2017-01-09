package com.n00b5.simplist;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/9/17
 */
public class AuthenticationTest extends Authentication {
    String hashed, password;

    @Test
    public void testHash() throws Exception {
        password = "test123";
        hashed = Authentication.hash(password);
        System.out.println(hashed);
    }

    @Test
    public void testValidatePassword() throws Exception {
        password = "test123";
        hashed = Authentication.hash(password);
        System.out.println(hashed);
        assertTrue(Authentication.validatePassword(password, hashed));
        password = "asdmfnas";
        assertFalse(Authentication.validatePassword(password, hashed));
    }

}