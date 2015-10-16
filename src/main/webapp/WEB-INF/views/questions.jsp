<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../views/common/head.jsp"></jsp:include>
    <title>Questions</title>
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h2>Passed tests</h2>
        <table id="example" class="table table-striped table-hover table-responsive"
               data-toggle="table"
               data-url="/questions/page/"
               data-height="400"
               data-side-pagination="server"
               data-pagination="true"
               data-page-list="[5, 10, 20, 50, 100, 200]"
               data-search="true">
            <thead>
            <tr>
                <th>Question ID</th>
                <th>Question title</th>
                <th>Question type</th>
                <th>Update question</th>
                <th>Delete question</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${questionPage.content}" var="question" varStatus="i">
                <c:set var="classTD" value="success"></c:set>
                <c:if test="${i.index%2 == 0}">
                    <c:set var="classTD" value="warning"></c:set>
                </c:if>
                <tr class="${classTD}">
                    <td>${question.id}</td>
                    <td>${question.title}</td>
                    <td>${question.questionType}</td>
                    <td><a href="/question/update/${question.id}" role="button"
                           class="btn btn-primary btn-sm">Update</a></td>
                    <td>
                        <a href="/question/delete/${question.id}" role="button"
                           class="btn btn-danger btn-sm">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="../views/common/footer.jsp">
    <jsp:param name="page" value="questions"></jsp:param>
</jsp:include>
</body>
</html>