<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../views/common/head.jsp"></jsp:include>
    <title>Add Question</title>
</head>
<body>
<div class="container">
    <div class="text-center">
        <div class="text-center">
            <h1>Результат прохождения теста</h1>
        </div>
        <div class="text-center">
            <h3>Ваш результат: </h3>

            <h3>${passedTest.result}</h3>
        </div>
        <div class="text-center">
            <a class="btn btn-success" href="runTest">Пройти еще раз</a>
        </div>
    </div>
    <div class="col-xs-10">
        <div id="test" class="test">
            <c:forEach items="${passedTest.passedQuestions}" var="passedQuestion" varStatus="i">
                <div class="test">
                    <div class="question">
                        <input type="hidden" value="">

                        <p>
                            <c:choose>
                                <c:when test="${passedQuestion.rightAnswer}">
                                    <span class='glyphicon glyphicon-ok green'></span>
                                </c:when>
                                <c:otherwise>
                                    <span class='glyphicon glyphicon-remove red'></span>
                                </c:otherwise>
                            </c:choose>
                            <span class="questionTitle">${passedQuestion.question.title}</span>
                        </p>

                        <p>
                        <pre class="java">${passedQuestion.question.questionContent}</pre>
                        </p>
                    </div>
                    <div class="answer">
                        <div>
                            <c:forEach items="${passedQuestion.userAnswers}" var="userAnswer" varStatus="i">
                                <div class="radio">
                                    <label>
                                        <c:choose>
                                            <c:when test="${userAnswer.userAnswer}">
                                                <input type="radio" disabled="disabled"
                                                       checked>
                                                <c:if test="${userAnswer.rightAnswer}">
                                                    <span class="correct">${userAnswer.answerTest}</span>
                                                </c:if>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="radio" disabled="disabled">
                                                <c:if test="${userAnswer.rightAnswer}">
                                                    <span class="correct">${userAnswer.answerTest}</span>
                                                </c:if>
                                                <input type="radio" disabled="disabled">${userAnswer.answerTest}
                                            </c:otherwise>
                                        </c:choose>
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
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

