package com.n00b5.simplist.web;

import com.n00b5.simplist.beans.User;
import com.n00b5.simplist.middle.BusinessDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/8/17
 */
@Controller
public class UserController {
    private BusinessDelegate businessDelegate;

    public void setBusinessDelegate(BusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "index";
    }
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST,
    consumes = "application/json")
    public Message register(@RequestBody @Valid User user) {
        if(businessDelegate.registerUser(user))
            return new Message("success", "Registration successful!");
        else return new Message("failure", "Already existing user!");
    }
}
