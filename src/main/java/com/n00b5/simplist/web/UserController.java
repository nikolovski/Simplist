package com.n00b5.simplist.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.n00b5.simplist.api.ebay.EbayToken;
import com.n00b5.simplist.api.ebay.eBayAPI;
import com.n00b5.simplist.api.etsy.EtsyToken;
import com.n00b5.simplist.api.etsy.OAuth1Converter;
import com.n00b5.simplist.beans.User;
import com.n00b5.simplist.middle.BusinessDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;


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
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public ResponseEntity<User> validate(@RequestParam String email,
                        @RequestParam String password, HttpServletResponse response) throws JsonProcessingException {

        User user = businessDelegate.loginUser(email,password);
        System.out.println(user);
        if(user!=null){
            user.setPassword(null);
            response.addCookie(new Cookie("user",new ObjectMapper().writeValueAsString(user)));
            return new ResponseEntity<User>(user,HttpStatus.OK);
        }
        else return new ResponseEntity("Invalid username/password combination",HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{other}")
    @ResponseBody
    public Message notFound(@PathVariable String other){
        return new Message("failure","Page Not Found!");
    }

    /**
     * Request mapping for the callback url of the eBay API
     * @param state
     * @param code
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/ebayToken", params = {"state", "code"})
    @ResponseBody
    public String getToken(String state, String code, HttpServletResponse response) throws IOException {
        EbayToken token = new eBayAPI().getToken(code);
        response.addCookie(new Cookie("eBayToken", new ObjectMapper().writeValueAsString(token)));
        return "You can now close this window.";
    }
    @RequestMapping(value = "/saveEbayToken", method = RequestMethod.POST)
    public void saveEbayToken(@RequestParam String token,
                                         @RequestParam String user) throws IOException {
        User userObj = new ObjectMapper().readValue(user,User.class);
        EbayToken tokenObj = new ObjectMapper().readValue(token,EbayToken.class);
        System.out.println(tokenObj);
        userObj.seteBayRefreshToken(tokenObj.getRefreshToken());
        System.out.println(userObj);
        businessDelegate.updateUser(userObj);
    }
    @RequestMapping(value = "/saveEtsyToken", method = RequestMethod.POST)
    public ResponseEntity saveEtsyToken(@RequestParam String token,
                                         @RequestParam String user) throws IOException {
        User userObj = new ObjectMapper().readValue(user,User.class);
        EtsyToken tokenObj = new ObjectMapper().readValue(token,EtsyToken.class);
        System.out.println(userObj);
        System.out.println(tokenObj);
        businessDelegate.insertEtsyToken(tokenObj);
        businessDelegate.updateUser(userObj);
        if(user!=null){
            return new ResponseEntity(user,HttpStatus.OK);
        }
        else return new ResponseEntity("Invalid username/password combination",HttpStatus.BAD_REQUEST);
    }
}
