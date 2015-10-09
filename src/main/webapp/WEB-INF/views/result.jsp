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
<body style="background-color: white">
<div class="container">
    <div class="text-center">
        <div class="text-center">
            <h1>Результат прохождения теста</h1>
        </div>
        <div class="text-center">
            <h3>Ваш результат: </h3>

            <h3>${passedTest.result} &#37;</h3>
        </div>
    </div>
    <div class="col-xs-offset-1 col-xs-10">
        <div id="test" class="test-result">
            <c:forEach items="${passedTest.passedQuestions}" var="passedQuestion" varStatus="i">
                <c:set var="questionClass" scope="request" value="incorrect"></c:set>
                <c:if test="${passedQuestion.rightAnswer}">
                    <c:set var="questionClass" scope="request" value="correct"></c:set>
                </c:if>
                <div class="question-result">
                    <div class="question-result-block ${questionClass}">
                        <div class="question-topic">Custom questions</div>
                        <div class="question-text">

                            <c:choose>
                                <c:when test="${passedQuestion.rightAnswer}">
                                    <span class="questionTitle right-question-title">${passedQuestion.question.title}</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="questionTitle wrong-question-title">${passedQuestion.question.title}</span>
                                </c:otherwise>
                            </c:choose>
                            <pre class="java">${passedQuestion.question.questionContent}</pre>
                        </div>

                        <ul class="answers">
                            <c:forEach items="${passedQuestion.userAnswers}" var="userAnswer" varStatus="j">
                                <c:set var="last" value=""></c:set>
                                <c:if test="${j.last}">
                                    <c:set var="last" value="last"></c:set>
                                </c:if>
                                <c:set var="answerClass" scope="request" value="wrong-answer"></c:set>
                                <c:if test="${userAnswer.rightAnswer}">
                                    <c:set var="answerClass" scope="request" value="correct-answer"></c:set>
                                </c:if>
                                <li class="${last}">
                                    <c:choose>
                                        <c:when test="${userAnswer.userAnswer}">
                                            <input type="radio" disabled="disabled"
                                                   checked>
                                            <span class="${answerClass}">${userAnswer.answerTest}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="radio" disabled="disabled">
                                            <span class="${answerClass}">${userAnswer.answerTest}</span>
                                        </c:otherwise>
                                    </c:choose>

                                </li>
                            </c:forEach>
                        </ul>
                        <p class="explanation">Пояснение:</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<div class="clearfix"></div>
</body>
<script>
    $(document).ready(function () {
        $.material.init();
    });
</script>
</html>

