package com.englishschool.dao.question;

import com.englishschool.dao.generic.GenericMongoDBDaoImpl;
import com.englishschool.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.englishschool.datamodel.CommonConstants.ID;

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

    @Override
    public Page<Question> findAllWithPagination(int pageNumber, int countOnPage) {
        Pageable pageable = new PageRequest(pageNumber, countOnPage);
        Query query = new Query();
        query.with(pageable);
        List<Question> questions = getMongoOperations().find(query, getClazz());
        long total = getMongoOperations().count(query, getClazz());
        System.out.println(total);
        Page<Question> questionPage = new PageImpl<Question>(questions, pageable, total);
        return questionPage;
    }
}
