package com.englishschool.service.question;

import com.englishschool.entity.Question;
import com.englishschool.entity.spring.QuestionModelAttribute;
import com.englishschool.service.generic.GenericManager;

import java.util.List;

/**
 * Created by Vadym_Vlasenko on 9/30/2015.
 */
public interface IQuestionService<T> extends GenericManager<T> {

    Question getQuestionFromModel(QuestionModelAttribute questionModelAttribute);

    List<Question> findQuestionsByListId(List<String> ids);

}
