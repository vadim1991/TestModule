package com.englishschool.entity.spring;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 10/5/2015.
 */
public class QuestionModelAttribute implements Serializable {

    private String title;
    private String content;
    private String category;
    private String questionType;
    private String explanation;
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

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
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

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "QuestionModelAttribute{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", category=" + category +
                ", questionType=" + questionType +
                ", explanation='" + explanation + '\'' +
                ", answers=" + answers +
                ", rightAnswers=" + rightAnswers +
                '}';
    }

}
