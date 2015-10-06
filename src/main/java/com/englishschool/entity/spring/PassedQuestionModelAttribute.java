package com.englishschool.entity.spring;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 10/6/2015.
 */
public class PassedQuestionModelAttribute implements Serializable {

    private String id;
    private List<Integer> userAnswers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<Integer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    @Override
    public String toString() {
        return "PassedQuestionModelAttribute{" +
                "id='" + id + '\'' +
                ", userAnswers=" + userAnswers +
                '}';
    }
}
