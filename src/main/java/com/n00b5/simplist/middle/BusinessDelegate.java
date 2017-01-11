package com.n00b5.simplist.middle;

import com.n00b5.simplist.beans.User;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/10/17
 */
public class BusinessDelegate {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public boolean registerUser(User user) {
        return userService.registerUser(user);
    }

    public User loginUser(String username, String password){
        return userService.loginUser(username,password);
    }
}
