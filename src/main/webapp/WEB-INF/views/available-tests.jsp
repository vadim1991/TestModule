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
        <h2>Available tests</h2>
        <table id="example" class="table table-striped table-hover table-responsive">
            <thead>
            <tr>
                <th>Test ID</th>
                <th>Test title</th>
                <th>Time of test</th>
                <th>Run test</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty availableTests}">
                <div>
                    <h1>You haven`t available tests</h1>
                </div>
            </c:if>
            <c:forEach items="${availableTests}" var="test" varStatus="i">
                <c:set var="classTD" value="success"></c:set>
                <c:if test="${i.index%2 == 0}">
                    <c:set var="classTD" value="warning"></c:set>
                </c:if>
                <tr class="${classTD}">
                    <td>${test.id}</td>
                    <td>${test.testTitle}</td>
                    <td>${test.timeOfTest / 60} min</td>
                    <td>
                        <a href="/run/test/${test.id}" role="button" class="btn btn-primary btn-sm">
                            <i class="mdi-navigation-arrow-forward"></i>
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
    <jsp:param name="page" value="available"></jsp:param>
</jsp:include>
</body>
</html>