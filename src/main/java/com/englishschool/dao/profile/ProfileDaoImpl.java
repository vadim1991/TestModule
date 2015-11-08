package com.englishschool.dao.profile;

import com.englishschool.dao.generic.GenericMongoDBDaoImpl;
import com.englishschool.entity.PassedTest;
import com.englishschool.entity.TestProfile;
import com.englishschool.entity.spring.AssignTestBean;
import com.mongodb.WriteResult;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

import static com.englishschool.datamodel.CacheConstants.PROFILE;
import static com.englishschool.datamodel.CommonConstants.AVAILABLE_TESTS;
import static com.englishschool.datamodel.CommonConstants.GROUP_ID;
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

    @Override
    public void addAvailableTestsToProfiles(AssignTestBean assignTestBean, boolean isAssign) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        List<String> groupIDs = assignTestBean.getGroupIDs();
        List<String> profileIDs = assignTestBean.getProfileIDs();
        if (!CollectionUtils.isEmpty(assignTestBean.getProfileIDs())) {
            criteria.where(ID).is(profileIDs);
        }
        if (!CollectionUtils.isEmpty(groupIDs)) {
            criteria.andOperator(Criteria.where(GROUP_ID).in(groupIDs));
        }
        query.addCriteria(criteria);
        Update update = getUpdateForAssign(isAssign,assignTestBean.getTestIDs());
        getMongoOperations().updateMulti(query, update, getClazz());
    }

    @Override
    public boolean addPassedTestToProfile(String profileID, PassedTest passedTest) {
        Query query = new Query(Criteria.where(ID).is(profileID));
        Update update = new Update();
        update.pull(AVAILABLE_TESTS, passedTest.getTestId());
        update.addToSet(passedTest.getId());
        WriteResult writeResult = getMongoOperations().updateMulti(query, update, getClazz());
        return writeResult.isUpdateOfExisting();
    }

    private Update getUpdateForAssign(boolean isAssign, List<String> testIDs) {
        Update update = new Update();
            for (String testID : testIDs) {
                if (isAssign) {
                    update.addToSet(AVAILABLE_TESTS, testID);
                } else {
                    update.pull(AVAILABLE_TESTS, testID);
                }
            }
        return update;
    }

}
