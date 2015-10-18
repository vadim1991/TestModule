package com.englishschool.service.question;

import com.englishschool.entity.Question;
import com.englishschool.entity.spring.DataTableBean;
import com.englishschool.entity.spring.QuestionForDatatableBean;
import com.englishschool.entity.spring.QuestionModelAttribute;
import com.englishschool.service.generic.GenericManager;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Vadym_Vlasenko on 9/30/2015.
 */
public interface IQuestionService<T> extends GenericManager<T> {

    Question getQuestionFromModel(QuestionModelAttribute questionModelAttribute);

    List<Question> findQuestionsByListId(List<String> ids);

    Page<Question> findAllWithPagination(DataTableBean dataTableBean);

    QuestionModelAttribute convertQuestionToModel(Question question);

    List<QuestionForDatatableBean> convertQuestionsForDataTableBean(List<Question> questions);

}
