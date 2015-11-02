package com.englishschool.service.generic;

import com.englishschool.entity.datatable.DataTableBean;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Vadym_Vlasenko on 9/30/2015.
 */
public interface GenericManager<T> {

    T findById(String id);

    List<T> findAll();

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    boolean deleteByID(String id);

    Page<T> findAllWithPagination(DataTableBean dataTableBean, String... fields);

}
