package com.englishschool.service.test;

import com.englishschool.entity.Test;
import com.englishschool.entity.datatable.TestForDataTableBean;
import com.englishschool.service.generic.GenericManager;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 10/5/2015.
 */
public interface ITestService extends GenericManager<Test> {

    Test getTestFromDB(HttpSession session, String profileID, String testID);

    List<Test> getTestByListIDS(List<String> ids);

    List<TestForDataTableBean> convertTestsForDataTableBean(List<Test> tests);

}
