package com.englishschool.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 10/6/2015.
 */
public class UserAnswer implements Serializable {

    private int id;
    private String answerTest;
    private boolean userAnswer;
    private boolean rightAnswer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerTest() {
        return answerTest;
    }

    public void setAnswerTest(String answerTest) {
        this.answerTest = answerTest;
    }

    public boolean isUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(boolean userAnswer) {
        this.userAnswer = userAnswer;
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

        UserAnswer that = (UserAnswer) o;

        if (id != that.id) return false;
        if (userAnswer != that.userAnswer) return false;
        if (rightAnswer != that.rightAnswer) return false;
        return !(answerTest != null ? !answerTest.equals(that.answerTest) : that.answerTest != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (answerTest != null ? answerTest.hashCode() : 0);
        result = 31 * result + (userAnswer ? 1 : 0);
        result = 31 * result + (rightAnswer ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserAnswer{" +
                "id=" + id +
                ", answerTest='" + answerTest + '\'' +
                ", userAnswer=" + userAnswer +
                ", rightAnswer=" + rightAnswer +
                '}';
    }
}
