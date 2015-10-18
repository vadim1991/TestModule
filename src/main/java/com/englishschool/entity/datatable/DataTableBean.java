package com.englishschool.entity.datatable;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * Created by Vadym_Vlasenko on 10/17/2015.
 */
public class DataTableBean implements Serializable {

    private Integer start;
    private Integer length;
    private Integer draw;
    private String searchWord;
    private String orderColumn;
    private String orderParam;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderParam() {
        return orderParam;
    }

    public void setOrderParam(String orderParam) {
        this.orderParam = orderParam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataTableBean that = (DataTableBean) o;
        return Objects.equal(start, that.start) &&
                Objects.equal(length, that.length) &&
                Objects.equal(searchWord, that.searchWord) &&
                Objects.equal(orderColumn, that.orderColumn) &&
                Objects.equal(orderParam, that.orderParam);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(start, length, searchWord, orderColumn, orderParam);
    }

    @Override
    public String toString() {
        return "DataTableBean{" +
                "start=" + start +
                ", length=" + length +
                ", draw=" + draw +
                ", serchWord='" + searchWord + '\'' +
                ", orderColumn='" + orderColumn + '\'' +
                ", orderParam='" + orderParam + '\'' +
                '}';
    }

}
