package com.englishschool.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.englishschool.datamodel.CommonConstants.PROFILE_ID;
import static com.englishschool.datamodel.CommonURLs.REDIRECT_TEST_CREATE_URL;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
@Controller
public class LoginController {

    public static final String PATH = "/";

    @RequestMapping(value = PATH, method = RequestMethod.GET)
    public String startApplication(HttpSession session) {
        session.setAttribute(PROFILE_ID, "11111");
        return REDIRECT_TEST_CREATE_URL;
    }

    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public String login() {
        return "denied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("form");

        return model;

    }

}
