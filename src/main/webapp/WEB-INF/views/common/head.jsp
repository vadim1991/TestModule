<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
  <link href="/resources/bower_components/bootstrap/dist/css/bootstrap.css" rel="stylesheet"/>
  <link href="/resources/bower_components/bootstrap-material-design/dist/css/roboto.css" rel="stylesheet"/>
  <link href="/resources/bower_components/bootstrap-material-design/dist/css/material-fullpalette.css" rel="stylesheet"/>
  <link href="/resources/bower_components/bootstrap-material-design/dist/css/ripples.css" rel="stylesheet"/>
  <link href="/resources/css/custom_style.css" rel="stylesheet"/>
</head>
<spring:url value="/" var="urlHome" />
<spring:url value="/create/question" var="urlAddQuestion" />
<spring:url value="/result/test/12345" var="showResults" />
<spring:url value="/available/tests" var="availableTests" />
<spring:url value="/passed/tests" var="passedTests" />
<nav class="navbar navbar">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="${urlHome}">Home Page</a>
    </div>
    <div id="navbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="${urlAddQuestion}">Add Question</a></li>
        <li><a href="${availableTests}">Available Tests</a></li>
        <li><a href="${passedTests}">Passed Tests</a></li>
      </ul>
    </div>
  </div>
</nav>