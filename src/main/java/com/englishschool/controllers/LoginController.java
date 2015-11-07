package com.englishschool.controllers;

import com.englishschool.entity.Role;
import com.englishschool.entity.TestProfile;
import com.englishschool.service.helper.ServiceUtils;
import com.englishschool.service.profile.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

import static com.englishschool.datamodel.CommonConstants.PROFILE_ID;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
@Controller
public class LoginController {

    @Autowired
    private IProfileService profileService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public String startApplication(HttpSession session, Principal principal) {
        System.out.println("authorization");
        TestProfile profile = profileService.findByEmail(principal.getName());
        session.setAttribute(PROFILE_ID, profile.getId());
        String redirectPage = "redirect:/available/tests";
        if (profile.getRoles().contains(Role.ROLE_ADMIN)) {
            redirectPage = "redirect:/assign/tests";
        }
        return redirectPage;
    }

    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public String login() {
        return "denied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", ServiceUtils.getMessageFromBundle("login.error", null, messageSource));
        }
        model.setViewName("form");
        return model;
    }

}
