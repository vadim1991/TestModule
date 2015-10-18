package com.englishschool.service.json;

import com.englishschool.entity.Question;
import com.englishschool.entity.spring.DataTableBean;
import com.englishschool.entity.spring.QuestionForDatatableBean;
import com.englishschool.entity.spring.QuestionJsonBean;
import com.englishschool.service.question.IQuestionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vadym_Vlasenko on 10/17/2015.
 */
@Service("jsonService")
public class QuestionJsonServiceImpl implements QuestionJsonService {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String getQuestionsDataJson(List<QuestionForDatatableBean> questionForDatatableBeans, DataTableBean dataTableBean, Integer totalCount) throws JsonProcessingException {
        QuestionJsonBean questionJsonBean = new QuestionJsonBean();
        questionJsonBean.setDraw(dataTableBean.getDraw());
        questionJsonBean.setData(questionForDatatableBeans);
        questionJsonBean.setRecordsTotal(totalCount);
        questionJsonBean.setRecordsFiltered(totalCount);
        String json = objectMapper.writeValueAsString(questionJsonBean);
        return json;
    }

}
