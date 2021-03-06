package com.englishschool.service.json;

import com.englishschool.entity.datatable.DataTableBean;
import com.englishschool.entity.datatable.QuestionForDataTableBean;
import com.englishschool.entity.datatable.TestForDataTableBean;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

/**
 * Created by Vadym_Vlasenko on 10/17/2015.
 */
public interface JsonService {

    String dataToJson(List<?> data, DataTableBean dataTableBean, Integer totalCount) throws JsonProcessingException;

}
