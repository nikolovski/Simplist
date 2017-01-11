package com.n00b5.simplist.web;

import com.n00b5.simplist.beans.User;
import com.n00b5.simplist.middle.BusinessDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/login", method = RequestMethod.POST,
    consumes = "application/json")
    @ResponseBody
    public ResponseEntity register(@RequestBody @Valid User user) {
        if(businessDelegate.registerUser(user))
            return new ResponseEntity("Registration successfull!",
                    HttpStatus.CREATED);
        else return new ResponseEntity("Email already exists! Please login.",
                HttpStatus.CONFLICT);
    }
    @RequestMapping(value = "/dashboard", method = RequestMethod.POST)
    public String login(@RequestParam String email,
                        @RequestParam String password) {
        System.out.println(email);
        System.out.println(password);
        User user = businessDelegate.loginUser(email,password);
        System.out.println(user);
        if(user!=null)
            return ("dashboard");
        return ("index");
    }
    @RequestMapping(value = "/{other}")
    @ResponseBody
    public Message notFound(@PathVariable String other){
        return new Message("failure","Page Not Found!");
    }

}
