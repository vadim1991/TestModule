package com.englishschool.entity;

import com.google.common.base.Objects;
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
    private String creationDate;
    private List<String> questionIds;
    private int timeOfTest;
    private String testTitle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public List<String> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<String> questionIds) {
        this.questionIds = questionIds;
    }

    public int getTimeOfTest() {
        return timeOfTest;
    }

    public void setTimeOfTest(int timeOfTest) {
        this.timeOfTest = timeOfTest;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equal(timeOfTest, test.timeOfTest) &&
                Objects.equal(id, test.id) &&
                Objects.equal(creationDate, test.creationDate) &&
                Objects.equal(questionIds, test.questionIds) &&
                Objects.equal(testTitle, test.testTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, creationDate, questionIds, timeOfTest, testTitle);
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
