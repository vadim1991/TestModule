package com.englishschool.entity;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
public enum QuestionType {

    RADIO("radio"),CHECKBOX("checkbox"),REGEXP("regexp");

    private String value;

    QuestionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
