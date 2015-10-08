package com.englishschool.dao.passedtest;

import com.englishschool.dao.generic.GenericMongoDBDaoImpl;
import com.englishschool.entity.PassedTest;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 10/7/2015.
 */
@Repository("passedTestDao")
public class PassedTestDaoImpl extends GenericMongoDBDaoImpl<PassedTest> implements com.englishschool.dao.generic.GenericDao<PassedTest> {

    public PassedTestDaoImpl() {
        setClazz(PassedTest.class);
    }
}
