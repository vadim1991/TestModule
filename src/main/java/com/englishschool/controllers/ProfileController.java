package com.englishschool.controllers;

import com.englishschool.datamodel.CommonURLs;
import com.englishschool.entity.Group;
import com.englishschool.entity.TestProfile;
import com.englishschool.entity.datatable.DataTableBean;
import com.englishschool.entity.datatable.ProfileDataTableBean;
import com.englishschool.entity.spring.AssignTestBean;
import com.englishschool.service.email.EmailSenderService;
import com.englishschool.service.group.IGroupService;
import com.englishschool.service.json.JsonService;
import com.englishschool.service.passedtest.IPassedTestService;
import com.englishschool.service.profile.IProfileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.englishschool.datamodel.CommonConstants.*;
import static com.englishschool.datamodel.CommonMessages.SUCCESS_CREATE_PROFILE;
import static com.englishschool.datamodel.CommonURLs.CREATE_TEST_PAGE;
import static com.englishschool.service.helper.ServiceUtils.completeDataTableBeanFromRequest;
import static com.englishschool.service.helper.ServiceUtils.generateStringKey;
import static com.englishschool.service.helper.ServiceUtils.getMessageFromBundle;

/**
 * Created by Vadym_Vlasenko on 10/19/2015.
 */
@Controller
public class ProfileController {

    public static final String AVERAGE_MARK = "averageMark";
    public static final String SUBJECT = "subject";
    public static final String BUTTON = "button";
    @Autowired
    private IProfileService profileService;
    @Autowired
    private JsonService jsonService;
    @Autowired
    private IGroupService<Group> groupService;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private IPassedTestService passedTestService;

    @RequestMapping(value = "/profile/create", method = RequestMethod.GET)
    public ModelAndView createProfile(Locale locale) {
        List<Group> groups = groupService.findAll();
        Map<String, Object> model = new HashMap<>();
        TestProfile profile = new TestProfile();
        model.put(PROFILE, profile);
        model.put(GROUPS, groups);
        model.put(BUTTON, getMessageFromBundle("profile.create.button", locale, messageSource));
        model.put(SUBJECT, getMessageFromBundle("profile.create", locale, messageSource));
        model.put("url", "/profile/create");
        return new ModelAndView("create-profile", model);
    }

    @RequestMapping(value = "/profile/create", method = RequestMethod.POST)
    public String createProfile(@ModelAttribute("profile") TestProfile profile, final RedirectAttributesModelMap redirectAttributes, Locale locale) throws MessagingException {
        profile.setPassword(generateStringKey());
        profileService.save(profile);
        emailSenderService.sendMimeEmail(profile);
        redirectAttributes.addFlashAttribute(MSG_ATTRIBUTE, getMessageFromBundle("profile.create.message", locale, messageSource));
        if (StringUtils.isNotBlank(profile.getId())) {
            redirectAttributes.addFlashAttribute(MSG_ATTRIBUTE, getMessageFromBundle("profile.update.message", locale, messageSource));
        }
        return "redirect:/profile/create";
    }

    @RequestMapping(value = "/admin/profile/{id}/update", method = RequestMethod.GET)
    public ModelAndView updateProfile(@PathVariable("id") String id, Locale locale) {
        TestProfile profile = profileService.findById(id);
        Map<String, Object> model = new HashMap<>();
        if (profile != null) {
            List<Group> groups = groupService.findAll();
            model.put(PROFILE, profile);
            model.put(GROUPS, groups);
            model.put(SUBJECT, getMessageFromBundle("profile.update", locale, messageSource));
            model.put(BUTTON, getMessageFromBundle("profile.update.button", locale, messageSource));
            model.put("url", "/admin/profile/update");
        }
        return new ModelAndView("create-profile", model);
    }

    @RequestMapping(value = "/admin/profile/update", method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute(PROFILE) TestProfile profile, final RedirectAttributesModelMap redirectAttributes, Locale locale) {
        TestProfile oldProfile = profileService.findById(profile.getId());
        profile.setPassedTests(oldProfile.getPassedTests());
        profile.setRoles(oldProfile.getRoles());
        profile.setPassword(profile.getPassword());
        profile.setAverageMark(profile.getAverageMark());
        System.out.println(profile);
        profileService.save(profile);
        redirectAttributes.addFlashAttribute(MSG_ATTRIBUTE, getMessageFromBundle("profile.update.message", locale, messageSource));
        return "redirect:/assign/tests";
    }

    @RequestMapping(value = CommonURLs.PROFILE_PAGES_URL, method = RequestMethod.GET)
    public void getProfilesByPages(DataTableBean dataTableBean, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] questionFields = {ID, NAME, SURNAME, EMAIL, AVERAGE_MARK};
        completeDataTableBeanFromRequest(request, questionFields, dataTableBean);
        Page<TestProfile> questionsPagination = profileService.findAllWithPagination(dataTableBean, questionFields);
        List<TestProfile> questions = questionsPagination.getContent();
        List<ProfileDataTableBean> dataModelQuestions = profileService.convertProfileForDataTableBean(questions);
        String questionsDataJson = jsonService.dataToJson(dataModelQuestions, dataTableBean, (int) questionsPagination.getTotalElements());
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

    @RequestMapping(value = "admin/delete/profiles", method = RequestMethod.POST)
    public void deleteTests(@RequestBody List<String> profileIDs, HttpServletResponse response) throws IOException {
        System.out.println(profileIDs);
        List<TestProfile> testProfiles = profileService.deleteByIDs(profileIDs);
        passedTestService.deletePassedTestsFromProfiles(testProfiles);
        response.getWriter().write(SUCCESS);
    }

}
