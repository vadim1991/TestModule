package com.englishschool.controllers;

import com.englishschool.entity.*;
import com.englishschool.entity.spring.PassedQuestionModelAttribute;
import com.englishschool.entity.spring.PassedTestModelAttribute;
import com.englishschool.service.passedtest.PassedTestServiceImpl;
import com.englishschool.service.profile.ProfileServiceImpl;
import com.englishschool.service.question.QuestionServiceImpl;
import com.englishschool.service.test.TestServiceImpl;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

import static com.englishschool.datamodel.CommonConstants.*;

/**
 * Created by Administrator on 10/1/2015.
 */
@Controller
public class RunTestController {

    public static final String CHECKBOX_STATE = "checkbox-state";
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private TestServiceImpl testService;
    @Autowired
    private ProfileServiceImpl profileService;
    @Autowired
    private PassedTestServiceImpl passedTestService;

    @RequestMapping(value = "/result/test/{id}", method = RequestMethod.GET)
    public ModelAndView showHistoryPassedTest(@PathVariable("id") String passedTestID) {
        PassedTest passedTest = passedTestService.findById(passedTestID);
        return new ModelAndView(RESULT_PAGE, PASSED_TEST, passedTest);
    }

    @RequestMapping(value = "/test/check", method = RequestMethod.POST)
    public String checkTest(@ModelAttribute("passedTestModel") PassedTestModelAttribute passedModel, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        PassedTest passedTestFromModel = getPassedTestFromModel(passedModel, session);
        passedTestService.save(passedTestFromModel);
        removeAllCookies(request, response);
        return "redirect:/result/test/" + passedTestFromModel.getId();
    }

    @RequestMapping(value = "/run/test/{id}", method = RequestMethod.GET)
    public ModelAndView runTestById(@PathVariable("id") String testID, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(PROFILE_ID, "11111");
        String profileID = (String) session.getAttribute(PROFILE_ID);
        Test currentTest = (Test) session.getAttribute(CURRENT_TEST);
        ModelAndView modelAndView = checkTestFromSession(session, currentTest, testID);
        if (modelAndView == null) {
            System.out.println("++++++DB");
            modelAndView = getTestFromDB(session, profileID, testID);
        }
        return modelAndView;
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
        System.out.println("----------" + countRight);
        passedTest.setEndTest(new DateTime());
        passedTest.setPassedQuestions(passedQuestions);
        passedTest.setResult(countRight/questions.size()*100);
        return passedTest;
    }

