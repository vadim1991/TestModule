package com.englishschool.validator;

import com.englishschool.entity.spring.QuestionModelAttribute;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadym_Vlasenko on 10/17/2015.
 */
@Component
public class QuestionModelValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return QuestionModelAttribute.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        QuestionModelAttribute questionModelAttribute = (QuestionModelAttribute) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.question.title");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "NotEmpty.question.content");
        List<String> answers = questionModelAttribute.getAnswers();
        List<String> validAnswers = getValidAnswers(answers);
        questionModelAttribute.setAnswers(validAnswers);
        List<Integer> rightAnswers = questionModelAttribute.getRightAnswers();
        if (rightAnswers == null || rightAnswers.size() < 1) {
            errors.rejectValue("rightAnswers", "NotEmpty.question.rightAnswer");
        }
    }

    private List<String> getValidAnswers(List<String> answers) {
        List<String> validAnswers = new ArrayList<>();
        if (answers != null) {
            for (String answer : answers) {
                if (StringUtils.isNotBlank(answer)) {
                    validAnswers.add(answer);
                }
            }
        }
        return validAnswers;
    }

}
