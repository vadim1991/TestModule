package com.englishschool.entity.spring;

import java.io.Serializable;

/**
 * Created by Vadym_Vlasenko on 10/17/2015.
 */
public class QuestionForDatatableBean implements Serializable {

    private String questionID;
    private String title;
    private String questionType;
    private String updateLink;
    private String deleteLink;

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

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getUpdateLink() {
        return updateLink;
    }

    public void setUpdateLink(String updateLink) {
        this.updateLink = updateLink;
    }

    public String getDeleteLink() {
        return deleteLink;
    }

    public void setDeleteLink(String deleteLink) {
        this.deleteLink = deleteLink;
    }

}
