package com.englishschool.controllers;

import com.englishschool.entity.PassedTest;
import com.englishschool.entity.Test;
import com.englishschool.entity.datatable.DataTableBean;
import com.englishschool.entity.datatable.TestForDataTableBean;
import com.englishschool.service.json.JsonServiceImpl;
import com.englishschool.service.passedtest.IPassedTestService;
import com.englishschool.service.profile.IProfileService;
import com.englishschool.service.question.IQuestionService;
import com.englishschool.service.test.ITestService;
import com.englishschool.validator.TestValidator;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import static com.englishschool.datamodel.CommonConstants.*;
import static com.englishschool.datamodel.CommonURLs.*;
import static com.englishschool.service.helper.ServiceUtils.*;

/**
 * Created by Administrator on 10/1/2015.
 */
@Controller
public class TestController {

    @Autowired
    private ITestService testService;
    @Autowired
    private IProfileService profileService;
    @Autowired
    private IPassedTestService passedTestService;
    @Autowired
    private JsonServiceImpl jsonService;
    @Autowired
    private TestValidator testValidator;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "test/{id}/update", method = RequestMethod.GET)
    public ModelAndView updateTest(@PathVariable(ID) String testID, Locale locale) {
        Test test = testService.findById(testID);
        Map<String, Object> model = new HashMap<>();
        if (test != null) {
            model.put(TEST, test);
            model.put("subject", getMessageFromBundle("test.update", locale, messageSource));
            model.put("button", getMessageFromBundle("test.update.button", locale, messageSource));
        }
        return new ModelAndView(CREATE_TEST_PAGE, model);
    }

    @RequestMapping(value = "test/{id}/delete", method = RequestMethod.POST)
    public void deleteTest(@PathVariable(ID) String testID, HttpServletResponse response) throws IOException {
        String resultMessage = "error";
        boolean deleteResult = testService.deleteByID(testID);
        if (deleteResult) {
            resultMessage = SUCCESS;
        }
        response.getWriter().write(resultMessage);
    }

    @RequestMapping(value = "admin/delete/tests", method = RequestMethod.POST)
    public void deleteTests(@RequestBody List<String> testIDs, HttpServletResponse response) throws IOException {
        System.out.println(testIDs);
        testService.deleteByIDs(testIDs);
        response.getWriter().write(SUCCESS);
    }

    @RequestMapping(value = TEST_PAGES_URL, method = RequestMethod.GET)
    public void getTestByPages(DataTableBean dataTableBean, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] testFields = {ID, TEST_TITLE, CREATION_DATE, QUESTION_AMOUNT, TIME_OF_TEST};
        completeDataTableBeanFromRequest(request, testFields, dataTableBean);
        Page<Test> testPagination = testService.findAllWithPagination(dataTableBean, testFields);
        List<Test> tests = testPagination.getContent();
        List<TestForDataTableBean> dataModelTests = testService.convertTestsForDataTableBean(tests);
        String testDataJson = jsonService.dataToJson(dataModelTests, dataTableBean, (int) testPagination.getTotalElements());
        response.getWriter().write(testDataJson);
    }

    @RequestMapping(value = TEST_CREATE_URL, method = RequestMethod.GET)
    public ModelAndView createTest(Locale locale) {
        Map<String, Object> model = new HashMap<>();
        Test test = new Test();
        model.put(TEST, test);
        model.put("subject", getMessageFromBundle("test.create", locale, messageSource));
        model.put("button", getMessageFromBundle("test.create.button", locale, messageSource));
        return new ModelAndView(CREATE_TEST_PAGE, model);
    }

    @RequestMapping(value = TEST_CREATE_URL, method = RequestMethod.POST)
    public String createTest(@ModelAttribute(TEST) Test test, final RedirectAttributes redirectAttributes, BindingResult result) {
        String createTime = convertDateToString(new DateTime());
        test.setCreationDate(createTime);
        testValidator.validate(test, result);
        if (result.hasErrors()) {
            return CREATE_TEST_PAGE;
        }
        testService.save(test);
        redirectAttributes.addFlashAttribute(MSG_ATTRIBUTE, getMessageFromBundle("test.create.message", null, messageSource));
        if (StringUtils.isNotBlank(test.getId())) {
            redirectAttributes.addFlashAttribute(MSG_ATTRIBUTE, getMessageFromBundle("test.update.message", null, messageSource));
        }
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
