package com.englishschool.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
public class PassedQuestion implements Serializable {

    private String id;
    private Question question;
    private List<UserAnswer> userAnswers;
    private boolean rightAnswer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public boolean isRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PassedQuestion that = (PassedQuestion) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        return !(userAnswers != null ? !userAnswers.equals(that.userAnswers) : that.userAnswers != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (userAnswers != null ? userAnswers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PassedQuestion{" +
                "id='" + id + '\'' +
                ", question=" + question +
                ", userAnswers=" + userAnswers +
                ", rightAnswer=" + rightAnswer +
                '}';
    }

}
