package com.englishschool.service.passedtest;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.dao.passedtest.PassedTestDaoImpl;
import com.englishschool.entity.*;
import com.englishschool.entity.spring.PassedQuestionModelAttribute;
import com.englishschool.entity.spring.PassedTestModelAttribute;
import com.englishschool.service.generic.GenericManagerImpl;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.englishschool.datamodel.CacheConstants.PASSED_TASK;
import static com.englishschool.datamodel.CacheConstants.PASSED_TESTS;
import static com.englishschool.datamodel.CommonConstants.*;
import static com.englishschool.service.helper.ServiceUtils.convertDateToString;

/**
 * Created by Administrator on 10/7/2015.
 */
@Service
@EnableCaching
public class PassedTestServiceImpl extends GenericManagerImpl<PassedTest, PassedTestDaoImpl> implements IPassedTestService {

    @Autowired
    @Qualifier(PASSED_TEST_DAO)
    @Override
    public void setDao(GenericDao dao) {
        super.setDao(dao);
    }

    @CacheEvict(value = PASSED_TESTS, allEntries = true)
    @Override
    public void save(PassedTest entity) {
        super.save(entity);
    }

    @Cacheable(value = PASSED_TASK)
    @Override
    public PassedTest findById(String id) {
        return super.findById(id);
    }

    @Cacheable(value = PASSED_TESTS)
    @Override
    public List<PassedTest> getPassedTestsByListIDS(List<String> ids) {
        return dao.getPassedTestsByListIDS(ids);
    }

    @Override
    public PassedTest getPassedTestFromTest(Test currentTest, String profileID) {
        PassedTest passedTest = new PassedTest();
        passedTest.setId(currentTest.getId() + profileID);
        passedTest.setTestId(currentTest.getId());
        passedTest.setStartTest(convertDateToString(new DateTime()));
        return passedTest;
    }

    public PassedTest getAndCheckPassedTestFromModel(PassedTestModelAttribute passedModel, HttpSession session) {
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
        passedTest.setResult((int) (countRight / questions.size() * 100));
        return passedTest;
    }

    @Override
    public PassedTestModelAttribute getPassedTestModelAttribute(Test currentTest) {
        PassedTestModelAttribute passedTest = new PassedTestModelAttribute();
        List<PassedQuestionModelAttribute> passedQuestions = new ArrayList<>();
        for (int i = 0; i < currentTest.getQuestionIds().size(); i++) {
            PassedQuestionModelAttribute passedQuestion = new PassedQuestionModelAttribute();
            passedQuestions.add(passedQuestion);
        }
        passedTest.setPassedQuestions(passedQuestions);
        return passedTest;
    }

}
