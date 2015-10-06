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
public class Test implements Serializable {

    @Id
    private String id;
    private DateTime creationDate;
    private List<String> questionIds;
    private int timeOfTest;
    private String testTitle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<String> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<String> questionIds) {
        this.questionIds = questionIds;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public int getTimeOfTest() {
        return timeOfTest;
    }

    public void setTimeOfTest(int timeOfTest) {
        this.timeOfTest = timeOfTest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (id != null ? !id.equals(test.id) : test.id != null) return false;
        if (creationDate != null ? !creationDate.equals(test.creationDate) : test.creationDate != null) return false;
        if (questionIds != null ? !questionIds.equals(test.questionIds) : test.questionIds != null) return false;
        return !(testTitle != null ? !testTitle.equals(test.testTitle) : test.testTitle != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (questionIds != null ? questionIds.hashCode() : 0);
        result = 31 * result + (testTitle != null ? testTitle.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id='" + id + '\'' +
                ", creationDate=" + creationDate +
                ", questionIds=" + questionIds +
                ", timeOfTest=" + timeOfTest +
                ", testTitle='" + testTitle + '\'' +
                '}';
    }

}
