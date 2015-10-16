package com.englishschool.service.test;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.dao.test.TestDaoImpl;
import com.englishschool.datamodel.CommonConstants;
import com.englishschool.entity.Test;
import com.englishschool.service.generic.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.englishschool.datamodel.CommonConstants.*;

/**
 * Created by Administrator on 10/5/2015.
 */
@Service
@EnableCaching
public class TestServiceImpl extends GenericManagerImpl<Test, TestDaoImpl> implements ITestService {

    @Autowired
    @Qualifier("testDao")
    @Override
    public void setDao(GenericDao dao) {
        super.setDao(dao);
    }

    @Override
    public Test getTestFromDB(HttpSession session, String profileID, String testID) {
        return null;
    }

    @Cacheable(value = "tests")
    @Override
    public List<Test> getTestByListIDS(List<String> ids) {
        return dao.getTestByListIDS(ids);
    }
}
