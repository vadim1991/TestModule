<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="resources/css/form.css" rel="stylesheet"/>
</head>
<body>
<header>
    <nav class="navbar navbar"></nav>
</header>
<form action="/j_spring_security_check" method="post">
    <h1>Material Design Login Form</h1>
    <input placeholder="Username" name="email" type="text" required="">
    <input placeholder="Password" name="password" type="password" required="">
    <button>Submit</button>
</form>


<jsp:include page="../views/common/footer.jsp"></jsp:include>
<script type="text/javascript" src="/resources/js/form.js"></script>
</body>
</html>
