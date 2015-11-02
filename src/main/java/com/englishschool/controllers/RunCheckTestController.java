package com.englishschool.controllers;

import com.englishschool.entity.PassedTest;
import com.englishschool.entity.Question;
import com.englishschool.entity.Test;
import com.englishschool.entity.TestProfile;
import com.englishschool.entity.spring.PassedTestModelAttribute;
import com.englishschool.service.passedtest.IPassedTestService;
import com.englishschool.service.profile.IProfileService;
import com.englishschool.service.question.IQuestionService;
import com.englishschool.service.test.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.englishschool.datamodel.CommonConstants.*;
import static com.englishschool.datamodel.CommonURLs.*;
import static com.englishschool.service.helper.ServiceUtils.*;

/**
 * Created by Vadym_Vlasenko on 11/2/2015.
 */
@Controller
public class RunCheckTestController {

    @Autowired
    private IProfileService profileService;
    @Autowired
    private IPassedTestService passedTestService;
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private ITestService testService;

    @RequestMapping(value = TEST_CHECK_URL, method = RequestMethod.POST)
    public String checkTest(@ModelAttribute(PASSED_TEST_MODEL) PassedTestModelAttribute passedModel, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String profileID = (String) session.getAttribute(PROFILE_ID);
        PassedTest passedTestFromModel = passedTestService.getAndCheckPassedTestFromModel(passedModel, session);
        passedTestService.save(passedTestFromModel);
        profileService.addPassedTestToProfile(profileID, passedTestFromModel.getId());
        removeAllCookies(request, response);
        invalidateTestInfoFromSession(session);
        return REDIRECT_RESULT_TEST_URL + passedTestFromModel.getId();
    }

    @RequestMapping(value = RUN_TEST_ID_URL, method = RequestMethod.GET)
    public ModelAndView runTestById(@PathVariable(ID) String testID, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String profileID = (String) session.getAttribute(PROFILE_ID);
        Test currentTest = (Test) session.getAttribute(CURRENT_TEST);
        ModelAndView modelAndView = getTestFromSession(session, currentTest, testID);
        if (modelAndView == null) {
            modelAndView = getTestFromDB(session, profileID, testID);
        }
        return modelAndView;
    }

    @RequestMapping(value = RESULT_TEST_ID_URL, method = RequestMethod.GET)
    public ModelAndView showHistoryPassedTest(@PathVariable(ID) String passedTestID, HttpServletRequest request) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String profileID = (String) session.getAttribute(PROFILE_ID);
        if (!profileService.isPassedTestExist(profileID, passedTestID)) {
            return new ModelAndView(ERROR_PAGE, "message", "Bad ID for passed test");
        }
        PassedTest passedTest = passedTestService.findById(passedTestID);
        return new ModelAndView(RESULT_PAGE, PASSED_TEST, passedTest);
    }

    private Map<String, Object> getModelWithQuestions(List<Question> questions, Test currentTest, PassedTest passedTest) {
        Map<String, Object> model = new HashMap<>();
        model.put(QUESTIONS, questions);
        model.put(TIMER, getRemainingTime(passedTest, currentTest));
        model.put(PASSED_TEST_MODEL, passedTestService.getPassedTestModelAttribute(currentTest));
        return model;
    }

    private ModelAndView getTestFromDB(HttpSession session, String profileID, String testID) {
        String resultPage = ERROR_PAGE;
        Map<String, Object> model = new HashMap<>();
        TestProfile profile = profileService.findById(profileID);
        if (profile != null) {
            List<String> availableTests = profile.getAvailableTests();
            List<String> passedTests = profile.getPassedTests();
            if (availableTests != null && availableTests.contains(testID)) {
                Test currentTest = testService.findById(testID);
                PassedTest passedTest = passedTestService.getPassedTestFromTest(currentTest, profileID);
                List<Question> questions = questionService.findQuestionsByListId(currentTest.getQuestionIds());
                session.setAttribute(QUESTIONS, questions);
                session.setAttribute(CURRENT_TEST, currentTest);
                session.setAttribute(PASSED_TEST, passedTest);
                model = getModelWithQuestions(questions, currentTest, passedTest);
                resultPage = TEST_PAGE;
            } else if (passedTests != null && passedTests.contains(testID)) {
                resultPage = PASSED_TEST_PAGE;
            } else {
                resultPage = ERROR_PAGE;
            }
        }
        return new ModelAndView(resultPage, model);
    }

    private ModelAndView getTestFromSession(HttpSession session, Test currentTest, String testID) {
        if (currentTest != null) {
            if (currentTest.getId().equals(testID)) {
                PassedTest passedTest = (PassedTest) session.getAttribute(PASSED_TEST);
                List<Question> questions = (List<Question>) session.getAttribute(QUESTIONS);
                return new ModelAndView(TEST_PAGE, getModelWithQuestions(questions, currentTest, passedTest));
            } else {
                Map<String, Object> model = new HashMap<>();
                model.put(TEST_ID, currentTest.getId());
                return new ModelAndView(CURRENT_TEST_PAGE, model);
            }
        }
        return null;
    }

}
