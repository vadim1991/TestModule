package com.englishschool.dao.test;

import com.englishschool.dao.generic.GenericMongoDBDaoImpl;
import com.englishschool.entity.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.englishschool.datamodel.CommonConstants.ID;

/**
 * Created by Administrator on 10/5/2015.
 */
@Repository("testDao")
public class TestDaoImpl extends GenericMongoDBDaoImpl<Test> implements ITestDao<Test> {

    public TestDaoImpl() {
        setClazz(Test.class);
    }

    @Override
    public List<Test> getTestByListIDS(List<String> ids) {
        Query query = new Query(Criteria.where(ID).in(ids));
        return getMongoOperations().find(query, Test.class);
    }
}
