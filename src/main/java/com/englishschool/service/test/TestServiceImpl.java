package com.englishschool.service.test;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.dao.test.TestDaoImpl;
import com.englishschool.entity.Test;
import com.englishschool.service.generic.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 10/5/2015.
 */
@Service
public class TestServiceImpl extends GenericManagerImpl<Test, TestDaoImpl> implements ITestService<Test> {

    @Autowired
    @Qualifier("testDao")
    @Override
    public void setDao(GenericDao dao) {
        super.setDao(dao);
    }

}
