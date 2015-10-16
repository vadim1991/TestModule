package com.englishschool.controllers;

import com.englishschool.datamodel.CommonConstants;
import com.englishschool.datamodel.CommonMessages;
import com.englishschool.entity.Question;
import com.englishschool.entity.spring.QuestionModelAttribute;
import com.englishschool.service.question.IQuestionService;
import com.google.common.base.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

import static com.englishschool.datamodel.CommonConstants.QUESTION;
import static com.englishschool.datamodel.CommonConstants.SUCCESS_MESSAGE;
import static com.englishschool.datamodel.CommonMessages.*;
import static com.englishschool.datamodel.CommonURLs.*;

/**
 * Created by Vadym_Vlasenko on 10/16/2015.
 */
@Controller
public class QuestionController {

    public static final String QUESTION_PAGE_OBJECT = "questionPage";
    public static final String QUESTIONS = "questions";
    @Autowired
    private IQuestionService<Question> questionService;

    @RequestMapping(value = "/questions/page/{pageNumber}", method = RequestMethod.GET)
    public ModelAndView getQuestionByPage(@PathVariable("pageNumber") Integer pageNumber) {
        Page<Question> questionPage = questionService.findAllWithPagination(pageNumber, 3);
        System.out.println(questionPage.getContent());
        System.out.println(questionPage.getTotalPages());
        System.out.println(questionPage.hasNext());
        Map<String, Object> model = new HashMap<>();
        model.put(QUESTION_PAGE_OBJECT, questionPage);
        return new ModelAndView(QUESTIONS, model);
    }

    @RequestMapping(value = CREATE_QUESTION_URL, method = RequestMethod.GET)
    public ModelAndView addNewQuestion() {
        Map<String, Object> model = new HashMap<>();
        model.put(QUESTION, new QuestionModelAttribute());
        return new ModelAndView(CREATE_QUESTION_JSP, model);
    }

    @RequestMapping(value = CREATE_QUESTION_URL, method = RequestMethod.POST)
    public String addNewQuestion(@ModelAttribute(QUESTION) QuestionModelAttribute questionModelAttribute, final RedirectAttributes redirectAttributes) {
        Question question = questionService.getQuestionFromModel(questionModelAttribute);
        questionService.save(question);
        redirectAttributes.addFlashAttribute("msg", SUCCESS_CREATE_QUESTION);
        //new ModelAndView(CREATE_QUESTION_JSP, SUCCESS_MESSAGE, SUCCESS_CREATE_QUESTION)
        return REDIRECT_CREATE_QUESTION_URL;
    }

}
