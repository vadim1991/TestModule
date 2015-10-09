package com.englishschool.dao.passedtest;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.entity.PassedTest;

import java.util.List;

/**
 * Created by Administrator on 10/8/2015.
 */
public interface IPassedTestDao<T> extends GenericDao<T> {

    List<PassedTest> getPassedTestsByListIDS(List<String> ids);

}
