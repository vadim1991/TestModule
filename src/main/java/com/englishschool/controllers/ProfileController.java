package com.englishschool.controllers;

import com.englishschool.entity.Group;
import com.englishschool.entity.TestProfile;
import com.englishschool.entity.datatable.DataTableBean;
import com.englishschool.entity.datatable.ProfileDataTableBean;
import com.englishschool.entity.spring.AssignTestBean;
import com.englishschool.service.email.EmailSenderService;
import com.englishschool.service.group.IGroupService;
import com.englishschool.service.json.JsonService;
import com.englishschool.service.profile.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.englishschool.datamodel.CommonConstants.ID;
import static com.englishschool.datamodel.CommonConstants.MSG_ATTRIBUTE;
import static com.englishschool.datamodel.CommonMessages.SUCCESS_CREATE_PROFILE;
import static com.englishschool.service.helper.ServiceUtils.completeDataTableBeanFromRequest;
import static com.englishschool.service.helper.ServiceUtils.generateStringKey;

/**
 * Created by Vadym_Vlasenko on 10/19/2015.
 */
@Controller
public class ProfileController {

    public static final String PROFILE_PAGES_URL = "/profile/pages";
    public static final String AGE = "age";
    public static final String EMAIL = "email";
    public static final String SURNAME = "surname";
    public static final String NAME = "name";
    public static final String ASSIGN_TEST = "assignTest";
    public static final String GROUPS = "groups";

    @Autowired
    private IProfileService profileService;
    @Autowired
    private JsonService jsonService;
    @Autowired
    private IGroupService<Group> groupService;
    @Autowired
    private EmailSenderService emailSenderService;

    @RequestMapping(value = "/profile/create", method = RequestMethod.GET)
    public ModelAndView createProfile() {
        List<Group> groups = groupService.findAll();
        Map<String, Object> model = new HashMap<>();
        TestProfile profile = new TestProfile();
        model.put("profile", profile);
        model.put("groups", groups);
        return new ModelAndView("create-profile", model);
    }

    @RequestMapping(value = "/profile/create", method = RequestMethod.POST)
    public String createProfile(@ModelAttribute("profile") TestProfile profile, final RedirectAttributesModelMap redirectAttributesModelMap) throws MessagingException {
        profile.setPassword(generateStringKey());
        System.out.println(profile);
        profileService.save(profile);
        emailSenderService.sendMimeEmail(profile);
        redirectAttributesModelMap.addFlashAttribute(MSG_ATTRIBUTE, SUCCESS_CREATE_PROFILE);
        return "redirect:/profile/create";
    }

    @RequestMapping(value = PROFILE_PAGES_URL, method = RequestMethod.GET)
    public void getProfilesByPages(DataTableBean dataTableBean, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] questionFields = {ID, NAME, SURNAME, EMAIL, AGE};
        completeDataTableBeanFromRequest(request, questionFields, dataTableBean);
        Page<TestProfile> questionsPagination = profileService.findAllWithPagination(dataTableBean, questionFields);
        List<TestProfile> questions = questionsPagination.getContent();
        List<ProfileDataTableBean> dataModelQuestions = profileService.convertProfileForDataTableBean(questions);
        String questionsDataJson = jsonService.dataToJson(dataModelQuestions, dataTableBean, (int) questionsPagination.getTotalElements());
        System.out.println(questionsDataJson);
        response.getWriter().write(questionsDataJson);
    }

    @RequestMapping(value = "/assign/tests", method = RequestMethod.GET)
    public ModelAndView assignTests() {
        List<Group> groups = groupService.findAll();
        Map<String, Object> model = new HashMap<>();
        AssignTestBean assignTestBean = new AssignTestBean();
        model.put(ASSIGN_TEST, assignTestBean);
        model.put(GROUPS, groups);
        return new ModelAndView("assign-test", model);
    }

    @RequestMapping(value = "/assign/tests", method = RequestMethod.POST)
    public String assignTests(@ModelAttribute(ASSIGN_TEST) AssignTestBean assignTestBean) {
        System.out.println(assignTestBean);
        Set<TestProfile> profilesFromAssignBean = profileService.getProfilesFromAssignBean(assignTestBean);
        profileService.assignTestToProfiles(profilesFromAssignBean, assignTestBean.getTestIDs());
        return "redirect:/assign/tests";
    }

}
