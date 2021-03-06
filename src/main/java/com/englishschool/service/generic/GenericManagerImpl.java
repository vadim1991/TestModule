package com.englishschool.service.generic;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.entity.datatable.DataTableBean;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Vadym_Vlasenko on 9/30/2015.
 */
public class GenericManagerImpl<T, D extends GenericDao<T>> implements
        GenericManager<T> {

    protected D dao;

    public void setDao(GenericDao dao) {
        this.dao = (D) dao;
    }

    public T findById(String id) {
        return dao.findById(id);
    }

    public List<T> findAll() {
        return dao.findAll();
    }

    public void save(T entity) {
        dao.save(entity);
    }

    public void update(T entity) {
        dao.update(entity);
    }

    public void delete(T entity) {
        dao.delete(entity);
    }

    @Override
    public boolean deleteByID(String id) {
        return dao.deleteByID(id);
    }

    @Override
    public List<T> deleteByIDs(List<String> IDs) {
        return dao.deleteByIDs(IDs);
    }

    @Override
    public Page<T> findAllWithPagination(DataTableBean dataTableBean, String... fields) {
        return dao.findAllWithPagination(dataTableBean, fields);
    }

}
