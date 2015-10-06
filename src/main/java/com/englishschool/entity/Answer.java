package com.englishschool.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
@Document
public class Answer implements Serializable {

    private int id;
    private String answerText;
    private boolean rightAnswer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
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

        Answer answer = (Answer) o;

        if (id != answer.id) return false;
        if (rightAnswer != answer.rightAnswer) return false;
        return !(answerText != null ? !answerText.equals(answer.answerText) : answer.answerText != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (answerText != null ? answerText.hashCode() : 0);
        result = 31 * result + (rightAnswer ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answerText='" + answerText + '\'' +
                ", rightAnswer=" + rightAnswer +
                '}';
    }

}
