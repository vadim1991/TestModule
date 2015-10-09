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
    <table class="table table-striped table-hover table-responsive">
        <thead>
        <tr>
            <th>TEST ID</th>
            <th>TEST RESULT</th>
            <th>TIME OF START</th>
            <th>TIME OF END</th>
            <th>SHOW RESULT</th>
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
                <td><a class="btn btn-fab btn-raised mdi-navigation-arrow-forward btn btn-material-teal-500"
                       href="/result/test/${passedTest.id}"></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="../views/common/footer.jsp"></jsp:include>
<script>
    $(document).ready(function () {
        $.material.init();
    });
</script>
</body>
</html>