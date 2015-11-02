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
        <form:form class="form-horizontal" action="/assign/tests" method="post" modelAttribute="assignTest">

            <h2>Assign Tests</h2>

            <spring:bind path="groupIDs">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Group IDs</label>

                    <div class="col-sm-5">
                        <form:select path="groupIDs" items="${groups}" size="3" class="form-control"
                                     itemLabel="groupName" itemValue="id" multiple="true">
                        </form:select>
                        <form:errors path="groupIDs" class="control-label"/>
                    </div>
                </div>
            </spring:bind>
            <input type="hidden" name="profileIDs" value="" id="profileIDs">

            <div class="form-group">
                <div class="datatable-block">
                    <p>Profiles</p>

                    <p>Selected profile : <span id="count-profiles">0</span></p>
                    <table id="profile-table" class="table table-striped table-hover table-bordered" cellspacing="0"
                           width="100%">
                        <thead>
                        <tr>
                            <th>Profile ID</th>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Email</th>
                            <th>Age</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <input type="hidden" name="testIDs" value="" id="testIDs">

            <div class="form-group">
                <div class="datatable-block">
                    <p>Tests</p>

                    <p>Selected tests : <span id="count-tests">0</span></p>

                    <div class="col-sm-4 col-sm-offset-9">
                        <a class="btn btn-danger btn-flat btn-sm" data-toggle="modal"
                           data-target="#complete-dialog">
                            <i class="mdi-content-clear"></i>
                            <span class="btn-text">Remove selected</span>

                            <div class="ripple-wrapper"></div>
                        </a>
                    </div>

                    <table id="tests-table" class="table table-striped table-hover table-bordered" cellspacing="0"
                           width="100%">
                        <thead>
                        <tr>
                            <th>Test ID</th>
                            <th>Test title</th>
                            <th>Test creation date</th>
                            <th>Test duration</th>
                            <th>Update test</th>
                            <th>Delete test</th>
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
<jsp:include page="dialogs/delete-tests.jsp"></jsp:include>
<jsp:include page="../views/common/footer.jsp">
    <jsp:param name="page" value="assignTest"></jsp:param>
</jsp:include>
</body>
</html>