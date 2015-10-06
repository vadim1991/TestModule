package com.englishschool.dao.generic;

import java.util.List;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
public interface GenericDao<T> {

    public T findById(String id);

    public List<T> findAll();

    public void update(T entity);

    public void save(T entity);

    public void delete(T entity);

}
