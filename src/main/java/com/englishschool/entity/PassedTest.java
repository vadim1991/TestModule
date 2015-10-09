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
    private double result;
    private List<PassedQuestion> passedQuestions;
    private String startTest;
    private String endTest;
    private DateTime duringTimeTest;

    public PassedTest() {
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

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public List<PassedQuestion> getPassedQuestions() {
        return passedQuestions;
    }

    public void setPassedQuestions(List<PassedQuestion> passedQuestions) {
        this.passedQuestions = passedQuestions;
    }

    public String getStartTest() {
        return startTest;
    }

    public void setStartTest(String startTest) {
        this.startTest = startTest;
    }

    public String getEndTest() {
        return endTest;
    }

    public void setEndTest(String endTest) {
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

        if (Double.compare(that.result, result) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (testId != null ? !testId.equals(that.testId) : that.testId != null) return false;
        if (passedQuestions != null ? !passedQuestions.equals(that.passedQuestions) : that.passedQuestions != null)
            return false;
        if (startTest != null ? !startTest.equals(that.startTest) : that.startTest != null) return false;
        if (endTest != null ? !endTest.equals(that.endTest) : that.endTest != null) return false;
        return !(duringTimeTest != null ? !duringTimeTest.equals(that.duringTimeTest) : that.duringTimeTest != null);

    }

    @Override
    public int hashCode() {
        int result1;
        long temp;
        result1 = id != null ? id.hashCode() : 0;
        result1 = 31 * result1 + (testId != null ? testId.hashCode() : 0);
        temp = Double.doubleToLongBits(result);
        result1 = 31 * result1 + (int) (temp ^ (temp >>> 32));
        result1 = 31 * result1 + (passedQuestions != null ? passedQuestions.hashCode() : 0);
        result1 = 31 * result1 + (startTest != null ? startTest.hashCode() : 0);
        result1 = 31 * result1 + (endTest != null ? endTest.hashCode() : 0);
        result1 = 31 * result1 + (duringTimeTest != null ? duringTimeTest.hashCode() : 0);
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
