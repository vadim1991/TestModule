<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Error</title>
    <jsp:include page="../views/common/header.jsp"/>
</head>
<body>

<div class="container">

    <h1>Error Page</h1>

    <p>${exception.message}</p>
    <!-- Exception: ${exception.message}.
          <c:forEach items="${exception.stackTrace}" var="stackTrace">
            ${stackTrace}
        </c:forEach>
      -->

</div>

</body>
</html>