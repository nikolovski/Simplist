package com.n00b5.simplist.middle;

import com.n00b5.simplist.Authentication;
import com.n00b5.simplist.Helper;
import com.n00b5.simplist.beans.User;
import com.n00b5.simplist.data.Facade;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/10/17
 */
public class UserService implements Helper {
    private Facade facade;

    public void setFacade(Facade facade) {
        this.facade = facade;
    }

    public void registerUser(String firstName, String lastName,
                             String email, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(Authentication.hash(password));
        facade.insertUser(user);
    }
}
