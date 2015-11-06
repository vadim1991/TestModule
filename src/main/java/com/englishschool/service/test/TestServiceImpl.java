package com.englishschool.service.test;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.dao.test.TestDaoImpl;
import com.englishschool.entity.Test;
import com.englishschool.entity.datatable.TestForDataTableBean;
import com.englishschool.service.generic.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.englishschool.datamodel.CacheConstants.TESTS;
import static com.englishschool.datamodel.CommonConstants.TEST_DAO;
import static com.englishschool.datamodel.CommonConstants.UPDATE_TEST_LINK_FORMAT;

/**
 * Created by Administrator on 10/5/2015.
 */
@Service
@EnableCaching
public class TestServiceImpl extends GenericManagerImpl<Test, TestDaoImpl> implements ITestService {

    @Autowired
    @Qualifier(TEST_DAO)
    @Override
    public void setDao(GenericDao dao) {
        super.setDao(dao);
    }

    @Override
    public Test getTestFromDB(HttpSession session, String profileID, String testID) {
        return null;
    }

    @Cacheable(value = TESTS)
    @Override
    public List<Test> getTestByListIDS(List<String> ids) {
        return dao.getTestByListIDS(ids);
    }

    @Override
    public List<TestForDataTableBean> convertTestsForDataTableBean(List<Test> tests) {
        List<TestForDataTableBean> beanList = null;
        if (tests != null) {
            beanList = new ArrayList<>();
            for (Test test : tests) {
                TestForDataTableBean bean = new TestForDataTableBean();
                bean.setCreationDate(test.getCreationDate());
                bean.setId(test.getId());
                bean.setTestTitle(test.getTestTitle());
                bean.setTimeOfTest(test.getTimeOfTest());
                bean.setUpdateLink(String.format(UPDATE_TEST_LINK_FORMAT, test.getId()));
                bean.setQuestionAmount(test.getQuestionIds().size());
                beanList.add(bean);
            }
        }
        return beanList;
    }
}
