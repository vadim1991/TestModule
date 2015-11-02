<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../views/common/head.jsp"></jsp:include>
    <title>Add Test</title>
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <c:if test="${not empty msg}">
            <div class="alert alert-dismissable alert-material-cyan-300">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <p>${msg}</p>
            </div>
        </c:if>
        <form:form class="form-horizontal" action="/test/create" method="post" modelAttribute="test">

            <h2>Add new Test</h2>

            <spring:bind path="testTitle">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label for="testTitle" class="col-lg-2 control-label">Title</label>

                    <div class="col-lg-10">
                        <form:input path="testTitle" type="text" id="testTitle" class="form-control"
                                    cssClass="form-control"
                                    placeholder="Title"/>
                        <span class="text-danger"><form:errors path="testTitle" class="control-label"/></span>
                    </div>
                </div>
            </spring:bind>
            <spring:bind path="timeOfTest">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label for="timeOfTest" class="col-lg-2 control-label">Test duration</label>

                    <div class="col-lg-10">
                        <form:input path="timeOfTest" class="form-control" id="timeOfTest" placeholder="Duration"/>
                        <span class="help-block">Duration test time (minutes)</span>
                        <span class="text-danger"><form:errors path="timeOfTest" class="control-label"/></span>
                    </div>
                </div>
            </spring:bind>
            <form:hidden path="questionIds" id="questionIDs"></form:hidden>
            <div class="form-group">
                <div class="datatable-block">
                    <p>Questions</p>
                    <p>Chooses questions : <span id="count-questions">0</span></p>
                    <table id="questions-table" class="table table-striped table-hover table-bordered" cellspacing="0"
                           width="100%">
                        <thead>
                        <tr>
                            <th>Question ID</th>
                            <th>Question title</th>
                            <th>Question category</th>
                            <th>Update question</th>
                            <th>Delete question</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <a href="/test/create" class="btn btn-default">Cancel</a>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </form:form>
    </div>
</div>
<jsp:include page="../views/common/footer.jsp">
    <jsp:param name="page" value="addTest"></jsp:param>
</jsp:include>
</body>
</html>