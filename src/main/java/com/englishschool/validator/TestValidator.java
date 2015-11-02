package com.englishschool.validator;

import com.englishschool.entity.Test;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by Vadym_Vlasenko on 11/2/2015.
 */
@Component
public class TestValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Test.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Test test = (Test) object;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "testTitle", "NotEmpty.test.title");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "timeOfTest", "NotEmpty.test.time");
        List<String> questionIds = test.getQuestionIds();
        checkTestTime(test.getTimeOfTest(), errors);
        checkQuestionIDs(questionIds, errors);
    }

    private void checkTestTime(int timeOfTest, Errors errors) {
        if (timeOfTest <= 0) {
            errors.rejectValue("timeOfTest", "Invalid.test.time");
        }
    }

    private void checkQuestionIDs(List<String> questionIDs, Errors errors) {
        if (questionIDs == null || questionIDs.isEmpty()) {
            errors.rejectValue("questionIds", "Invalid.test.question");
        }
    }

}
