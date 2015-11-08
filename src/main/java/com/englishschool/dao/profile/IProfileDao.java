package com.englishschool.dao.profile;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.entity.PassedTest;
import com.englishschool.entity.TestProfile;
import com.englishschool.entity.spring.AssignTestBean;

import java.util.List;
import java.util.Set;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
public interface IProfileDao<T> extends GenericDao<T> {

    List<TestProfile> findUsersByCriteria(String property, Object value);

    List<TestProfile> findProfilesByIDs(List<String> IDs);

    void saveMultiplyProfiles(Set<TestProfile> testProfiles);

    void addAvailableTestsToProfiles(AssignTestBean assignTestBean, boolean isAssign);

    boolean addPassedTestToProfile(String profileID, PassedTest passedTest);

}
