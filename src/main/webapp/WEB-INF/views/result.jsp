<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../views/common/head.jsp"></jsp:include>
    <title>Show Test Results</title>
</head>
<body>
<div class="container" id="resultTestModel">

    <%--Page title start--%>
    <div class="col-sm-12 text-center">
        <h1>Результат прохождения теста</h1>

        <h3>Ваш результат: </h3>

        <h3>${passedTest.result} &#37;</h3>
    </div>
    <%--Page title end--%>

    <%--Questions list start --%>
    <div class="col-sm-12 text-center">
        <ul class="pagination" id="test-list">
            <c:forEach items="${questions}" var="question" varStatus="i">
                <li>
                    <a href="#" role="button">${i.index + 1}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <%--Questions list end --%>

    <%--Questions list start --%>
    <c:forEach items="${passedTest.passedQuestions}" var="passedQuestion" varStatus="i">
        <c:set var="questionClass" scope="request" value="incorrect"></c:set>
        <c:if test="${passedQuestion.rightAnswer}">
            <c:set var="questionClass" scope="request" value="correct"></c:set>
        </c:if>
        <div class="container question" data-question='${i.index + 1}' style="display:none;">
                <%--Question text start--%>
            <div class="col-sm-12 ${questionClass}">
                <div class="row well bs-component shadow-z-1 question-block-result">
                    <div class="list-group">
                        <div class="list-group-item">
                            <div class="row-action-primary">
                                <c:choose>
                                    <c:when test="${passedQuestion.rightAnswer}">
                                        <i class="mdi-action-thumb-up"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="mdi-action-thumb-down"></i>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="row-content">
                                <div class="least-content">
                                    <span class="label label-info">${passedQuestion.question.category}</span>
                                </div>
                                <div>
                                    <h4 class="list-group-item-text">${passedQuestion.question.title}</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="question-code col-sm-12">
                        <pre class="java">${passedQuestion.question.questionContent}</pre>
                    </div>
                </div>
            </div>
                <%--Question text end--%>

                <%--Answers block start--%>
            <div class="col-sm-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Варианты ответа:</h3>
                    </div>
                    <div class="panel-body" id="${question.id}">
                        <c:forEach items="${passedQuestion.userAnswers}" var="userAnswer" varStatus="j">
                            <c:set var="rightAnswer" scope="request" value="${userAnswer.rightAnswer}"></c:set>
                            <c:choose>
                                <c:when test="${userAnswer.userAnswer}">
                                    <div class="checkbox ${rightAnswer ? 'right-answer' : 'wrong-answer'}">
                                        <c:choose>
                                            <c:when test="${rightAnswer}">
                                                <i class="mdi-toggle-check-box"></i>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="wrong-icon">
                                                    <i class="mdi-content-clear cross-icon"></i>
                                                    <i class="mdi-toggle-check-box-outline-blank"></i>
                                                </span>
                                            </c:otherwise>
                                        </c:choose>
                                        <span class="answer-text">${userAnswer.answerTest}</span>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="checkbox ${rightAnswer ? 'right-answer' : 'answer'}">
                                        <i class="mdi-toggle-check-box-outline-blank"></i>
                                        <span class="answer-text">${userAnswer.answerTest}</span>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                </div>
                <c:if test="${not empty passedQuestion.question.explanation}">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">Пояснение:</h3>
                        </div>
                        <div class="panel-body text-justify">
                            <p>${passedQuestion.question.explanation}</p>
                        </div>
                    </div>
                </c:if>
            </div>
                <%--Answers block end--%>
        </div>
    </c:forEach>
</div>
<jsp:include page="../views/common/footer.jsp">
    <jsp:param name="footerClass" value="hidden"/>
</jsp:include>
</body>
</html>

