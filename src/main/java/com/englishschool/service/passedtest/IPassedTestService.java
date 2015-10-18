package com.englishschool.service.passedtest;

import com.englishschool.entity.PassedTest;
import com.englishschool.entity.Test;
import com.englishschool.entity.spring.PassedTestModelAttribute;
import com.englishschool.service.generic.GenericManager;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 10/7/2015.
 */
public interface IPassedTestService extends GenericManager<PassedTest> {

    List<PassedTest> getPassedTestsByListIDS(List<String> ids);

    PassedTest getPassedTestFromTest(Test currentTest, String profileID);

    PassedTest getAndCheckPassedTestFromModel(PassedTestModelAttribute passedModel, HttpSession session);

    PassedTestModelAttribute getPassedTestModelAttribute(Test currentTest);

}
