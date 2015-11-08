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

import static com.englishschool.datamodel.CommonConstants.*;

/**
 * Created by Administrator on 10/5/2015.
 */
@Service
public class ProfileServiceImpl extends GenericManagerImpl<TestProfile, ProfileDaoImpl> implements IProfileService {

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
                profileDataTableBean.setAverageMark(profile.getAverageMark());
                profileDataTableBean.setEmail(profile.getEmail());
                profileDataTableBean.setName(profile.getName());
                profileDataTableBean.setSurname(profile.getSurname());
                profileDataTableBean.setUpdateLink(String.format(UPDATE_PROFILE_LINK_FORMAT, profile.getId()));
                profileForDataTableBeans.add(profileDataTableBean);
            }
        }
        return profileForDataTableBeans;
    }

    @Override
    public TestProfile findByEmail(String email) {
        List<TestProfile> usersByCriteria = dao.findUsersByCriteria(EMAIL, email);
        return usersByCriteria != null ? usersByCriteria.get(0) : null;
    }

    @Override
    public void addAvailableTestsToProfiles(AssignTestBean assignTestBean, boolean isAssign) {
        dao.addAvailableTestsToProfiles(assignTestBean, isAssign);
    }

//    @Override
//    public boolean addPassedTestToProfile(String profileID, PassedTest passedTest) {
//        boolean result = false;
//        if (profileID != null && passedTest != null) {
//            TestProfile profile = findById(profileID);
//            int averageMark = calculateAverageMark(profile.getAverageMark(), passedTest.getResult(), profile.getPassedTests().size());
//            profile.setAverageMark(averageMark);
//            save(profile);
//            result = dao.addPassedTestToProfile(profileID, passedTest);
//        }
//        return result;
//    }

    private int calculateAverageMark(int markFromUser, int markFromPassedTest, int passedTestAmount) {
        int oldAverageMark = markFromUser * (passedTestAmount - 1);
        return (oldAverageMark + markFromPassedTest) / passedTestAmount;
    }

}
