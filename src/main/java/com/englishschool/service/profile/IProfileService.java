package com.englishschool.service.profile;

import com.englishschool.entity.PassedTest;
import com.englishschool.entity.TestProfile;
import com.englishschool.entity.datatable.ProfileDataTableBean;
import com.englishschool.entity.spring.AssignTestBean;
import com.englishschool.service.generic.GenericManager;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 10/5/2015.
 */
public interface IProfileService extends GenericManager<TestProfile> {

    //boolean addPassedTestToProfile(String profileID, PassedTest passedTest);

    boolean isPassedTestExist(String profileID, String passedTest);

    List<String> getAvailableTests(String profileID);

    List<String> getPassedTests(String profileID);

    List<TestProfile> getAll();

    List<ProfileDataTableBean> convertProfileForDataTableBean(List<TestProfile> questions);

    TestProfile findByEmail(String email);

    void addAvailableTestsToProfiles(AssignTestBean assignTestBean, boolean isAssign);

    boolean addPassedTestToProfile(String profileID, PassedTest passedTest);

}
