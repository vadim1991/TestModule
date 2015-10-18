package com.englishschool.service.question;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.dao.question.QuestionDaoImpl;
import com.englishschool.datamodel.CacheConstants;
import com.englishschool.datamodel.CommonConstants;
import com.englishschool.entity.Answer;
import com.englishschool.entity.Question;
import com.englishschool.entity.spring.DataTableBean;
import com.englishschool.entity.spring.QuestionForDataTableBean;
import com.englishschool.entity.spring.QuestionModelAttribute;
import com.englishschool.service.generic.GenericManagerImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.englishschool.datamodel.CommonConstants.DELETE_LINK_FORMAT;
import static com.englishschool.datamodel.CommonConstants.UPDATE_LINK_FORMAT;

/**
 * Created by Vadym_Vlasenko on 9/30/2015.
 */
@Service
@EnableCaching
public class QuestionServiceImpl extends GenericManagerImpl<Question, QuestionDaoImpl> implements IQuestionService<Question> {

    @Autowired
    @Qualifier(CommonConstants.QUESTION_DAO)
    @Override
    public void setDao(GenericDao dao) {
        super.setDao(dao);
    }

    @CacheEvict(value = CacheConstants.QUESTION_PAGES, allEntries = true)
    @Override
    public void save(Question entity) {
        super.save(entity);
    }

    @CacheEvict(value = CacheConstants.QUESTION_PAGES, allEntries = true)
    @Override
    public boolean deleteByID(String id) {
        return super.deleteByID(id);
    }

    @Override
    public Question getQuestionFromModel(QuestionModelAttribute questionModelAttribute) {
        Question question = new Question();
        question.setTitle(questionModelAttribute.getTitle());
        question.setQuestionType(questionModelAttribute.getQuestionType());
        question.setQuestionContent(questionModelAttribute.getContent());
        question.setCategory(questionModelAttribute.getCategory());
        setAnswersToQuestion(question, questionModelAttribute);
        return question;
    }

    @Override
    public List<Question> findQuestionsByListId(List<String> ids) {
        return dao.findQuestionsByListId(ids);
    }

    @Cacheable(value = CacheConstants.QUESTION_PAGES)
    @Override
    public Page<Question> findAllWithPagination(DataTableBean dataTableBean) {
        return dao.findAllWithPagination(dataTableBean);
    }

    @Override
    public QuestionModelAttribute convertQuestionToModel(Question question) {
        List<String> answers = new ArrayList<>();
        List<Integer> rightAnswers = new ArrayList<>();
        List<Answer> fullAnswers = question.getAnswers();
        QuestionModelAttribute questionModelAttribute = new QuestionModelAttribute();
        questionModelAttribute.setTitle(question.getTitle());
        questionModelAttribute.setContent(question.getQuestionContent());
        questionModelAttribute.setExplanation(question.getExplanation());
        questionModelAttribute.setQuestionType(question.getQuestionType());
        for (int i = 0; i < fullAnswers.size(); i++) {
            Answer answer = fullAnswers.get(i);
            answers.add(answer.getAnswerText());
            if (answer.isRightAnswer()) {
                rightAnswers.add(i);
            }
        }
        questionModelAttribute.setAnswers(answers);
        questionModelAttribute.setRightAnswers(rightAnswers);
        return questionModelAttribute;
    }

    @Override
    public List<QuestionForDataTableBean> convertQuestionsForDataTableBean(List<Question> questions) {
        List<QuestionForDataTableBean> questionForDataTableBeans = null;
        if (questions != null) {
            questionForDataTableBeans = new ArrayList<>();
            for (Question question : questions) {
                QuestionForDataTableBean dataTableBean = new QuestionForDataTableBean();
                dataTableBean.setQuestionID(question.getId());
                dataTableBean.setTitle(question.getTitle());
                dataTableBean.setCategory(question.getCategory());
                dataTableBean.setUpdateLink(String.format(UPDATE_LINK_FORMAT, question.getId()));
                dataTableBean.setDeleteLink(String.format(DELETE_LINK_FORMAT, question.getId()));
                questionForDataTableBeans.add(dataTableBean);
            }
        }
        return questionForDataTableBeans;
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
