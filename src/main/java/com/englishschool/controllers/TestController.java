package com.englishschool.controllers;

import com.englishschool.entity.*;
import com.englishschool.entity.spring.DataTableBean;
import com.englishschool.entity.spring.PassedQuestionModelAttribute;
import com.englishschool.entity.spring.PassedTestModelAttribute;
import com.englishschool.entity.spring.QuestionForDataTableBean;
import com.englishschool.service.category.ICategoryService;
import com.englishschool.service.json.QuestionJsonServiceImpl;
import com.englishschool.service.passedtest.IPassedTestService;
import com.englishschool.service.profile.IProfileService;
import com.englishschool.service.question.IQuestionService;
import com.englishschool.service.test.ITestService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.englishschool.datamodel.CommonConstants.*;
import static com.englishschool.datamodel.CommonMessages.SUCCESS_CREATE_TEST;
import static com.englishschool.datamodel.CommonURLs.*;

/**
 * Created by Administrator on 10/1/2015.
 */
@Controller
public class TestController {

    @Autowired
    private IQuestionService questionService;
    @Autowired
    private ITestService testService;
    @Autowired
    private IProfileService profileService;
    @Autowired
    private IPassedTestService passedTestService;
    @Autowired
    private QuestionJsonServiceImpl questionJsonService;
    @Autowired
    private ICategoryService<Category> categoryService;

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

    @RequestMapping(value = TEST_CHECK_URL, method = RequestMethod.POST)
    public String checkTest(@ModelAttribute(PASSED_TEST_MODEL) PassedTestModelAttribute passedModel, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String profileID = (String) session.getAttribute(PROFILE_ID);
        PassedTest passedTestFromModel = getPassedTestFromModel(passedModel, session);
        passedTestService.save(passedTestFromModel);
        profileService.addPassedTestToProfile(profileID, passedTestFromModel.getId());
        removeAllCookies(request, response);
        invalidateTestInfoFromSession(session);
        return REDIRECT_RESULT_TEST_URL + passedTestFromModel.getId();
    }

    @RequestMapping(value = QUESTIONS_PAGES_URL, method = RequestMethod.GET)
    public void getQuestionByPage(DataTableBean dataTableBean, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] properties = {QUESTION_ID, TITLE, CATEGORY};
        String orderColumn = request.getParameter(ORDER_0_COLUMN);
        String order = null;
        if (!orderColumn.isEmpty()) {
            order = properties[Integer.parseInt(orderColumn)];
        }
        dataTableBean.setOrderColumn(order);
        dataTableBean.setOrderParam(request.getParameter(ORDER_0_DIR));
        dataTableBean.setSearchWord(request.getParameter(SEARCH_VALUE));
        Page allWithPagination = questionService.findAllWithPagination(dataTableBean);
        List<Question> questions = allWithPagination.getContent();
        List<QuestionForDataTableBean> dataModelQuestions = questionService.convertQuestionsForDataTableBean(questions);
        String questionsDataJson = questionJsonService.getQuestionsDataJson(dataModelQuestions, dataTableBean, (int) allWithPagination.getTotalElements());
        System.out.println(questionsDataJson);
        response.getWriter().write(questionsDataJson);
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
        //String profileID = (String) session.getAttribute(PROFILE_ID);
        List<String> availableTestIDs = profileService.getAvailableTests("11111");
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

    @RequestMapping(value = RUN_TEST_ID_URL, method = RequestMethod.GET)
    public ModelAndView runTestById(@PathVariable(ID) String testID, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String profileID = (String) session.getAttribute(PROFILE_ID);
        Test currentTest = (Test) session.getAttribute(CURRENT_TEST);
        ModelAndView modelAndView = checkTestFromSession(session, currentTest, testID);
        if (modelAndView == null) {
            modelAndView = getTestFromDB(session, profileID, testID);
        }
        return modelAndView;
    }

    private void invalidateTestInfoFromSession(HttpSession session) {
        session.setAttribute(CURRENT_TEST, null);
        session.setAttribute(PASSED_TEST, null);
        session.setAttribute(QUESTIONS, null);
    }

