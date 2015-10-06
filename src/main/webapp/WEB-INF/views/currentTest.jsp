<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <jsp:include page="../views/common/head.jsp"></jsp:include>
  <title>Error</title>
</head>
<body>

<div class="container">

  <h1>You have a current not passed test</h1>
  <div><a href="/run/test/${testID}" class="btn btn-info">Continue current Test</a> </div>

  <p>${exception.message}</p>
  <!-- Exception: ${exception.message}.
          <c:forEach items="${exception.stackTrace}" var="stackTrace">
            ${stackTrace}
        </c:forEach>
      -->

</div>

</body>
</html>