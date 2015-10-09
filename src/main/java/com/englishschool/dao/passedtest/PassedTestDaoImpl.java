package com.englishschool.dao.passedtest;

import com.englishschool.dao.generic.GenericMongoDBDaoImpl;
import com.englishschool.entity.PassedTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.englishschool.datamodel.CommonConstants.ID;

/**
 * Created by Administrator on 10/7/2015.
 */
@Repository("passedTestDao")
public class PassedTestDaoImpl extends GenericMongoDBDaoImpl<PassedTest> implements IPassedTestDao<PassedTest> {

    public PassedTestDaoImpl() {
        setClazz(PassedTest.class);
    }

    @Override
    public List<PassedTest> getPassedTestsByListIDS(List<String> ids) {
        Query query = new Query(Criteria.where(ID).in(ids));
        return getMongoOperations().find(query, PassedTest.class);
    }
}
