package com.englishschool.dao.question;

import com.englishschool.dao.generic.GenericMongoDBDaoImpl;
import com.englishschool.entity.Question;
import com.englishschool.entity.spring.DataTableBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.*;
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
    public Page<Question> findAllWithPagination(DataTableBean dataTableBean) {
        Integer start = dataTableBean.getStart();
        Integer length = dataTableBean.getLength();
        Integer pageNumber = start / length;
        Pageable pageable = new PageRequest(pageNumber, length);
        Query query = new Query();
        query.with(pageable);
        Sort sortObject = getSortObject(dataTableBean.getOrderColumn(), dataTableBean.getOrderParam());
        if (sortObject != null) {
            query.with(sortObject);
        }
        String searchWord = dataTableBean.getSearchWord();
        if (StringUtils.isNotBlank(searchWord)) {
            query.addCriteria(Criteria.where("title").regex(searchWord));
        }
        List<Question> questions = getMongoOperations().find(query, getClazz());
        long total = getMongoOperations().count(query, getClazz());
        Page<Question> questionPage = new PageImpl<>(questions, pageable, total);
        return questionPage;
    }

    private Sort getSortObject(String orderColumn, String orderParam) {
        Sort sort = null;
        Sort.Direction sortParam = Sort.Direction.ASC;
        if (orderParam != null && ("desc").equals(orderParam)) {
            sortParam = Sort.Direction.DESC;
        }
        if (orderColumn != null) {
            sort = new Sort(sortParam, orderColumn);
        }
        return sort;
    }

}
