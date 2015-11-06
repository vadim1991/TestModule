package com.englishschool.controllers;

import com.englishschool.entity.Category;
import com.englishschool.entity.Question;
import com.englishschool.entity.datatable.DataTableBean;
import com.englishschool.entity.datatable.QuestionForDataTableBean;
import com.englishschool.entity.spring.QuestionModelAttribute;
import com.englishschool.service.category.ICategoryService;
import com.englishschool.service.json.JsonService;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.englishschool.datamodel.CommonConstants.*;
import static com.englishschool.datamodel.CommonMessages.SUCCESS_CREATE_QUESTION;
import static com.englishschool.datamodel.CommonMessages.SUCCESS_DELETE_QUESTION;
import static com.englishschool.datamodel.CommonURLs.*;
import static com.englishschool.service.helper.ServiceUtils.completeDataTableBeanFromRequest;

/**
 * Created by Vadym_Vlasenko on 10/16/2015.
 */
@Controller
public class QuestionController {

    @Autowired
    private ICategoryService<Category> categoryService;
    @Autowired
    private QuestionModelValidator validator;
    @Autowired
    private IQuestionService<Question> questionService;
    @Autowired
    private JsonService jsonService;

    @RequestMapping(value = CREATE_QUESTION_URL, method = RequestMethod.GET)
    public ModelAndView addNewQuestion() {
        List<Category> categories = categoryService.findAll();
        Map<String, Object> model = new HashMap<>();
        model.put(QUESTION, new QuestionModelAttribute());
        model.put(CATEGORIES, categories);
        return new ModelAndView(CREATE_QUESTION_PAGE, model);
    }

    @RequestMapping(value = CREATE_QUESTION_URL, method = RequestMethod.POST)
    public String addNewQuestion(@ModelAttribute(QUESTION) QuestionModelAttribute question, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        validator.validate(question, bindingResult);
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

    @RequestMapping(value = QUESTIONS_PAGES_URL, method = RequestMethod.GET)
    public void getQuestionByPages(DataTableBean dataTableBean, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] questionFields = {QUESTION_ID, TITLE, CATEGORY};
        completeDataTableBeanFromRequest(request, questionFields, dataTableBean);
        Page<Question> questionsPagination = questionService.findAllWithPagination(dataTableBean, questionFields);
        List<Question> questions = questionsPagination.getContent();
        List<QuestionForDataTableBean> dataModelQuestions = questionService.convertQuestionsForDataTableBean(questions);
        String questionsDataJson = jsonService.dataToJson(dataModelQuestions, dataTableBean, (int) questionsPagination.getTotalElements());
        response.getWriter().write(questionsDataJson);
    }

    @RequestMapping(value = "/admin/delete/questions", method = RequestMethod.POST)
    public void deleteTests(@RequestBody List<String> questionsIDs, HttpServletResponse response) throws IOException {
        System.out.println(questionsIDs);
        questionService.deleteByIDs(questionsIDs);
        response.getWriter().write(SUCCESS);
    }

    @RequestMapping(value = DELETE_QUESTION_URL, method = RequestMethod.GET)
    public String deleteQuestion(@PathVariable(ID) String id, final RedirectAttributes redirectAttributes) {
        boolean deleteResult = questionService.deleteByID(id);
        if (deleteResult) {
            redirectAttributes.addFlashAttribute(MSG_ATTRIBUTE, SUCCESS_DELETE_QUESTION);
        }
        return REDIRECT_TEST_CREATE_URL;
    }

}
