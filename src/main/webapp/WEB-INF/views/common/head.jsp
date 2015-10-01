<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
  <link href="../resources/bower_components/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
  <link href="../resources/bower_components/bootstrap-material-design/dist/css/roboto.css" rel="stylesheet">
  <link href="../resources/bower_components/bootstrap-material-design/dist/css/material-fullpalette.css" rel="stylesheet">
  <link href="../resources/bower_components/bootstrap-material-design/dist/css/ripples.css" rel="stylesheet">
  <script src="../resources/bower_components/jquery/dist/jquery.js"></script>
  <script src="../resources/bower_components/bootstrap/dist/js/bootstrap.js"></script>
  <script src="../resources/bower_components/bootstrap-material-design/dist/js/material.js"></script>
  <script src="../resources/bower_components/bootstrap-material-design/dist/js/ripples.js"></script>
</head>
<spring:url value="/" var="urlHome" />
<spring:url value="/create/question" var="urlAddQuestion" />
<nav class="navbar navbar-inverse">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="${urlHome}">Home Page</a>
    </div>
    <div id="navbar">
      <ul class="nav navbar-nav navbar-right">
        <li class="active"><a href="${urlAddQuestion}">Add Question</a></li>
      </ul>
    </div>
  </div>
</nav>