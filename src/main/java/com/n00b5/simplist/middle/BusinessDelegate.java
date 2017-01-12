package com.n00b5.simplist.middle;

import com.n00b5.simplist.api.ebay.EbayToken;
import com.n00b5.simplist.api.etsy.EtsyToken;
import com.n00b5.simplist.beans.User;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/10/17
 */
public class BusinessDelegate {
    private UserService userService;
    private TokenService tokenService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public boolean registerUser(User user) {
        return userService.registerUser(user);
    }

    public User loginUser(String username, String password){
        return userService.loginUser(username,password);
    }

    public void updateUser(User user) {
        userService.updateUser(user);
    }
    public void insertEbayToken(EbayToken token){
        tokenService.insertEbayToken(token);
    }

    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public TokenService getTokenService() {
        return tokenService;
    }

    public void insertEtsyToken(EtsyToken tokenObj) {
        tokenService.insertEtsyToken(tokenObj);
    }
}
