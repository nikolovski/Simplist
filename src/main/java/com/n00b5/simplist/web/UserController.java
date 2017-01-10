package com.n00b5.simplist.web;

import com.n00b5.simplist.middle.BusinessDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String register(@RequestParam String first_name,
                           @RequestParam String last_name,
                           @RequestParam String new_email,
                           @RequestParam String new_password) {
        businessDelegate.registerUser(first_name, last_name, new_email, new_password);
        return "redirect:/login";
    }
}
