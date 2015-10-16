<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Home page</title>
    <jsp:include page="../views/main/header.jsp"/>
</head>
<body>
<h1>My home page</h1>

<div class="container">
    <div>
        This is DIV block
    </div>
    <div>
        <a class="btn btn-flat btn-warning"
           href="https://accounts.google.com/o/oauth2/auth?redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fgglogin&response_type=code&client_id=514760360658-c6jcg1d26f2arb8fiosvnnl53r393kan.apps.googleusercontent.com&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile"
           title="����� ����� Google">����� ����� Google</a>
    </div>
    <div>
        <a class="btn btn-flat btn-warning" href="http://oauth.vk.com/authorize?client_id=5045023&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fvklogin&response_type=code&display=page&scope=friends"
           title="����� ����� ���������">����� ����� ���������</a>
    </div>
</div>
<jsp:include page="../views/common/footer.jsp"></jsp:include>
</body>
</html>