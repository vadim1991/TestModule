package com.englishschool.dao.question;

import com.englishschool.dao.generic.GenericMongoDBDaoImpl;
import com.englishschool.entity.Question;
import org.springframework.stereotype.Repository;

/**
 * Created by Vadym_Vlasenko on 9/30/2015.
 */
@Repository("questionDao")
public class QuestionDaoImpl extends GenericMongoDBDaoImpl<Question> implements IQuestionDao<Question> {

    public QuestionDaoImpl() {
        setClazz(Question.class);
    }
}
