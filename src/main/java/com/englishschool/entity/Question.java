package com.englishschool.entity;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
@Document
public class Question implements Serializable {

    @Id
    private String id;
    private String title;
    private String questionContent;
    private String explanation;
    private List<Answer> answers;
    private QuestionType questionType;

    public Question() {
        answers = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equal(id, question.id) &&
                Objects.equal(title, question.title) &&
                Objects.equal(questionContent, question.questionContent);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, title, questionContent);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", questionContent='" + questionContent + '\'' +
                ", answers=" + answers +
                ", questionType=" + questionType +
                '}';
    }

}
