package com.englishschool.dao.test;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.entity.Test;

import java.util.List;

/**
 * Created by Administrator on 10/5/2015.
 */
public interface ITestDao<T> extends GenericDao<T> {

    List<Test> getTestByListIDS(List<String> ids);

}
