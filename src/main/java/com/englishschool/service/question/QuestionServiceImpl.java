package com.englishschool.service.question;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.dao.question.QuestionDaoImpl;
import com.englishschool.entity.Answer;
import com.englishschool.entity.Question;
import com.englishschool.entity.spring.QuestionModelAttribute;
import com.englishschool.service.generic.GenericManagerImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadym_Vlasenko on 9/30/2015.
 */
@Service
public class QuestionServiceImpl extends GenericManagerImpl<Question, QuestionDaoImpl> implements IQuestionService<Question> {

    @Autowired
    @Qualifier("questionDao")
    @Override
    public void setDao(GenericDao dao) {
        super.setDao(dao);
    }

    @Override
    public Question getQuestionFromModel(QuestionModelAttribute questionModelAttribute) {
        Question question = new Question();
        question.setTitle(questionModelAttribute.getTitle());
        question.setQuestionType(questionModelAttribute.getQuestionType());
        question.setQuestionContent(questionModelAttribute.getContent());
        setAnswersToQuestion(question, questionModelAttribute);
        return question;
    }

    @Override
    public List<Question> findQuestionsByListId(List<String> ids) {
        return dao.findQuestionsByListId(ids);
    }

    @Override
    public Page<Question> findAllWithPagination(int pageNumber, int countOnPage) {
        return dao.findAllWithPagination(pageNumber, countOnPage);
    }

    private void setAnswersToQuestion(Question question, QuestionModelAttribute questionModelAttribute) {
        List<Answer> answers = new ArrayList<>();
        List<String> answersText = questionModelAttribute.getAnswers();
        List<Integer> rightAnswers = questionModelAttribute.getRightAnswers();
        for (int i = 0; i < answersText.size(); i++) {
            String answerText = answersText.get(i);
            if (StringUtils.isNotBlank(answerText)) {
                Answer answer = new Answer();
                answer.setId(i);
                answer.setAnswerText(answerText);
                answer.setRightAnswer(rightAnswers.contains(i));
                answers.add(answer);
            }
        }
        question.setAnswers(answers);
    }
}
