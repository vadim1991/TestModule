package com.englishschool.dao.profile;

import com.englishschool.dao.generic.GenericMongoDBDaoImpl;
import com.englishschool.entity.TestProfile;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
@Repository("profileDao")
public class ProfileDaoImpl extends GenericMongoDBDaoImpl<TestProfile> implements IProfileDao<TestProfile> {

    public ProfileDaoImpl() {
        setClazz(TestProfile.class);
    }

    @Override
    public List<TestProfile> findUsersByCriteria(String property, Object value) {
        Query query = new Query(Criteria.where(property).is(value));
        return getMongoOperations().find(query, getClazz());
    }



}
