package com.englishschool.entity.json;

import com.englishschool.entity.datatable.TestForDataTableBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Vadym_Vlasenko on 10/18/2015.
 */
public class TestJsonBean implements Serializable {

    private Integer draw;
    private Integer recordsTotal;
    private Integer recordsFiltered;
    private List<TestForDataTableBean> data;

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

    public List<TestForDataTableBean> getData() {
        return data;
    }

    public void setData(List<TestForDataTableBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TestJsonBean{" +
                "draw=" + draw +
                ", recordsTotal=" + recordsTotal +
                ", recordsFiltered=" + recordsFiltered +
                ", data=" + data +
                '}';
    }
}
