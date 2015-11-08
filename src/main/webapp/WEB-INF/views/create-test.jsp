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
<div class="container custom-container">
    <div class="jumbotron">
        <c:if test="${not empty msg}">
            <div class="alert alert-dismissable alert-material-cyan-300">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <p>${msg}</p>
            </div>
        </c:if>
        <form:form class="form-horizontal" action="/test/create" method="post" modelAttribute="test">

            <h2>${subject}</h2>
            <c:if test="${not empty test.id}">
                <input name="id" type="hidden" value="${test.id}">
            </c:if>
            <spring:bind path="testTitle">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label for="testTitle" class="col-lg-2 control-label">Title</label>

                    <div class="col-lg-10">
                        <form:input data-validate="required" path="testTitle" type="text" id="testTitle"
                                    class="form-control"
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
                        <form:input data-validate="required,number,minVal(1)" path="timeOfTest" class="form-control"
                                    id="timeOfTest" placeholder="Duration"/>
                        <span class="help-block">Duration test time (minutes)</span>
                        <span class="text-danger"><form:errors path="timeOfTest" class="control-label"/></span>
                    </div>
                </div>
            </spring:bind>
            <form:hidden path="questionIds" id="questionIDs"></form:hidden>
            <spring:bind path="questionIds">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <div class="datatable-block">
                        <div class="row">
                            <div class="col-sm-4">
                                <p>Questions</p>
                                <p>Selected questions : <span id="count-questions">0</span></p></div>
                            <div class="col-sm-4 col-sm-offset-4">
                                <a class="btn btn-danger btn-flat btn-sm remove-all-button" data-toggle="modal"
                                   data-target="#complete-dialog">
                                    <i class="mdi-content-clear"></i>
                                    <span class="btn-text">Remove selected</span>

                                    <div class="ripple-wrapper">
                                    </div>
                                </a>
                            </div>
                        </div>
                        <span class="text-danger"><form:errors path="questionIds" class="control-label"/></span>
                        <table id="questions-table" class="table table-striped table-hover table-bordered"
                               cellspacing="0"
                               width="100%">
                            <thead>
                            <tr>
                                <th>Question ID</th>
                                <th>Question title</th>
                                <th>Question category</th>
                                <th>Update question</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </spring:bind>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <a href="/test/create" class="btn btn-default">Cancel</a>
                    <button type="submit" class="btn btn-primary">${button}</button>
                </div>
            </div>
        </form:form>
    </div>
</div>
<jsp:include page="../views/common/footer.jsp">
    <jsp:param name="page" value="addTest"></jsp:param>
</jsp:include>
<jsp:include page="dialogs/delete-confirm.jsp">
    <jsp:param name="title" value="questions"></jsp:param>
    <jsp:param name="id" value="complete-dialog"></jsp:param>
</jsp:include>
</body>
</html>