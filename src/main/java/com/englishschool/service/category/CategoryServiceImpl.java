package com.englishschool.service.category;

import com.englishschool.dao.category.CategoryDaoImpl;
import com.englishschool.dao.generic.GenericDao;
import com.englishschool.entity.Category;
import com.englishschool.service.generic.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.englishschool.datamodel.CacheConstants.CATEGORIES;
import static com.englishschool.datamodel.CommonConstants.CATEGORY_DAO;

/**
 * Created by Vadym_Vlasenko on 10/18/2015.
 */
@Service
@EnableCaching
public class CategoryServiceImpl extends GenericManagerImpl<Category, CategoryDaoImpl> implements ICategoryService<Category> {

    @Autowired
    @Qualifier(CATEGORY_DAO)
    @Override
    public void setDao(GenericDao dao) {
        super.setDao(dao);
    }

    @CacheEvict(value = CATEGORIES, allEntries = true)
    @Override
    public void save(Category entity) {
        super.save(entity);
    }

    @Cacheable(value = CATEGORIES)
    @Override
    public List<Category> findAll() {
        return super.findAll();
    }
}
