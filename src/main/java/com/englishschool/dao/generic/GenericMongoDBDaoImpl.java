package com.englishschool.dao.generic;

import com.englishschool.datamodel.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static com.englishschool.datamodel.CommonConstants.*;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
public class GenericMongoDBDaoImpl<T> implements GenericDao<T> {

    @Autowired
    private MongoOperations mongoOperations;
    private Class<T> clazz;

    @Override
    public T findById(int id) {
        Query query = new Query(Criteria.where(ID).is(id));
        return mongoOperations.findOne(query, clazz);
    }

    @Override
    public List<T> findAll() {
        return mongoOperations.findAll(clazz);
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

}
