package com.englishschool.service.profile;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.dao.profile.ProfileDaoImpl;
import com.englishschool.entity.PassedTest;
import com.englishschool.entity.TestProfile;
import com.englishschool.entity.datatable.ProfileDataTableBean;
import com.englishschool.entity.spring.AssignTestBean;
import com.englishschool.service.generic.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.englishschool.datamodel.CommonConstants.GROUP_ID;
import static com.englishschool.datamodel.CommonConstants.PROFILE_DAO;

/**
 * Created by Administrator on 10/5/2015.
 */
@Service
public class ProfileServiceImpl extends GenericManagerImpl<TestProfile, ProfileDaoImpl> implements IProfileService {

    public static final String EMAIL = "email";

    @Autowired
    @Qualifier(PROFILE_DAO)
    @Override
    public void setDao(GenericDao dao) {
        super.setDao(dao);
    }

    @Override
    public boolean addPassedTestToProfile(String profileID, PassedTest passedTest) {
        boolean result = false;
        TestProfile profile = findById(profileID);
        if (profile != null) {
            String passedTestID = passedTest.getId();
            List<String> availableTests = profile.getAvailableTests();
            availableTests.remove(passedTest.getTestId());
            List<String> passedTests = profile.getPassedTests();
            if (passedTests == null) {
                passedTests = new ArrayList<>();
            }
            passedTests.add(passedTestID);
            profile.setPassedTests(passedTests);
            int averageMark = calculateAverageMark(profile.getAverageMark(), passedTest.getResult(), passedTests.size());
            profile.setAverageMark(averageMark);
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

    public List<TestProfile> getAll() {
        return dao.findAll();
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

    @Override
    public List<ProfileDataTableBean> convertProfileForDataTableBean(List<TestProfile> profiles) {
        List<ProfileDataTableBean> profileForDataTableBeans = null;
        if (profiles != null) {
            profileForDataTableBeans = new ArrayList<>();
            for (TestProfile profile : profiles) {
                ProfileDataTableBean profileDataTableBean = new ProfileDataTableBean();
                profileDataTableBean.setId(profile.getId());
                profileDataTableBean.setAge(profile.getAge());
                profileDataTableBean.setEmail(profile.getEmail());
                profileDataTableBean.setName(profile.getName());
                profileDataTableBean.setSurname(profile.getSurname());
                profileForDataTableBeans.add(profileDataTableBean);
            }
        }
        return profileForDataTableBeans;
    }

    @Override
    public void assignTestToProfiles(Set<TestProfile> testProfiles, List<String> testIDs) {
        if (testProfiles != null) {
            Set<TestProfile> resultProfiles = new HashSet<>();
            for (TestProfile testProfile : testProfiles) {
                List<String> availableTests = testProfile.getAvailableTests();
                Set<String> availableTestsSet = new HashSet<>();
                if (availableTests != null) {
                    availableTestsSet.addAll(testProfile.getAvailableTests());
                }
                availableTestsSet.addAll(testIDs);
                testProfile.setAvailableTests(new ArrayList<>(availableTestsSet));
                save(testProfile);
                resultProfiles.add(testProfile);
            }
        }
    }

    @Override
    public Set<TestProfile> getProfilesFromAssignBean(AssignTestBean assignTestBean) {
        List<String> groupIDs = assignTestBean.getGroupIDs();
        Set<TestProfile> testProfiles = new LinkedHashSet<>();
        if (groupIDs != null) {
            for (String groupId : groupIDs) {
                testProfiles.addAll(dao.findUsersByCriteria(GROUP_ID, groupId));
            }
        }
        List<String> profileIDs = assignTestBean.getProfileIDs();
        if (profileIDs != null) {
            testProfiles.addAll(dao.findProfilesByIDs(profileIDs));
        }
        return testProfiles;
    }

    @Override
    public TestProfile findByEmail(String email) {
        List<TestProfile> usersByCriteria = dao.findUsersByCriteria(EMAIL, email);
        return usersByCriteria != null ? usersByCriteria.get(0) : null;
    }

    @Override
    public int getAverageMark(List<PassedTest> passedTests) {
        int totalMark = 0;
        for (PassedTest passedTest : passedTests) {
            totalMark += passedTest.getResult();
        }
        return totalMark / passedTests.size();
    }

    private int calculateAverageMark(int markFromUser, int markFromPassedTest, int passedTestAmount) {
        return (markFromUser + markFromPassedTest) / passedTestAmount;
    }

}
