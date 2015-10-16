package com.englishschool.controllers;

import com.englishschool.entity.Question;
import com.englishschool.entity.spring.QuestionModelAttribute;
import com.englishschool.service.question.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static com.englishschool.datamodel.CommonConstants.QUESTION;
import static com.englishschool.datamodel.CommonURLs.*;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
@Controller
public class CreateTestController {

    @Autowired
    private IQuestionService questionService;


}
