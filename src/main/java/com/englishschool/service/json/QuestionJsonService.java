package com.englishschool.service.json;

import com.englishschool.entity.spring.DataTableBean;
import com.englishschool.entity.spring.QuestionForDatatableBean;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

/**
 * Created by Vadym_Vlasenko on 10/17/2015.
 */
public interface QuestionJsonService {

    String getQuestionsDataJson(List<QuestionForDatatableBean> questionForDatatableBeans, DataTableBean dataTableBean, Integer totalCount) throws JsonProcessingException;

}
