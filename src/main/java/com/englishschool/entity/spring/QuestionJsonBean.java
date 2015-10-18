package com.englishschool.entity.spring;

import com.englishschool.entity.Question;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Vadym_Vlasenko on 10/17/2015.
 */
public class QuestionJsonBean implements Serializable {

    private Integer draw;
    private Integer recordsTotal;
    private Integer recordsFiltered;
    private List<QuestionForDatatableBean> data;

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<QuestionForDatatableBean> getData() {
        return data;
    }

    public void setData(List<QuestionForDatatableBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "QuestionJsonBean{" +
                "draw=" + draw +
                ", recordsTotal=" + recordsTotal +
                ", recordsFiltered=" + recordsFiltered +
                ", data=" + data +
                '}';
    }

}
