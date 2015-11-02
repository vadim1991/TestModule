<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="resources/css/form.css" rel="stylesheet"/>
</head>
<body>
<form action="/j_spring_security_check" method="post" id="login-form">
    <div class="content">
        <div class="title">Welcome</div>
        <input type="text" data-validate="required" id="email" name="email" placeholder="E-mail"/>
        <input type="password" data-validate="required" name="password" placeholder="Password"/>
        <button>Sign in</button>
        <div class="social"><span>or sign up with social media</span></div>
        <div class="buttons">
            <button class="facebook"><i class="fa fa-facebook"></i>Facebook</button>
            <button class="twitter"><i class="fa fa-twitter"></i>Twitter</button>
            <button class="google"><i class="fa fa-google-plus"></i>Google</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="/resources/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/resources/js/verify.js"></script>
<script type="text/javascript" src="/resources/js/verify.min.js"></script>
<script>

</script>
</body>
</html>
