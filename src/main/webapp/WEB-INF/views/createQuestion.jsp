<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <jsp:include page="../views/common/head.jsp"></jsp:include>
    <title>Add Question</title>
</head>
<body>
<div class="container">
    <div class="page-header"></div>
    <div class="jumbotron">
        <form:form class="form-horizontal" action="/create/question" method="post" modelAttribute="question">
            <fieldset>
                <legend>Add new Question</legend>
                <div>${success_add}</div>
                <div class="form-group">
                    <label for="title" class="col-lg-2 control-label">Title</label>

                    <div class="col-lg-10">
                        <input type="text" name="title" class="form-control" id="title" placeholder="Title">
                    </div>
                </div>
                <div class="form-group">
                    <label for="textArea" class="col-lg-2 control-label">Content Question</label>

                    <div class="col-lg-10">
                        <textarea class="form-control" name="questionContent" rows="3" id="textArea"></textarea>
                        <span class="help-block">A longer block of help text that breaks onto a new line and may extend beyond one line.</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Type of Question</label>

                    <div class="col-lg-10">
                        <div class="radio radio-primary">
                            <label>
                                <input type="radio" name="questionType" id="optionsRadios1" value="CHECKBOX" checked="">
                                Checkbox
                            </label>
                        </div>
                        <div class="radio radio-primary">
                            <label>
                                <input type="radio" name="questionType" id="optionsRadios2" value="RADIO">
                                Radio
                            </label>
                        </div>
                        <div class="radio radio-primary">
                            <label>
                                <input type="radio" name="questionType" id="optionsRadios3" value="REGEXP">
                                Regexp
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <c:forEach items="${question.answers}" varStatus="i">
                        <label class="col-lg-2 control-label">Answer ${i.index + 1}</label>

                        <div class="col-lg-10">
                            <form:input path="answers[${i.index}].answerText" class="form-control"
                                        type="text"
                                        placeholder="Answer"/>
                            <label class="checkbox">
                                <label>
                                    <form:checkbox path="answers[${i.index}].rightAnswer" id="rightAnswer"/>
                                </label>
                            </label>
                        </div>
                    </c:forEach>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button class="btn btn-default">Cancel</button>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </div>
            </fieldset>
        </form:form>

    </div>
</div>
<jsp:include page="../views/common/footer.jsp"></jsp:include>
<script>
    $(document).ready(function () {
        // This command is used to initialize some elements and make them work properly
        $.material.init();
    });

</script>
<script>
    $("#addNew").click(function () {
        var toAdd = $("#toAdd");
        var cloneObject = $("#answer").clone();
        toAdd.append(cloneObject);
    });

</script>
</body>
</html>