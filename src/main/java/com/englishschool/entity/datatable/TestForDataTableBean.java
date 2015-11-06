package com.englishschool.entity.datatable;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * Created by Vadym_Vlasenko on 10/18/2015.
 */
public class TestForDataTableBean implements Serializable {

    private String id;
    private String testTitle;
    private String creationDate;
    private Integer timeOfTest;
    private String updateLink;
    private int questionAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getTimeOfTest() {
        return timeOfTest;
    }

    public void setTimeOfTest(Integer timeOfTest) {
        this.timeOfTest = timeOfTest;
    }

    public String getUpdateLink() {
        return updateLink;
    }

    public void setUpdateLink(String updateLink) {
        this.updateLink = updateLink;
    }

    public int getQuestionAmount() {
        return questionAmount;
    }

    public void setQuestionAmount(int questionAmount) {
        this.questionAmount = questionAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestForDataTableBean that = (TestForDataTableBean) o;
        return Objects.equal(questionAmount, that.questionAmount) &&
                Objects.equal(id, that.id) &&
                Objects.equal(testTitle, that.testTitle) &&
                Objects.equal(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, testTitle, creationDate, questionAmount);
    }

    @Override
    public String toString() {
        return "TestForDataTableBean{" +
                "id='" + id + '\'' +
                ", testTitle='" + testTitle + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", timeOfTest=" + timeOfTest +
                ", updateLink='" + updateLink + '\'' +
                ", questionAmount=" + questionAmount +
                '}';
    }
}
