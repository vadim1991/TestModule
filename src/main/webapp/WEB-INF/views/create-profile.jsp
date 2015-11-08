<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../views/common/head.jsp"></jsp:include>
    <title>Add Profile</title>
</head>
<body>
<div class="container custom-container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3 jumbotron">
            <c:if test="${not empty msg}">
                <div class="alert alert-dismissable alert-material-cyan-300">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <p>${msg}</p>
                </div>
            </c:if>
            <form:form class="form-horizontal add-profile" action="${url}" method="post"
                       modelAttribute="profile">

                <h2>${subject}</h2>
                <c:if test="${not empty profile.id}">
                    <input name="id" type="hidden" value="${profile.id}">
                </c:if>
                <spring:bind path="name">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label for="name" class="col-lg-2 control-label">Name</label>

                        <div class="col-lg-10">
                            <form:input data-validate="required" path="name" type="text" id="name" class="form-control"
                                        cssClass="form-control"
                                        placeholder="Name"/>
                            <span class="text-danger"><form:errors path="name" class="control-label"/></span>
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="surname">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label for="surname" class="col-lg-2 control-label">Surname</label>

                        <div class="col-lg-10">
                            <form:input data-validate="required" path="surname" type="text" id="surname"
                                        class="form-control"
                                        cssClass="form-control"
                                        placeholder="Surname"/>
                            <span class="text-danger"><form:errors path="surname" class="control-label"/></span>
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="email">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label for="email" class="col-lg-2 control-label">Email</label>

                        <div class="col-lg-10">
                            <form:input data-validate="required,email" path="email" type="text" id="email"
                                        class="form-control" cssClass="form-control"
                                        placeholder="Email"/>
                            <span class="text-danger"><form:errors path="email" class="control-label"/></span>
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="age">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label for="age" class="col-lg-2 control-label">Age</label>

                        <div class="col-lg-10">
                            <form:input data-validate="number,minVal(18),maxVal(70)" path="age" type="text" id="age"
                                        class="form-control" cssClass="form-control"
                                        placeholder="Age"/>
                            <span class="text-danger"><form:errors path="age" class="control-label"/></span>
                        </div>
                    </div>
                </spring:bind>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <div class="row">
                        <label class="col-sm-2 control-label">Group ID</label>

                        <div class="col-sm-4">
                            <form:select data-validate="required" path="groupId" class="form-control">
                                <form:option value="NONE" label="--- Select ---"/>
                                <form:options items="${groups}" itemLabel="groupName" itemValue="id"/>
                            </form:select>
                            <form:errors path="groupId" class="control-label"/>
                        </div>
                        <div class="col-sm-4 col-sm-offset-2">
                            <a class="btn btn-primary btn-flat btn-sm add-category" data-toggle="modal"
                               data-target="#complete-dialog">
                                <i class="mdi-content-add"></i>
                                <span class="btn-text"> Add Group </span>

                                <div class="ripple-wrapper"></div>
                            </a>
                        </div>
                    </div>
                </div>
                <c:if test="${button eq 'Update'}">
                    <spring:bind path="availableTests">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label for="availableTests" class="col-lg-2 control-label">Available tests</label>

                            <div class="col-lg-10">
                                <form:input path="availableTests" type="text" id="availableTests"
                                            class="form-control" cssClass="form-control"
                                            placeholder="available tests"/>
                                <span class="text-danger"><form:errors path="availableTests" class="control-label"/></span>
                            </div>
                        </div>
                    </spring:bind>
                </c:if>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <a href="/profile/create" class="btn btn-default">Cancel</a>
                        <button type="submit" class="btn btn-primary">${button}</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="dialogs/create-group.jsp"></jsp:include>
<jsp:include page="../views/common/footer.jsp">
    <jsp:param name="page" value="addProfile"></jsp:param>
</jsp:include>
</body>
</html>