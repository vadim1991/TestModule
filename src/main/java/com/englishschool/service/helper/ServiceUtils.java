package com.englishschool.service.helper;

import com.englishschool.entity.PassedTest;
import com.englishschool.entity.Test;
import com.englishschool.entity.datatable.DataTableBean;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.englishschool.datamodel.CommonConstants.*;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created by Vadym_Vlasenko on 10/18/2015.
 */
public class ServiceUtils {

    public static String convertDateToString(DateTime dateTime) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_TIME_FORMAT);
        return dateTime.toString(fmt);
    }

    public static DateTime convertStringToDate(String dateTime) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_TIME_FORMAT);
        return DateTime.parse(dateTime, fmt);
    }

    public static long getRemainingTime(PassedTest passedTest, Test currentTest) {
        DateTime startTestTime = convertStringToDate(passedTest.getStartTest());
        long delta = new DateTime().getMillis() - startTestTime.getMillis();
        int timeOfTest = currentTest.getTimeOfTest() * SECONDS_FROM_MINUTE;
        return timeOfTest - (delta) / MILLISECONDS_FROM_SECOND;
    }

    public static void invalidateTestInfoFromSession(HttpSession session) {
        session.setAttribute(CURRENT_TEST, null);
        session.setAttribute(PASSED_TEST, null);
        session.setAttribute(QUESTIONS, null);
    }

    public static void removeAllCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if (name.contains(CHECKBOX_STATE) || name.contains(CURRENT) || name.contains(ACTIVE)) {
                cookie.setMaxAge(0);
                cookie.setPath(request.getContextPath() + RIGHT_SLASH);
                response.addCookie(cookie);
            }
        }
    }

    public static void completeDataTableBeanFromRequest(HttpServletRequest request, String[] fields, DataTableBean tableBean) {
        String orderColumn = request.getParameter(ORDER_0_COLUMN);
        String order = null;
        if (isNotBlank(orderColumn)) {
            order = fields[Integer.parseInt(orderColumn)];
        }
        tableBean.setOrderColumn(order);
        tableBean.setOrderParam(request.getParameter(ORDER_0_DIR));
        tableBean.setSearchWord(request.getParameter(SEARCH_VALUE));
    }

}