    private PassedTest getPassedTestFromModel(PassedTestModelAttribute passedModel, HttpSession session) {
        double countRight = 0;
        List<PassedQuestionModelAttribute> passedModelQuestion = passedModel.getPassedQuestions();
        PassedTest passedTest = (PassedTest) session.getAttribute(PASSED_TEST);
        List<Question> questions = (List<Question>) session.getAttribute(QUESTIONS);
        List<PassedQuestion> passedQuestions = new ArrayList<>();
        for (int i = 0; i < passedModelQuestion.size(); i++) {
            boolean rightAnswer = true;
            Question question = questions.get(i);
            PassedQuestion passedQuestion = new PassedQuestion();
            PassedQuestionModelAttribute modelAttribute = passedModelQuestion.get(i);
            List<Integer> userAnswers = modelAttribute.getUserAnswers();
            List<Answer> answers = question.getAnswers();
            List<UserAnswer> userAnswerList = new ArrayList<>();
            for (int j = 0; j < answers.size(); j++) {
                Answer answer = answers.get(j);
                UserAnswer userAnswer = new UserAnswer();
                userAnswer.setId(answer.getId());
                userAnswer.setAnswerTest(answer.getAnswerText());
                userAnswer.setRightAnswer(answer.isRightAnswer());
                if (userAnswers != null) {
                    userAnswer.setUserAnswer(userAnswers.contains(j));
                }
                rightAnswer &= userAnswer.isRightAnswer() == userAnswer.isUserAnswer() ? true : false;
                userAnswerList.add(userAnswer);
            }
            if (rightAnswer) {
                countRight++;
            }
            passedQuestion.setRightAnswer(rightAnswer);
            passedQuestion.setUserAnswers(userAnswerList);
            passedQuestion.setQuestion(question);
            passedQuestions.add(passedQuestion);
        }
        passedTest.setEndTest(convertDateToString(new DateTime()));
        passedTest.setPassedQuestions(passedQuestions);
        passedTest.setResult(countRight / questions.size() * 100);
        return passedTest;
    }

    private String convertDateToString(DateTime dateTime) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
        return dateTime.toString(fmt);
    }

    private DateTime convertStringToDate(String dateTime) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
        return DateTime.parse(dateTime, fmt);
    }

    private void removeAllCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if (name.contains(CHECKBOX_STATE) || name.contains(CURRENT) || name.contains(ACTIVE)) {
                cookie.setMaxAge(0);
                cookie.setPath(request.getContextPath() + RIGHT_SLASH);
                response.addCookie(cookie);
            }
        }
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
                PassedTest passedTest = getPassedTest(currentTest);
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

    private Map<String, Object> getModelWithQuestions(List<Question> questions, Test currentTest, PassedTest passedTest) {
        Map<String, Object> model = new HashMap<>();
        model.put(QUESTIONS, questions);
        model.put(TIMER, getRemainingTime(passedTest, currentTest));
        model.put(PASSED_TEST_MODEL, getPassedTestModelAttribute(currentTest));
        return model;
    }

    private long getRemainingTime(PassedTest passedTest, Test currentTest) {
        DateTime startTestTime = convertStringToDate(passedTest.getStartTest());
        long delta = new DateTime().getMillis() - startTestTime.getMillis();
        int timeOfTest = currentTest.getTimeOfTest() * SECONDS_FROM_MINUTE;
        return timeOfTest - (delta) / MILLISECONDS_FROM_SECOND;
    }

    private ModelAndView checkTestFromSession(HttpSession session, Test currentTest, String testID) {
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

    private PassedTestModelAttribute getPassedTestModelAttribute(Test currentTest) {
        PassedTestModelAttribute passedTest = new PassedTestModelAttribute();
        List<PassedQuestionModelAttribute> passedQuestions = new ArrayList<>();
        for (int i = 0; i < currentTest.getQuestionIds().size(); i++) {
            PassedQuestionModelAttribute passedQuestion = new PassedQuestionModelAttribute();
            passedQuestions.add(passedQuestion);
        }
        passedTest.setPassedQuestions(passedQuestions);
        return passedTest;
    }

    private PassedTest getPassedTest(Test currentTest) {
        PassedTest passedTest = new PassedTest();
        passedTest.setId(currentTest.getId());
        passedTest.setTestId(currentTest.getId());
        passedTest.setStartTest(convertDateToString(new DateTime()));
        return passedTest;
    }

}
