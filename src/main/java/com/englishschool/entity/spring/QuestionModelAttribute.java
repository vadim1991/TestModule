package com.englishschool.entity.spring;

import com.englishschool.entity.QuestionType;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 10/5/2015.
 */
public class QuestionModelAttribute implements Serializable {

    private String title;
    private String content;
    private QuestionType questionType;
    private List<String> answers;
    private List<Integer> rightAnswers;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public List<Integer> getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(List<Integer> rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    @Override
    public String toString() {
        return "QuestionModelAttribute{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", questionType=" + questionType +
                ", answers=" + answers +
                ", rightAnswers=" + rightAnswers +
                '}';
    }

}
