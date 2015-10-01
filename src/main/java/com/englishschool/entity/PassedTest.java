package com.englishschool.entity;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
@Document
public class PassedTest implements Serializable {

    @Id
    private String id;
    private String testId;
    private int result;
    private List<PassedQuestion> passedQuestions;
    private DateTime startTest;
    private DateTime endTest;
    private DateTime duringTimeTest;

    public PassedTest() {
        startTest = new DateTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<PassedQuestion> getPassedQuestions() {
        return passedQuestions;
    }

    public void setPassedQuestions(List<PassedQuestion> passedQuestions) {
        this.passedQuestions = passedQuestions;
    }

    public DateTime getStartTest() {
        return startTest;
    }

    public void setStartTest(DateTime startTest) {
        this.startTest = startTest;
    }

    public DateTime getEndTest() {
        return endTest;
    }

    public void setEndTest(DateTime endTest) {
        this.endTest = endTest;
    }

    public DateTime getDuringTimeTest() {
        return duringTimeTest;
    }

    public void setDuringTimeTest(DateTime duringTimeTest) {
        this.duringTimeTest = duringTimeTest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PassedTest that = (PassedTest) o;

        if (result != that.result) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (testId != null ? !testId.equals(that.testId) : that.testId != null) return false;
        if (startTest != null ? !startTest.equals(that.startTest) : that.startTest != null) return false;
        return !(endTest != null ? !endTest.equals(that.endTest) : that.endTest != null);

    }

    @Override
    public int hashCode() {
        int result1 = id != null ? id.hashCode() : 0;
        result1 = 31 * result1 + (testId != null ? testId.hashCode() : 0);
        result1 = 31 * result1 + result;
        result1 = 31 * result1 + (startTest != null ? startTest.hashCode() : 0);
        result1 = 31 * result1 + (endTest != null ? endTest.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "PassedTest{" +
                "id='" + id + '\'' +
                ", testId='" + testId + '\'' +
                ", result=" + result +
                ", passedQuestions=" + passedQuestions +
                ", startTest=" + startTest +
                ", endTest=" + endTest +
                ", duringTimeTest=" + duringTimeTest +
                '}';
    }

}
