package com.englishschool.dao.question;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.entity.Question;

import java.util.List;

/**
 * Created by Vadym_Vlasenko on 9/30/2015.
 */
public interface IQuestionDao<T> extends GenericDao<T> {

    List<Question> findQuestionsByListId(List<String> ids);

}
