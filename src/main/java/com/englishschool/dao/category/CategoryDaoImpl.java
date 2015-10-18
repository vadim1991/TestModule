package com.englishschool.dao.category;

import com.englishschool.dao.generic.GenericMongoDBDaoImpl;
import com.englishschool.entity.Category;
import org.springframework.stereotype.Repository;

/**
 * Created by Vadym_Vlasenko on 10/18/2015.
 */
@Repository("categoryDao")
public class CategoryDaoImpl extends GenericMongoDBDaoImpl<Category> implements ICategoryDao<Category> {

    public CategoryDaoImpl() {
        setClazz(Category.class);
    }
}
