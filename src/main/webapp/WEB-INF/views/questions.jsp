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
        <c:if test="${not empty msg}">
            <div class="alert alert-dismissable alert-material-cyan-300">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <p>${msg}</p>
            </div>
        </c:if>
        <h2>Questions</h2>
        <table id="example" class="table table-striped table-hover table-responsive">
            <thead>
            <tr>
                <th>Question ID</th>
                <th>Question title</th>
                <th>Question type</th>
                <th>Update question</th>
                <th>Delete question</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<jsp:include page="../views/common/footer.jsp">
    <jsp:param name="page" value="questions"></jsp:param>
</jsp:include>
</body>
</html>