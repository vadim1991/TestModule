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
            <div class="col-sm-9 ${questionClass}">
                <div class="row well bs-component shadow-z-1">
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
                                    <span class="label label-warning">Custom questions</span>
                                    <span class="label label-info">JAVA</span>
                                </div>
                                <h4 class="list-group-item-heading">${passedQuestion.question.title}</h4>
                                <p class="list-group-item-text">Donec id elit non mi porta gravida at eget metus?</p>
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
            <div class="col-sm-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Варианты ответа:</h3>
                    </div>
                    <div class="panel-body" id="${question.id}">
                        <c:forEach items="${passedQuestion.userAnswers}" var="userAnswer" varStatus="j">
                            <%--<c:set var="last" value=""></c:set>--%>
                            <%--<c:if test="${j.last}">--%>
                                <%--<c:set var="last" value="last"></c:set>--%>
                            <%--</c:if>--%>
                            <%--<c:set var="answerClass" scope="request" value="wrong-answer"></c:set>--%>
                            <%--<c:if test="${userAnswer.rightAnswer}">--%>
                                <%--<c:set var="answerClass" scope="request" value="correct-answer"></c:set>--%>
                            <%--</c:if>--%>
                            <%--<div class="${last}">--%>
                                <%--<c:choose>--%>
                                    <%--<c:when test="${userAnswer.userAnswer}">--%>
                                        <%--<input type="radio" disabled="disabled" checked>--%>
                                        <%--<span class="${answerClass}">${userAnswer.answerTest}</span>--%>
                                    <%--</c:when>--%>
                                    <%--<c:otherwise>--%>
                                        <%--<input type="radio" disabled="disabled">--%>
                                        <%--<span class="${answerClass}">${userAnswer.answerTest}</span>--%>
                                    <%--</c:otherwise>--%>
                                <%--</c:choose>--%>
                            <%--</div>--%>
                        </c:forEach>
                        <div class="checkbox right-answer">
                            <i class="mdi-toggle-check-box"></i>
                            <span class="answer-text">Right answer</span>
                        </div>
                        <div class="checkbox right-answer">
                            <i class="mdi-toggle-check-box"></i>
                            <span class="answer-text">Right answer</span>
                        </div>
                        <div class="checkbox answer">
                            <i class="mdi-toggle-check-box-outline-blank"></i>
                            <span class="answer-text">Answer</span>
                        </div>
                        <div class="checkbox wrong-answer">
                            <span class="wrong-icon">
                                <i class="mdi-content-clear cross-icon"></i>
                                <i class="mdi-toggle-check-box-outline-blank"></i>
                            </span>
                            <span class="answer-text">Wrong answer</span>
                        </div>
                    </div>
                </div>
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">Пояснение:</h3>
                    </div>
                    <div class="panel-body text-justify">
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer consequat tempus sapien, et feugiat eros
                        hendrerit eget. Nulla ante purus, egestas eget vulputate vel, euismod ac ante. </p>
                    </div>
                </div>
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

