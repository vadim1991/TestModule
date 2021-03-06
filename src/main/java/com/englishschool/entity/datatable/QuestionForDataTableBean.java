package com.englishschool.entity.datatable;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * Created by Vadym_Vlasenko on 10/17/2015.
 */
public class QuestionForDataTableBean implements Serializable {

    private String questionID;
    private String category;
    private String title;
    private String updateLink;

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdateLink() {
        return updateLink;
    }

    public void setUpdateLink(String updateLink) {
        this.updateLink = updateLink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionForDataTableBean that = (QuestionForDataTableBean) o;
        return Objects.equal(questionID, that.questionID) &&
                Objects.equal(category, that.category) &&
                Objects.equal(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(questionID, category, title);
    }

    @Override
    public String toString() {
        return "QuestionForDataTableBean{" +
                "questionID='" + questionID + '\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
