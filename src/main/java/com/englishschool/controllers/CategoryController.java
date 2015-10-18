package com.englishschool.controllers;

import com.englishschool.entity.Category;
import com.englishschool.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import static com.englishschool.datamodel.CommonConstants.MSG_ATTRIBUTE;
import static com.englishschool.datamodel.CommonMessages.SUCCESS_CREATE_CATEGORY;
import static com.englishschool.datamodel.CommonURLs.CATEGORY_CREATE_URL;
import static com.englishschool.datamodel.CommonURLs.REDIRECT_CREATE_QUESTION_URL;

/**
 * Created by Vadym_Vlasenko on 10/18/2015.
 */
@Controller
public class CategoryController {

    @Autowired
    private ICategoryService<Category> categoryService;

    @RequestMapping(value = CATEGORY_CREATE_URL, method = RequestMethod.POST)
    public String createCategory(Category category, final RedirectAttributesModelMap redirectAttributesModelMap) {
        categoryService.save(category);
        redirectAttributesModelMap.addFlashAttribute(MSG_ATTRIBUTE, SUCCESS_CREATE_CATEGORY);
        return REDIRECT_CREATE_QUESTION_URL;
    }

}
