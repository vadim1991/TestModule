<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
    <link rel="stylesheet" href="../resources/css/custom_style.css">
    <link rel="stylesheet" href="../resources/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="../resources/dist/css/roboto.min.css">
    <link rel="stylesheet" href="../resources/dist/css/material-fullpalette.min.css">
    <link rel="stylesheet" href="../resources/dist/css/ripples.min.css">
    <link rel="stylesheet" href="../resources/dist/css/material.css">
    <link href="//fezvrasta.github.io/snackbarjs/dist/snackbar.min.css" rel="stylesheet">
    <script src="../resources/js/jquery-1.11.2.min.js"></script>
    <script src="../resources/bootstrap/js/bootstrap.js"></script>
    <script src="../resources/dist/js/ripples.js"></script>
    <script src="../resources/dist/js/material.js"></script>
</head>
<spring:url value="/" var="urlHome" />
<nav class="navbar navbar-material-light-blue-300">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${urlHome}">Spring MVC Form</a>
        </div>
        <div id="navbar">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="${urlAddUser}">Add User</a></li>
            </ul>
        </div>
    </div>
</nav>