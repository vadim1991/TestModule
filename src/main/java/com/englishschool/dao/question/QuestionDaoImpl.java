package com.englishschool.dao.question;

import com.englishschool.dao.generic.GenericMongoDBDaoImpl;
import com.englishschool.datamodel.CommonConstants;
import com.englishschool.entity.Question;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.englishschool.datamodel.CommonConstants.*;

/**
 * Created by Vadym_Vlasenko on 9/30/2015.
 */
@Repository("questionDao")
public class QuestionDaoImpl extends GenericMongoDBDaoImpl<Question> implements IQuestionDao<Question> {

    public QuestionDaoImpl() {
        setClazz(Question.class);
    }

    @Override
    public List<Question> findQuestionsByListId(List<String> ids) {
        Query query = new Query(Criteria.where(ID).in(ids));
        return getMongoOperations().find(query, Question.class);
    }
}
