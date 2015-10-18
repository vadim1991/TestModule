package com.englishschool.dao.question;

import com.englishschool.dao.generic.GenericMongoDBDaoImpl;
import com.englishschool.entity.Question;
import com.englishschool.entity.spring.DataTableBean;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.englishschool.datamodel.CommonConstants.*;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

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
    public Page<Question> findAllWithPagination(DataTableBean dataTableBean) {
        Query query = new Query();
        Integer start = dataTableBean.getStart();
        Integer length = dataTableBean.getLength();
        String searchWord = dataTableBean.getSearchWord();
        Integer pageNumber = start / length;
        Pageable pageable = new PageRequest(pageNumber, length);
        query.with(pageable);
        addSortObject(dataTableBean.getOrderColumn(), dataTableBean.getOrderParam(), query);
        addSearchCriteria(searchWord, query);
        List<Question> questions = getMongoOperations().find(query, getClazz());
        long total = getMongoOperations().count(query, getClazz());
        Page<Question> questionPage = new PageImpl<>(questions, pageable, total);
        return questionPage;
    }

    private void addSortObject(String orderColumn, String orderParam, Query query) {
        if (isNotBlank(orderColumn)) {
            Sort.Direction sortParam = DESC.equals(orderParam) ? Sort.Direction.DESC : Sort.Direction.ASC;
            Sort sort = new Sort(sortParam, orderColumn);
            query.with(sort);
        }
    }

    private void addSearchCriteria(String searchWord, Query query) {
        if (isNotBlank(searchWord)) {
            Criteria titleCriteria = Criteria.where(TITLE).regex(searchWord);
            Criteria categoryCriteria = Criteria.where(CATEGORY).regex(searchWord);
            Criteria commonCriteria = new Criteria().orOperator(titleCriteria, categoryCriteria);
            query.addCriteria(commonCriteria);
        }
    }

}
