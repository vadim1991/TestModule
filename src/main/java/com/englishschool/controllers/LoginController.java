package com.englishschool.controllers;

import com.englishschool.entity.Role;
import com.englishschool.entity.TestProfile;
import com.englishschool.service.profile.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

import static com.englishschool.datamodel.CommonConstants.PROFILE_ID;
import static com.englishschool.datamodel.CommonURLs.AVAILABLE_TESTS_URL;
import static com.englishschool.datamodel.CommonURLs.REDIRECT_TEST_CREATE_URL;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
@Controller
public class LoginController {

    public static final String PATH = "/";

    @Autowired
    private IProfileService profileService;

    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public String startApplication(HttpSession session, Principal principal) {
        System.out.println("authorization");
        String redirectPage = REDIRECT_TEST_CREATE_URL;
        TestProfile profile = profileService.findByEmail(principal.getName());
        session.setAttribute(PROFILE_ID, profile.getId());
        if (profile.getRoles().contains(Role.ROLE_USER)) {
            redirectPage = "redirect:/available/tests";
        }
        return redirectPage;
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
