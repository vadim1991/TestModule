package com.englishschool.service.profile;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.dao.profile.ProfileDaoImpl;
import com.englishschool.entity.Test;
import com.englishschool.entity.TestProfile;
import com.englishschool.service.generic.GenericManagerImpl;
import com.englishschool.service.test.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 10/5/2015.
 */
@Service
public class ProfileServiceImpl extends GenericManagerImpl<TestProfile, ProfileDaoImpl> implements IProfileService {

    @Autowired
    private ITestService testService;

    @Autowired
    @Qualifier("profileDao")
    @Override
    public void setDao(GenericDao dao) {
        super.setDao(dao);
    }

    @Override
    public boolean addPassedTestToProfile(String profileID, String passedTestID) {
        boolean result = false;
        TestProfile profile = findById(profileID);
        if (profile != null) {
            List<String> availableTests = profile.getAvailableTests();
            availableTests.remove(passedTestID);
            List<String> passedTests = profile.getPassedTests();
            if (passedTests != null) {
                passedTests.add(passedTestID);
            } else {
                ArrayList<String> newPassedTestList = new ArrayList<>();
                newPassedTestList.add(passedTestID);
                profile.setPassedTests(newPassedTestList);
            }
            save(profile);
            result = true;
        }
        return result;
    }

    @Override
    public boolean isPassedTestExist(String profileID, String passedID) {
        boolean result = false;
        TestProfile profile = findById(profileID);
        if (profile != null) {
            List<String> passedTests = profile.getPassedTests();
            if (passedTests != null) {
                result = passedTests.contains(passedID);
            }
        }
        return result;
    }

    @Override
    public List<String> getAvailableTests(String profileID) {
        TestProfile profile = findById(profileID);
        List<String> availableTests = profile.getAvailableTests();
        return availableTests == null ? new ArrayList<String>() : availableTests;
    }

    @Override
    public List<String> getPassedTests(String profileID) {
        TestProfile profile = findById(profileID);
        List<String> passedTests = profile.getPassedTests();
        return passedTests == null ? new ArrayList<String>() : passedTests;
    }
}
