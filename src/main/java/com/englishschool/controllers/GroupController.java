package com.englishschool.controllers;

import com.englishschool.datamodel.CommonConstants;
import com.englishschool.datamodel.CommonMessages;
import com.englishschool.entity.Group;
import com.englishschool.service.group.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import static com.englishschool.datamodel.CommonConstants.*;
import static com.englishschool.datamodel.CommonMessages.*;

/**
 * Created by Vadym_Vlasenko on 10/19/2015.
 */
@Controller
public class GroupController {

    public static final String REDIRECT_PROFILE_CREATE_URL = "redirect:/profile/create";
    public static final String GROUP_CREATE_URL = "/group/create";
    @Autowired
    private IGroupService<Group> groupService;

    @RequestMapping(value = GROUP_CREATE_URL, method = RequestMethod.POST)
    public String createGroup(Group group, final RedirectAttributesModelMap attributesModelMap) {
        groupService.save(group);
        attributesModelMap.addFlashAttribute(MSG_ATTRIBUTE, SUCCESS_CREATE_GROUP);
        return REDIRECT_PROFILE_CREATE_URL;
    }

}
