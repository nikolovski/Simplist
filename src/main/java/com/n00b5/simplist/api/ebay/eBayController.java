package com.n00b5.simplist.api.ebay;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;

/**
 * Created by d4k1d23 on 12/30/16.
 */
@Controller
@RequestMapping("/ebay")
public class eBayController {

    @RequestMapping(value = "/oauth")
    public ModelAndView getCode() throws IOException {
        String url = new eBayAPI().generateURL();
        return new ModelAndView("redirect:" + url);
    }

}
