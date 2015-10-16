package com.englishschool.service.passedtest;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.dao.passedtest.PassedTestDaoImpl;
import com.englishschool.entity.PassedTest;
import com.englishschool.service.generic.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 10/7/2015.
 */
@Service
@EnableCaching
public class PassedTestServiceImpl extends GenericManagerImpl<PassedTest, PassedTestDaoImpl> implements IPassedTestService {

    @Autowired
    @Qualifier("passedTestDao")
    @Override
    public void setDao(GenericDao dao) {
        super.setDao(dao);
    }

    @Cacheable(value = "passedTask")
    @Override
    public PassedTest findById(String id) {
        return super.findById(id);
    }

    @Cacheable(value = "passed-tests")
    @Override
    public List<PassedTest> getPassedTestsByListIDS(List<String> ids) {
        System.out.println("---------------- passed Tests DB");
        return dao.getPassedTestsByListIDS(ids);
    }
}
