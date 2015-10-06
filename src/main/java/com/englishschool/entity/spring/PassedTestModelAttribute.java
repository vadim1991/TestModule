package com.englishschool.entity.spring;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 10/6/2015.
 */
public class PassedTestModelAttribute implements Serializable {

    private List<PassedQuestionModelAttribute> passedQuestions;

    public List<PassedQuestionModelAttribute> getPassedQuestions() {
        return passedQuestions;
    }

    public void setPassedQuestions(List<PassedQuestionModelAttribute> passedQuestions) {
        this.passedQuestions = passedQuestions;
    }

    @Override
    public String toString() {
        return "PassedTestModelAttribute{" +
                "passedQuestions=" + passedQuestions +
                '}';
    }
}
