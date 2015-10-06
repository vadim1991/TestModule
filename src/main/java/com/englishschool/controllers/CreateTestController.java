package com.englishschool.controllers;

import com.englishschool.datamodel.CommonConstants;
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

import static com.englishschool.datamodel.CommonConstants.*;
import static com.englishschool.datamodel.CommonURLs.*;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
@Controller
public class CreateTestController {

    @Autowired
    private IQuestionService questionService;

    @RequestMapping(value = CREATE_QUESTION_URL, method = RequestMethod.GET)
    public ModelAndView addNewQuestion() {
        HashMap<String, Object> model = new HashMap<>();
        model.put(QUESTION, new QuestionModelAttribute());
        return new ModelAndView(CREATE_QUESTION_JSP, model);
    }

    @RequestMapping(value = CREATE_QUESTION_URL, method = RequestMethod.POST)
    public String addNewQuestion(@ModelAttribute(QUESTION) QuestionModelAttribute questionModelAttribute) {
        Question question = questionService.getQuestionFromModel(questionModelAttribute);
        questionService.save(question);
        //new ModelAndView(CREATE_QUESTION_JSP, SUCCESS_MESSAGE, SUCCESS_CREATE_QUESTION)
        return REDIRECT_CREATE_QUESTION_URL;
    }

}
