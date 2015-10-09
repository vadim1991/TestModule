package com.englishschool.service.passedtest;

import com.englishschool.entity.PassedTest;
import com.englishschool.service.generic.GenericManager;

import java.util.List;

/**
 * Created by Administrator on 10/7/2015.
 */
public interface IPassedTestService extends GenericManager<PassedTest> {

    List<PassedTest> getPassedTestsByListIDS(List<String> ids);

}
