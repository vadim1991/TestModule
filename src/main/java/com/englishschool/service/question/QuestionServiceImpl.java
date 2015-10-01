package com.englishschool.service.question;

import com.englishschool.dao.generic.GenericDao;
import com.englishschool.service.generic.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Vadym_Vlasenko on 9/30/2015.
 */
@Service
public class QuestionServiceImpl extends GenericManagerImpl implements IQuestionService {

    @Autowired
    @Qualifier("questionDao")
    @Override
    public void setDao(GenericDao dao) {
        super.setDao(dao);
    }
}
