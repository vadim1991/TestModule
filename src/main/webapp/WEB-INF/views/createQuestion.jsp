<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../views/common/head.jsp"></jsp:include>
    <title>Add Question</title>
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
        <form:form class="form-horizontal" action="/question/create" method="post" modelAttribute="question">

            <h2>Add new Question</h2>

            <spring:bind path="title">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label for="title" class="col-lg-2 control-label">Title</label>

                    <div class="col-lg-10">
                        <form:input data-validate="required" path="title" type="text" id="title" class="form-control" cssClass="form-control"
                                    placeholder="Title"/>
                        <span class="text-danger"><form:errors path="title" class="control-label"/></span>
                    </div>
                </div>
            </spring:bind>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Category</label>
                <div class="col-sm-5">
                    <form:select data-validate="required" path="category" class="form-control">
                        <form:option value="NONE" label="--- Select ---" />
                        <form:options items="${categories}" itemLabel="title" itemValue="title"/>
                    </form:select>
                    <form:errors path="category" class="control-label" />
                </div>
                <div class="col-sm-4 col-sm-offset-9">
                    <a class="btn btn-primary btn-flat btn-sm" data-toggle="modal" data-target="#complete-dialog">
                        <i class="mdi-content-add"></i>
                        <span class="btn-text"> Add category </span>

                        <div class="ripple-wrapper"></div>
                    </a>
                </div>
            </div>
            <spring:bind path="content">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label for="textArea" class="col-lg-2 control-label">Content Question</label>

                    <div class="col-lg-10">
                        <form:textarea data-validate="required" path="content" class="form-control" id="textArea" rows="5"/>
                        <span class="help-block">The content of question</span>
                        <span class="text-danger"><form:errors path="content" class="control-label"/></span>
                    </div>
                </div>
            </spring:bind>
            <div class="form-group">
                <label for="explanation" class="col-lg-2 control-label">Explanation</label>

                <div class="col-lg-10">
                    <form:textarea path="explanation" rows="3" class="form-control" id="explanation"></form:textarea>
                    <span class="help-block">Explanation for question</span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">Type of Question</label>

                <div class="col-lg-10">
                    <div class="radio radio-primary">
                        <label>
                            <input class="type" type="radio" name="questionType" id="optionsRadios1"
                                   value="CHECKBOX" checked="">
                            Checkbox
                        </label>
                    </div>
                    <div class="radio radio-primary">
                        <label>
                            <input class="type" type="radio" name="questionType" id="optionsRadios2" value="RADIO">
                            Radio
                        </label>
                    </div>
                </div>
            </div>
            <c:set var="index" scope="request" value="2"/>
            <div class="form-group">
                <div class="col-sm-4 col-sm-offset-9">
                    <button type="button" id="add" class="btn btn-primary btn-flat btn-sm">
                        <i class="mdi-content-add"></i>
                        <span class="btn-text"> Add </span>

                        <div class="ripple-wrapper"></div>
                    </button>
                    <button type="button" id="remove-last" class="btn btn-danger btn-flat btn-sm">
                        <i class="mdi-content-clear"></i>
                        <span class="btn-text"> Remove </span>

                        <div class="ripple-wrapper"></div>
                    </button>
                </div>
            </div>
            <div id="answersDiv">
                <spring:bind path="rightAnswers">
                    <c:choose>
                        <c:when test="${not empty question.answers}">
                            <c:forEach items="${question.answers}" var="answer" varStatus="i">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label class="col-sm-2 control-label"
                                           id="answerLabel${i.index + 1}">Answer ${i.index + 1}</label>

                                    <div class="answers col-sm-9" id="answerBlock${i.index + 1}">
                                        <form:input path="answers[${i.index}]" class="form-control"
                                                    type="text"
                                                    placeholder="answer"/>
                                    </div>
                                    <div class="ansButton checkbox col-sm-1">
                                        <label>
                                            <input class="button" type="checkbox" name="rightAnswers"
                                                   value="${i.index}"/>
                                        </label>
                                    </div>
                                    <div class="ansButton radio radio-primary" style="display: none;">
                                        <label>
                                            <input class="button" type="radio" name="rightAnswers" value="${i.index}"/>
                                        </label>
                                    </div>
                                    <c:set var="index" value="${index + 1}"/>
                                </div>
                            </c:forEach>
                            <form:errors path="rightAnswers" class="text-danger"/>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="i" begin="0" end="${index}">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label class="col-sm-2 control-label"
                                           id="answerLabel${i + 1}">Answer ${i + 1}</label>

                                    <div class="answers col-sm-9" id="answerBlock${i + 1}">
                                        <form:input path="answers" class="form-control"
                                                    type="text"
                                                    placeholder="answer"/>
                                    </div>
                                    <div class="ansButton checkbox col-sm-1">
                                        <label>
                                            <input class="button" type="checkbox" name="rightAnswers" value="${i}"/>
                                        </label>
                                    </div>
                                    <div class="ansButton radio radio-primary" style="display: none;">
                                        <label>
                                            <input class="button" type="radio" name="rightAnswers" value="${i}"/>
                                        </label>
                                    </div>
                                    <c:set var="index" value="${index + 1}"/>
                                </div>
                            </c:forEach>
                            <form:errors path="rightAnswers" class="text-danger"/>
                        </c:otherwise>
                    </c:choose>
                </spring:bind>
            </div>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <a href="/create/question" class="btn btn-default">Cancel</a>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </form:form>
    </div>
</div>
<jsp:include page="dialogs/create-category.jsp"></jsp:include>
<jsp:include page="../views/common/footer.jsp">
    <jsp:param name="page" value="addQuestion"></jsp:param>
</jsp:include>
</body>
</html>