package com.englishschool.service.passedtest;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.dao.passedtest.PassedTestDaoImpl;
import com.englishschool.entity.PassedTest;
import com.englishschool.service.generic.GenericManagerImpl;
import com.englishschool.service.test.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 10/7/2015.
 */
@Service
public class PassedTestServiceImpl extends GenericManagerImpl<PassedTest, PassedTestDaoImpl> implements ITestService<PassedTest> {

    @Autowired
    @Qualifier("passedTestDao")
    @Override
    public void setDao(GenericDao dao) {
        super.setDao(dao);
    }
}
