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
public class UserService {
    private Facade facade;

    public void setFacade(Facade facade) {
        this.facade = facade;
    }

    public boolean registerUser(User user) {
        User existing = facade.getUserByEmail(user.getEmail());
        if(existing==null){
            user.setPassword(Authentication.hash(user.getPassword()));
            facade.insertUser(user);
            return true;
        }
        return false;

    }

    public User loginUser(String username, String password) {
        User existing = facade.getUserByEmail(username);
        System.out.println(existing);
        if(existing == null) return null;
        return  Authentication.validatePassword(password,existing.getPassword())?
                existing: null;
    }

    public void updateUser(User user) {
        facade.updateUser(user);
    }
}
