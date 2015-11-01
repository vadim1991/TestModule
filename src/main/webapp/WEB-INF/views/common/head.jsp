<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <link href="/resources/bower_components/bootstrap/dist/css/bootstrap.css" rel="stylesheet"/>
    <link href="/resources/bower_components/bootstrap-material-design/dist/css/roboto.css" rel="stylesheet"/>
    <link href="/resources/bower_components/bootstrap-material-design/dist/css/material-fullpalette.css"
          rel="stylesheet"/>
    <link href="/resources/bower_components/bootstrap-material-design/dist/css/ripples.css" rel="stylesheet"/>
    <link href="/resources/css/dataTables.bootstrap.min.css" rel="stylesheet"/>
    <link href="/resources/css/select.bootstrap.min.css" rel="stylesheet"/>
    <link href="/resources/css/custom_style.css" rel="stylesheet"/>
</head>
<spring:url value="/" var="urlHome"/>
<spring:url value="/assign/tests" var="urlAssignTest"/>
<spring:url value="/profile/create" var="addProfileUrl"/>
<spring:url value="/question/create" var="urlAddQuestion"/>
<spring:url value="/test/create" var="urlAddTest"/>
<spring:url value="/result/test/12345" var="showResults"/>
<spring:url value="/available/tests" var="availableTests"/>
<spring:url value="/passed/tests" var="passedTests"/>
<spring:url value="/logout" var="logout"/>
<nav class="navbar navbar">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${urlHome}">Home Page</a>
        </div>
        <div id="navbar">
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                    <li><a id="assignTest" href="${urlAssignTest}">Assign Test</a></li>
                    <li><a id="addProfile" href="${addProfileUrl}">Add Profile</a></li>
                    <li><a id="addTest" href="${urlAddTest}">Add Test</a></li>
                    <li><a id="addQuestion" href="${urlAddQuestion}">Add Question</a></li>
                </sec:authorize>
                <li><a id="available" href="${availableTests}">Available Tests</a></li>
                <li><a id="passed" href="${passedTests}">Passed Tests</a></li>
                <sec:authorize access="isAuthenticated()">
                    <li><a id="logout" href="${logout}">Logout</a></li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>