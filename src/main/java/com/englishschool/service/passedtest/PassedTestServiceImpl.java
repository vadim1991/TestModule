package com.englishschool.service.passedtest;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.dao.passedtest.PassedTestDaoImpl;
import com.englishschool.datamodel.CommonConstants;
import com.englishschool.entity.PassedTest;
import com.englishschool.service.generic.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.englishschool.datamodel.CacheConstants.PASSED_TASK;
import static com.englishschool.datamodel.CacheConstants.PASSED_TESTS;
import static com.englishschool.datamodel.CommonConstants.*;

/**
 * Created by Administrator on 10/7/2015.
 */
@Service
@EnableCaching
public class PassedTestServiceImpl extends GenericManagerImpl<PassedTest, PassedTestDaoImpl> implements IPassedTestService {

    @Autowired
    @Qualifier(PASSED_TEST_DAO)
    @Override
    public void setDao(GenericDao dao) {
        super.setDao(dao);
    }

    @CacheEvict(value = PASSED_TESTS, allEntries = true)
    @Override
    public void save(PassedTest entity) {
        super.save(entity);
    }

    @Cacheable(value = PASSED_TASK)
    @Override
    public PassedTest findById(String id) {
        return super.findById(id);
    }

    @Cacheable(value = PASSED_TESTS)
    @Override
    public List<PassedTest> getPassedTestsByListIDS(List<String> ids) {
        return dao.getPassedTestsByListIDS(ids);
    }
}