    private void removeAllCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().contains(CHECKBOX_STATE)) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    private ModelAndView getTestFromDB(HttpSession session, String profileID, String testID) {
        String resultPage = ERROR_PAGE;
        HashMap<String, Object> model = new HashMap<>();
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

    private HashMap<String, Object> getModelWithQuestions(List<Question> questions, Test currentTest, PassedTest passedTest) {
        HashMap<String, Object> model = new HashMap<>();
        model.put(QUESTIONS, questions);
        model.put(TIMER, getRemainingTime(passedTest, currentTest));
        model.put(PASSED_TEST_MODEL, getPassedTestModelAttribute(currentTest));
        return model;
    }

    private long getRemainingTime(PassedTest passedTest, Test currentTest) {
        long delta = new DateTime().getMillis() - passedTest.getStartTest().getMillis();
        return currentTest.getTimeOfTest() - (delta) / MILLISECONDS_FROM_SECOND;
    }

    private ModelAndView checkTestFromSession(HttpSession session, Test currentTest, String testID) {
        if (currentTest != null) {
            if (currentTest.getId().equals(testID)) {
                System.out.println(SESSION);
                PassedTest passedTest = (PassedTest) session.getAttribute(PASSED_TEST);
                List<Question> questions = (List<Question>) session.getAttribute(QUESTIONS);
                return new ModelAndView(TEST_PAGE, getModelWithQuestions(questions, currentTest, passedTest));
            } else {
                HashMap<String, Object> model = new HashMap<>();
                model.put(TEST_ID, currentTest.getId());
                return new ModelAndView(CURRENT_TEST_PAGE, model);
            }
        }
        return null;
    }

    private TestProfile getProfile() {
        TestProfile testProfile = new TestProfile();
        ArrayList<String> tests = new ArrayList<>();
        testProfile.setId("11111");
        Test test = getTest();
        tests.add(test.getId());
        testProfile.setAvailableTests(tests);
        return testProfile;
    }

    private Test getTest() {
        Test test = new Test();
        ArrayList<String> questions = new ArrayList<>();
        questions.add("5612c3c295de30f9fd2392f1");
        questions.add("5612c3e295de30f9fd2392f2");
        test.setId("12345");
        test.setQuestionIds(questions);
        test.setTimeOfTest(1200);
        testService.save(test);
        return test;
    }

    private PassedTestModelAttribute getPassedTestModelAttribute(Test currentTest) {
        PassedTestModelAttribute passedTest = new PassedTestModelAttribute();
        ArrayList<PassedQuestionModelAttribute> passedQuestions = new ArrayList<>();
        for (int i = 0; i < currentTest.getQuestionIds().size(); i++) {
            PassedQuestionModelAttribute passedQuestion = new PassedQuestionModelAttribute();
            passedQuestions.add(passedQuestion);
        }
        passedTest.setPassedQuestions(passedQuestions);
        return passedTest;
    }

    private PassedTest getPassedTest(Test currentTest) {
        PassedTest passedTest = new PassedTest();
        passedTest.setTestId(currentTest.getId());
        passedTest.setStartTest(new DateTime());
        return passedTest;
    }

    private List<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        Question question = new Question();
        Answer answer = new Answer();
        Answer answer1 = new Answer();
        answer.setAnswerText("answer1");
        answer1.setAnswerText("answer1");
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(answer);
        answers.add(answer1);
        question.setId("12345");
        question.setAnswers(answers);
        question.setTitle("new Question");
        question.setQuestionContent("<code class=\"java\"><span class=\"java__keyword\">package</span>&nbsp;test;&nbsp;<br>&nbsp;<br><span class=\"java__keyword\">class</span>&nbsp;BoxPrinter&nbsp;{&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"java__keyword\">private</span>&nbsp;Object&nbsp;val;&nbsp;<br>&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"java__keyword\">public</span>&nbsp;BoxPrinter(Object&nbsp;arg)&nbsp;{&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;val&nbsp;=&nbsp;arg;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;}&nbsp;<br>&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"java__keyword\">public</span>&nbsp;String&nbsp;toString()&nbsp;{&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"java__keyword\">return</span>&nbsp;<span class=\"java__string\">\"{\"</span>&nbsp;+&nbsp;val&nbsp;+&nbsp;<span class=\"java__string\">\"}\"</span>;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;}&nbsp;<br>&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"java__keyword\">public</span>&nbsp;Object&nbsp;getValue()&nbsp;{&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"java__keyword\">return</span>&nbsp;val;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;}&nbsp;<br>}&nbsp;<br>&nbsp;<br><span class=\"java__keyword\">class</span>&nbsp;Test&nbsp;{&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"java__keyword\">public</span>&nbsp;<span class=\"java__keyword\">static</span>&nbsp;<span class=\"java__keyword\">void</span>&nbsp;main(String[]&nbsp;args)&nbsp;{&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;BoxPrinter&nbsp;value1&nbsp;=&nbsp;<span class=\"java__keyword\">new</span>&nbsp;BoxPrinter(<span class=\"java__keyword\">new</span>&nbsp;Integer(<span class=\"java__number\">10</span>));&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;System.out.println(value1);&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Integer&nbsp;intValue1&nbsp;=&nbsp;(Integer)&nbsp;value1.getValue();&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;BoxPrinter&nbsp;value2&nbsp;=&nbsp;<span class=\"java__keyword\">new</span>&nbsp;BoxPrinter(<span class=\"java__string\">\"Hello&nbsp;world\"</span>);&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;System.out.println(value2);&nbsp;<br>&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"java__com\"></span>&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"java__com\"></span>&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Integer&nbsp;intValue2&nbsp;=&nbsp;(Integer)&nbsp;value2.getValue();&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;}&nbsp;<br>}&nbsp;<br></code>");
        Question question1 = new Question();
        question1.setId("123");
        question1.setAnswers(answers);
        question1.setTitle("new Question2");
        question1.setQuestionContent("<code class=\"java\"><span class=\"java__keyword\">package</span>&nbsp;test;&nbsp;<br>&nbsp;<br><span class=\"java__keyword\">class</span>&nbsp;BoxPrinter&nbsp;{&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"java__keyword\">private</span>&nbsp;Object&nbsp;val;&nbsp;<br>&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"java__keyword\">public</span>&nbsp;BoxPrinter(Object&nbsp;arg)&nbsp;{&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;val&nbsp;=&nbsp;arg;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;}&nbsp;<br>&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"java__keyword\">public</span>&nbsp;String&nbsp;toString()&nbsp;{&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"java__keyword\">return</span>&nbsp;<span class=\"java__string\">\"{\"</span>&nbsp;+&nbsp;val&nbsp;+&nbsp;<span class=\"java__string\">\"}\"</span>;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;}&nbsp;<br>&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"java__keyword\">public</span>&nbsp;Object&nbsp;getValue()&nbsp;{&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"java__keyword\">return</span>&nbsp;val;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;}&nbsp;<br>}&nbsp;<br>&nbsp;<br><span class=\"java__keyword\">class</span>&nbsp;Test&nbsp;{&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"java__keyword\">public</span>&nbsp;<span class=\"java__keyword\">static</span>&nbsp;<span class=\"java__keyword\">void</span>&nbsp;main(String[]&nbsp;args)&nbsp;{&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;BoxPrinter&nbsp;value1&nbsp;=&nbsp;<span class=\"java__keyword\">new</span>&nbsp;BoxPrinter(<span class=\"java__keyword\">new</span>&nbsp;Integer(<span class=\"java__number\">10</span>));&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;System.out.println(value1);&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Integer&nbsp;intValue1&nbsp;=&nbsp;(Integer)&nbsp;value1.getValue();&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;BoxPrinter&nbsp;value2&nbsp;=&nbsp;<span class=\"java__keyword\">new</span>&nbsp;BoxPrinter(<span class=\"java__string\">\"Hello&nbsp;world\"</span>);&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;System.out.println(value2);&nbsp;<br>&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"java__com\"></span>&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"java__com\"></span>&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Integer&nbsp;intValue2&nbsp;=&nbsp;(Integer)&nbsp;value2.getValue();&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;}&nbsp;<br>}&nbsp;<br></code>");
        questions.add(question);
        questions.add(question);
        questions.add(question1);
        questions.add(question1);
        return questions;
    }

}
