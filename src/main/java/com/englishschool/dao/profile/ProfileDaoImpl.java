package com.englishschool.dao.profile;

import com.englishschool.dao.generic.GenericMongoDBDaoImpl;
import com.englishschool.entity.TestProfile;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static com.englishschool.datamodel.CacheConstants.PROFILE;
import static com.englishschool.datamodel.CommonConstants.ID;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
@Repository("profileDao")
public class ProfileDaoImpl extends GenericMongoDBDaoImpl<TestProfile> implements IProfileDao<TestProfile> {

    public ProfileDaoImpl() {
        setClazz(TestProfile.class);
    }

    @Override
    public TestProfile findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<TestProfile> findUsersByCriteria(String property, Object value) {
        Query query = new Query(Criteria.where(property).is(value));
        return getMongoOperations().find(query, getClazz());
    }

    @Override
    public List<TestProfile> findProfilesByIDs(List<String> IDs) {
        Query query = new Query(Criteria.where(ID).in(IDs));
        return getMongoOperations().find(query, getClazz());
    }

    @Override
    public void saveMultiplyProfiles(Set<TestProfile> testProfiles) {
        getMongoOperations().insert(testProfiles, getClazz());
    }

}
