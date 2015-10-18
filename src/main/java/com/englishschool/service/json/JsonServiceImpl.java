package com.englishschool.service.json;

import com.englishschool.entity.datatable.DataTableBean;
import com.englishschool.entity.datatable.QuestionForDataTableBean;
import com.englishschool.entity.datatable.TestForDataTableBean;
import com.englishschool.entity.json.QuestionJsonBean;
import com.englishschool.entity.json.TestJsonBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vadym_Vlasenko on 10/17/2015.
 */
@Service("jsonService")
public class JsonServiceImpl implements JsonService {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String questionsDataToJson(List<QuestionForDataTableBean> questionForDataTableBeans, DataTableBean dataTableBean, Integer totalCount) throws JsonProcessingException {
        QuestionJsonBean questionJsonBean = new QuestionJsonBean();
        questionJsonBean.setDraw(dataTableBean.getDraw());
        questionJsonBean.setData(questionForDataTableBeans);
        questionJsonBean.setRecordsTotal(totalCount);
        questionJsonBean.setRecordsFiltered(totalCount);
        return objectMapper.writeValueAsString(questionJsonBean);
    }

    @Override
    public String testsDataToJson(List<TestForDataTableBean> testsForDataTableBeans, DataTableBean dataTableBean, Integer totalCount) throws JsonProcessingException {
        TestJsonBean testJsonBean = new TestJsonBean();
        testJsonBean.setData(testsForDataTableBeans);
        testJsonBean.setDraw(dataTableBean.getDraw());
        testJsonBean.setRecordsFiltered(totalCount);
        testJsonBean.setRecordsTotal(totalCount);
        return objectMapper.writeValueAsString(testJsonBean);
    }

}
