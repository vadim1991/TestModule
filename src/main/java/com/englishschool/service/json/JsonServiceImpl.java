package com.englishschool.service.json;

import com.englishschool.entity.datatable.DataTableBean;
import com.englishschool.entity.json.DataTableJsonBean;
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


    public String dataToJson(List<?> data, DataTableBean dataTableBean, Integer totalCount) throws JsonProcessingException {
        DataTableJsonBean jsonBean = new DataTableJsonBean();
        jsonBean.setData(data);
        jsonBean.setDraw(dataTableBean.getDraw());
        jsonBean.setRecordsFiltered(totalCount);
        jsonBean.setRecordsTotal(totalCount);
        return objectMapper.writeValueAsString(jsonBean);
    }

}
