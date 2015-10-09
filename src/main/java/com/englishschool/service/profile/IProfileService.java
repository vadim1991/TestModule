package com.englishschool.service.profile;

import com.englishschool.entity.TestProfile;
import com.englishschool.service.generic.GenericManager;

import java.util.List;

/**
 * Created by Administrator on 10/5/2015.
 */
public interface IProfileService extends GenericManager<TestProfile> {

    boolean addPassedTestToProfile(String profileID, String passedTestID);

    boolean isPassedTestExist(String profileID, String passedTest);

    List<String> getAvailableTests(String profileID);

    List<String> getPassedTests(String profileID);

}
