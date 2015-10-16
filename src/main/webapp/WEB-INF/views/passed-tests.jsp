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
    <div class="jumbotron">
        <h2>Passed tests</h2>
        <table class="table table-striped table-hover table-responsive">
            <thead>
            <tr>
                <th>Test ID</th>
                <th>Test title</th>
                <th>Time of test</th>
                <th>Run test</th>
                <th>Show result</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${passedTests}" var="passedTest" varStatus="i">
                <c:set var="classTD" value="success"></c:set>
                <c:if test="${i.index%2 == 0}">
                    <c:set var="classTD" value="warning"></c:set>
                </c:if>
                <tr class="${classTD}">
                    <td>${passedTest.id}</td>
                    <td>${passedTest.result}</td>
                    <td>${passedTest.startTest}</td>
                    <td>${passedTest.endTest}</td>
                    <td>
                        <a href="/result/test/${passedTest.id}" role="button" class="btn btn-primary btn-sm">
                        <i class="mdi-action-visibility"></i>
                        <div class="ripple-wrapper"></div>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="../views/common/footer.jsp">
    <jsp:param name="page" value="passed"></jsp:param>
</jsp:include>
</body>
</html>