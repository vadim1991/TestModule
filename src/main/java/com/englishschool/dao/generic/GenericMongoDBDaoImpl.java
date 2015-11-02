package com.englishschool.dao.generic;

import com.englishschool.entity.datatable.DataTableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static com.englishschool.datamodel.CommonConstants.DESC;
import static com.englishschool.datamodel.CommonConstants.ID;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
public class GenericMongoDBDaoImpl<T> implements GenericDao<T> {

    @Autowired
    private MongoOperations mongoOperations;
    private Class<T> clazz;

    @Override
    public T findById(String id) {
        Query query = new Query(Criteria.where(ID).is(id));
        return mongoOperations.findOne(query, getClazz());
    }

    @Override
    public List<T> findAll() {
        PageRequest pageRequest = new PageRequest(0, 10);
        return mongoOperations.findAll(getClazz());
    }

    @Override
    public void update(T entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(T entity) {
        mongoOperations.save(entity);
    }

    @Override
    public void delete(T entity) {
        mongoOperations.remove(entity);
    }

    @Override
    public boolean deleteByID(String id) {
        Query query = new Query(Criteria.where(ID).is(id));
        return deleteByQuery(query);
    }

    @Override
    public boolean deleteByIDs(List<String> IDs) {
        Query query = new Query(Criteria.where(ID).in(IDs));
        return deleteByQuery(query);
    }

    @Override
    public Page<T> findAllWithPagination(DataTableBean dataTableBean, String... fields) {
        Query query = new Query();
        Integer start = dataTableBean.getStart();
        Integer length = dataTableBean.getLength();
        String searchWord = dataTableBean.getSearchWord();
        Integer pageNumber = start / length;
        Pageable pageable = new PageRequest(pageNumber, length);
        query.with(pageable);
        addSortObject(dataTableBean.getOrderColumn(), dataTableBean.getOrderParam(), query);
        addSearchCriteria(searchWord, query, fields);
        List<T> questions = getMongoOperations().find(query, getClazz());
        long total = getMongoOperations().count(query, getClazz());
        return new PageImpl<>(questions, pageable, total);
    }

    private void addSortObject(String orderColumn, String orderParam, Query query) {
        if (isNotBlank(orderColumn)) {
            Sort.Direction sortParam = DESC.equals(orderParam) ? Sort.Direction.DESC : Sort.Direction.ASC;
            Sort sort = new Sort(sortParam, orderColumn);
            query.with(sort);
        }
    }

    private void addSearchCriteria(String searchWord, Query query, String... fields) {
        if (isNotBlank(searchWord) && fields != null) {
            Criteria commonCriteria = new Criteria();
            int length = fields.length;
            Criteria[] criteriaArray = new Criteria[length];
            for (int i = 0; i < length; i++) {
                String field = fields[i];
                Criteria criteria = Criteria.where(field).regex(searchWord);
                criteriaArray[i] = criteria;
            }
            commonCriteria.orOperator(criteriaArray);
            query.addCriteria(commonCriteria);
        }
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public MongoOperations getMongoOperations() {
        return mongoOperations;
    }

    public void setMongoOperations(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    private boolean deleteByQuery(Query query) {
        List<T> allAndRemove = getMongoOperations().findAllAndRemove(query, getClazz());
        return allAndRemove == null ? false : true;
    }

}
