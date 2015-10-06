package com.englishschool.dao.test;

import com.englishschool.dao.generic.GenericMongoDBDaoImpl;
import com.englishschool.entity.Test;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 10/5/2015.
 */
@Repository("testDao")
public class TestDaoImpl extends GenericMongoDBDaoImpl<Test> implements ITestDao<Test> {

    public TestDaoImpl() {
        setClazz(Test.class);
    }
}
