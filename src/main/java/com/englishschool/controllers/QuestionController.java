package com.englishschool.controllers;

import com.englishschool.entity.Question;
import com.englishschool.entity.spring.DataTableBean;
import com.englishschool.entity.spring.QuestionModelAttribute;
import com.englishschool.service.question.IQuestionService;
import com.englishschool.validator.QuestionModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.englishschool.datamodel.CommonConstants.*;
import static com.englishschool.datamodel.CommonMessages.SUCCESS_CREATE_QUESTION;
import static com.englishschool.datamodel.CommonMessages.SUCCESS_DELETE_QUESTION;
import static com.englishschool.datamodel.CommonURLs.*;

/**
 * Created by Vadym_Vlasenko on 10/16/2015.
 */
@Controller
public class QuestionController {

    public static final String QUESTION_PAGE_OBJECT = "questionPage";
    public static final String QUESTIONS = "questions";

    @Autowired
    private QuestionModelValidator questionValidator;
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(questionValidator);
    }
    @Autowired
    private IQuestionService<Question> questionService;

    @RequestMapping(value = CREATE_QUESTION_URL, method = RequestMethod.GET)
    public ModelAndView addNewQuestion() {
        Map<String, Object> model = new HashMap<>();
        model.put(QUESTION, new QuestionModelAttribute());
        return new ModelAndView(CREATE_QUESTION_PAGE, model);
    }

    @RequestMapping(value = ALL_QUESTIONS_URL, method = RequestMethod.GET)
    public String getAllQuestions() {
//        List<Question> questions = questionService.findAll();
//        model.put(QUESTIONS, questions);
//        Map<String, Object> model = new HashMap<>();
        return ALL_QUESTIONS_PAGE;
    }

    @RequestMapping(value = CREATE_QUESTION_URL, method = RequestMethod.POST)
    public String addNewQuestion(@ModelAttribute(QUESTION) @Validated QuestionModelAttribute question, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        System.out.println(question);
        if (bindingResult.hasErrors()) {
            return CREATE_QUESTION_PAGE;
        }
        Question questionBean = questionService.getQuestionFromModel(question);
        questionService.save(questionBean);
        redirectAttributes.addFlashAttribute(MSG_ATTRIBUTE, SUCCESS_CREATE_QUESTION);
        return REDIRECT_CREATE_QUESTION_URL;
    }

    @RequestMapping(value = UPDATE_QUESTION_GET_URL, method = RequestMethod.GET)
    public ModelAndView updateQuestion(@PathVariable(ID) String id) {
        Map<String, Object> model = new HashMap<>();
        Question question = questionService.findById(id);
        QuestionModelAttribute questionModelAttribute = questionService.convertQuestionToModel(question);
        model.put(QUESTION, questionModelAttribute);
        return new ModelAndView(UPDATE_QUESTION_PAGE, model);
    }

    @RequestMapping(value = DELETE_QUESTION_URL, method = RequestMethod.GET)
    public String deleteQuestion(@PathVariable(ID) String id, final RedirectAttributes redirectAttributes) {
        System.out.println("------------delete");
        boolean deleteResult = questionService.deleteByID(id);
        System.out.println("------------delete");
        if (deleteResult) {
            redirectAttributes.addFlashAttribute(MSG_ATTRIBUTE, SUCCESS_DELETE_QUESTION);
        }
        return REDIRECT_QUESTIONS_URL;
    }

}
