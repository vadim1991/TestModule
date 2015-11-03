<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../views/common/head.jsp"></jsp:include>
    <title>Run Test</title>
</head>
<body>
<form:form action="/test/check" modelAttribute="passedTestModel" method="post">
    <div class="container">
            <%--Page title start--%>
        <div class="col-sm-12 text-center">
            <h1>Тестирование</h1>
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
            <ul class="pagination">
                <li>
                    <a href="#" role="button" id="end-test">
                        <i class="mdi-image-assistant-photo"></i>
                        <span class="btn-text">Завершить тест</span>
                    </a>
                </li>
                <li class="disabled">
                    <a href="#" role="button" class="btn-with-text">
                        <i class="mdi-image-timer"></i>
                        <span id="timer" class="btn-text">0:00</span>
                    </a>
                </li>
            </ul>
        </div>
            <%--Questions list end --%>

        <c:forEach items="${questions}" var="question" varStatus="i">
            <div class="container question" data-question='${i.index + 1}' style="display:none;">
                    <%--Question text start--%>
                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <c:set var="buttonClass" value="checkbox"></c:set>
                        <c:if test="${question.questionType eq 'RADIO'}">
                            <c:set var="buttonClass" value="radio"></c:set>
                        </c:if>
                        <input type="hidden" name="passedQuestions[${i.index}].id" value="${question.id}">

                        <div class="panel-heading">
                            <div class="list-group">
                                <div class="list-group-item">
                                    <div class="row-content">
                                        <div class="least-content">
                                            <span class="label label-info">${question.category}</span>
                                        </div>
                                        <div>
                                            <h4 class="list-group-item-heading">Question ${i.index + 1}</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="question-text col-sm-12">
                                <h4>${question.title}</h4>
                            </div>
                            <div class="question-code col-sm-12">
                                <pre class="java">${question.questionContent}</pre>
                            </div>
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
                            <c:forEach items="${question.answers}" var="answer" varStatus="j">
                                <div class="${buttonClass} ${buttonClass}-primary">
                                    <label>
                                        <input class="answer" type="${buttonClass}" data-id="${question.id}${answer.id}"
                                               name="passedQuestions[${i.index}].userAnswers" value="${answer.id}">
                                            ${answer.answerText}
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <a data-id="${i.index + 1}" class='confirm btn btn-success submit-btn'>Ответить</a>
                </div>
                    <%--Answers block end--%>
            </div>

        </c:forEach>
    </div>
</form:form>
<jsp:include page="../views/common/footer.jsp">
    <jsp:param name="page" value="addQuestion"></jsp:param>
    <jsp:param name="footerClass" value="hidden"/>
</jsp:include>
<script>
    $(document).trigger('timer', ${timer});
</script>
</body>
</html>
