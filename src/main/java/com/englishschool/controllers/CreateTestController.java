package com.englishschool.controllers;

import com.englishschool.entity.Answer;
import com.englishschool.entity.Question;
import com.englishschool.service.question.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.englishschool.datamodel.CommonConstants.SUCCESS_MESSAGE;
import static com.englishschool.datamodel.CommonMessages.SUCCESS_CREATE_QUESTION;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
@Controller
public class CreateTestController {

    @Autowired
    private IQuestionService questionService;

    @RequestMapping(value = "create/question", method = RequestMethod.GET)
    public ModelAndView addNewQuestion() {
        Answer answer = new Answer();
        Question question = new Question();
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(answer);
        answers.add(answer);
        answers.add(answer);
        question.setAnswers(answers);
        return new ModelAndView("createQuestion", "question", question);
    }

    @RequestMapping(value = "create/question", method = RequestMethod.POST)
    public String addNewQuestion(@ModelAttribute("question")Question model, Question question, Model modelMap, Answer answer) {
        System.out.println(question);
        System.out.println(answer);
        System.out.println(model);
        questionService.save(question);
        modelMap.addAttribute(SUCCESS_MESSAGE, SUCCESS_CREATE_QUESTION);
        return "createQuestion";
    }

}
