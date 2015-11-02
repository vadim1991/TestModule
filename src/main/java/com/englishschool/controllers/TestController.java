package com.englishschool.controllers;

import com.englishschool.entity.*;
import com.englishschool.entity.datatable.DataTableBean;
import com.englishschool.entity.datatable.QuestionForDataTableBean;
import com.englishschool.entity.datatable.TestForDataTableBean;
import com.englishschool.entity.spring.PassedTestModelAttribute;
import com.englishschool.service.group.IGroupService;
import com.englishschool.service.json.JsonServiceImpl;
import com.englishschool.service.passedtest.IPassedTestService;
import com.englishschool.service.profile.IProfileService;
import com.englishschool.service.question.IQuestionService;
import com.englishschool.service.test.ITestService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.englishschool.datamodel.CommonConstants.*;
import static com.englishschool.datamodel.CommonMessages.SUCCESS_CREATE_TEST;
import static com.englishschool.datamodel.CommonURLs.*;
import static com.englishschool.service.helper.ServiceUtils.*;

/**
 * Created by Administrator on 10/1/2015.
 */
@Controller
public class TestController {

    public static final String CREATION_DATE = "creationDate";
    public static final String TIME_OF_TEST = "timeOfTest";
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private ITestService testService;
    @Autowired
    private IProfileService profileService;
    @Autowired
    private IPassedTestService passedTestService;
    @Autowired
    private JsonServiceImpl jsonService;

    @RequestMapping(value = QUESTIONS_PAGES_URL, method = RequestMethod.GET)
    public void getQuestionByPages(DataTableBean dataTableBean, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] questionFields = {QUESTION_ID, TITLE, CATEGORY};
        completeDataTableBeanFromRequest(request, questionFields, dataTableBean);
        Page<Question> questionsPagination = questionService.findAllWithPagination(dataTableBean, questionFields);
        List<Question> questions = questionsPagination.getContent();
        List<QuestionForDataTableBean> dataModelQuestions = questionService.convertQuestionsForDataTableBean(questions);
        String questionsDataJson = jsonService.dataToJson(dataModelQuestions, dataTableBean, (int) questionsPagination.getTotalElements());
        response.getWriter().write(questionsDataJson);
    }

    @RequestMapping(value = "test/{id}/update")
    public ModelAndView updateTest(@PathVariable(ID) String testID) {
        Test test = testService.findById(testID);
        Map<String, Object> model = new HashMap<>();
        if (test != null) {
            model.put(TEST, test);
        }
        return new ModelAndView(CREATE_TEST_PAGE, model);
    }

    @RequestMapping(value = TEST_PAGES_URL, method = RequestMethod.GET)
    public void getTestByPages(DataTableBean dataTableBean, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] testFields = {ID, TEST_TITLE, CREATION_DATE, TIME_OF_TEST};
        completeDataTableBeanFromRequest(request, testFields, dataTableBean);
        Page<Test> testPagination = testService.findAllWithPagination(dataTableBean, testFields);
        List<Test> tests = testPagination.getContent();
        List<TestForDataTableBean> dataModelTests = testService.convertTestsForDataTableBean(tests);
        String testDataJson = jsonService.dataToJson(dataModelTests, dataTableBean, (int) testPagination.getTotalElements());
        response.getWriter().write(testDataJson);
    }

    @RequestMapping(value = TEST_CREATE_URL, method = RequestMethod.GET)
    public ModelAndView createTest() {
        Map<String, Object> model = new HashMap<>();
        Test test = new Test();
        model.put(TEST, test);
        return new ModelAndView(CREATE_TEST_PAGE, model);
    }

    @RequestMapping(value = TEST_CREATE_URL, method = RequestMethod.POST)
    public String createTest(@ModelAttribute(TEST) Test test, final RedirectAttributes redirectAttributes) {
        String createTime = convertDateToString(new DateTime());
        test.setCreationDate(createTime);
        testService.save(test);
        redirectAttributes.addFlashAttribute(MSG_ATTRIBUTE, SUCCESS_CREATE_TEST);
        System.out.println(test);
        return REDIRECT_TEST_CREATE_URL;
    }

    @RequestMapping(value = AVAILABLE_TESTS_URL, method = RequestMethod.GET)
    public ModelAndView availableTests(HttpSession session) {
        String profileID = (String) session.getAttribute(PROFILE_ID);
        List<String> availableTestIDs = profileService.getAvailableTests(profileID);
        List<Test> availableTests = testService.getTestByListIDS(availableTestIDs);
        return new ModelAndView(AVAILABLE_TESTS_PAGE, AVAILABLE_TESTS, availableTests);
    }

    @RequestMapping(value = PASSED_TESTS_URL, method = RequestMethod.GET)
    public ModelAndView passedTests(HttpSession session) {
        String profileID = (String) session.getAttribute(PROFILE_ID);
        List<String> passedTestIDs = profileService.getPassedTests(profileID);
        List<PassedTest> passedTests = passedTestService.getPassedTestsByListIDS(passedTestIDs);
        return new ModelAndView(PASSED_TESTS_PAGE, PASSED_TESTS, passedTests);
    }

}
