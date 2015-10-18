package com.englishschool.dao.generic;

import java.util.List;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
public interface GenericDao<T> {

    T findById(String id);

    List<T> findAll();

    void update(T entity);

    void save(T entity);

    void delete(T entity);

    boolean deleteByID(String id);

}
