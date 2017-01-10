package com.n00b5.simplist.middle;

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

    public void registerUser(String first_name, String last_name, String new_email, String new_password) {
        userService.registerUser(first_name, last_name, new_email, new_password);
    }
}
