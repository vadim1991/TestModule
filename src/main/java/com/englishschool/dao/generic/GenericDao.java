package com.englishschool.dao.generic;

import com.englishschool.entity.datatable.DataTableBean;
import org.springframework.data.domain.Page;

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

    boolean deleteByIDs(List<String> IDs);

    Page<T> findAllWithPagination(DataTableBean dataTableBean, String... fields);

}
