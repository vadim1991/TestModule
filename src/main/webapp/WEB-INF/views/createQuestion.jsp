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
<div class="container">
    <div class="jumbotron">
        <c:if test="${not empty msg}">
            <div class="alert alert-dismissable alert-material-cyan-300">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <p>${msg}</p>
            </div>
        </c:if>
        <form:form class="form-horizontal" action="/create/question" method="post" modelAttribute="question">
            <fieldset>
                <h2>Add new Question</h2>

                <div>${success_add}</div>
                <div class="form-group">
                    <label for="title" class="col-lg-2 control-label">Title</label>

                    <div class="col-lg-10">
                        <form:input path="title" type="text" id="title" class="form-control" cssClass="form-control"
                                    placeholder="Title"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="textArea" class="col-lg-2 control-label">Content Question</label>

                    <div class="col-lg-10">
                        <form:textarea path="content" class="form-control" id="textArea" rows="5"/>
                        <span class="help-block">A longer block of help text that breaks onto a new line and may extend beyond one line.</span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="explanation" class="col-lg-2 control-label">Explanation</label>

                    <div class="col-lg-10">
                        <textarea rows="3" class="form-control" id="explanation"></textarea>
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
                    <c:forEach var="i" begin="0" end="${index}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" id="answerLabel${i + 1}">Answer ${i + 1}</label>

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
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <a href="/create/question" class="btn btn-default">Cancel</a>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </div>
            </fieldset>
        </form:form>
    </div>
</div>
<jsp:include page="../views/common/footer.jsp">
    <jsp:param name="page" value="addQuestion"></jsp:param>
</jsp:include>
</body>
</html>