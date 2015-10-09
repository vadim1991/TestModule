<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="WEB-INF/views/common/head.jsp"></jsp:include>
    <title>Error</title>
</head>
<body>

<div class="container">

    <h1>Index Page</h1>

    <div>${message}</div>

    <p>${exception.message}</p>
    <!-- Exception: ${exception.message}.
          <c:forEach items="${exception.stackTrace}" var="stackTrace">
            ${stackTrace}
        </c:forEach>
      -->

</div>

</body>
</html>
