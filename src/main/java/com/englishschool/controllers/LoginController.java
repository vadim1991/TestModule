package com.englishschool.controllers;

import com.englishschool.datamodel.CommonURLs;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

import static com.englishschool.datamodel.CommonConstants.PROFILE_ID;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
@Controller
public class LoginController {

    public static final String PATH = "/";

    @RequestMapping(value = PATH, method = RequestMethod.GET)
    public String startApplication(HttpSession session) {
        session.setAttribute(PROFILE_ID, "11111");
        return CommonURLs.REDIRECT_CREATE_QUESTION_URL;
    }

}
