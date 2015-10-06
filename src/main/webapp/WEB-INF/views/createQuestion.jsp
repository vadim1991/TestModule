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
    <div class="page-header"></div>
    <div class="jumbotron">
        <form:form class="form-horizontal" action="/create/question" method="post" modelAttribute="question">
            <fieldset>
                <legend>Add new Question</legend>
                <div>${success_add}</div>
                <div class="form-group">
                    <label for="title" class="col-lg-2 control-label">Title</label>

                    <div class="col-lg-10">
                        <form:input path="title" type="text" class="form-control" cssClass="form-control"
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
                <a id="add" class="btn btn-warning btn-sm">Add Answer</a>
                <a id="remove-last" class="btn btn-danger btn-sm">Remove last</a>

                <div class="form-group" id="answersDiv">
                    <c:forEach var="i" begin="0" end="${index}">
                        <label class="col-lg-2 control-label" id="answerLabel${i + 1}">Answer ${i + 1}</label>

                        <div class="answers col-lg-10" id="answerBlock${i + 1}">
                            <form:input path="answers" class="form-control"
                                        type="text"
                                        placeholder="answer"/>
                            <div class="ansButton checkbox">
                                <label>
                                    <input class="button" type="checkbox" name="rightAnswers" value="${i}"/>
                                </label>
                            </div>
                        </div>
                    </c:forEach>
                    <c:set var="index" value="${index + 1}"/>
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
<jsp:include page="../views/common/footer.jsp"></jsp:include>
<script>
    $(document).ready(function () {
        $.material.init();
    });

</script>
<script>
    $("#add").click(function () {
        var answerDiv = $("#answersDiv");
        var answers = $(".answers");
        var sizeAnswers = answers.size();
        var lastElem = $("#answerBlock" + (sizeAnswers));
        var clone = lastElem.clone();
        var button = clone.find(".button");
        clone.attr("id", "answerBlock" + (sizeAnswers + 1));
        button.attr("value", sizeAnswers);
        answerDiv.append('<label class="col-lg-2 control-label" id=answerLabel' + (sizeAnswers + 1) + '>Answer ' + (sizeAnswers + 1) + '</label>');
        answerDiv.append(clone);
    });
</script>
<script>
    $("#remove-last").click(function () {
        var answers = $(".answers");
        var sizeAnswers = answers.size();
        if (sizeAnswers > 2) {
            var lastElem = $("#answerBlock" + (sizeAnswers));
            var lastLabel = $("#answerLabel" + (sizeAnswers));
            lastElem.remove();
            lastLabel.remove();
        }
    });
</script>
<script>
    $(".type").change(function () {
        var answers = $(".answers");
        if ($(this).attr("value") == "RADIO") {
            answers.each(function (index, element) {
                var elem = $(element);
                var checkbox = elem.find(".checkbox");
                var button = elem.find(".button");
                var codeRadio = setupRadio(button.attr("value"));
                checkbox.remove();
                elem.append(codeRadio);
            });
        }
        if ($(this).attr("value") == "CHECKBOX") {
            answers.each(function (index, element) {
                var elem = $(element);
                var radio = elem.find(".radio");
                var button = elem.find(".button");
                var codeCheckbox = setupCheckBox(button.attr("value"));
                radio.remove();
                elem.append(codeCheckbox);
            });
        }
    });

    function setupRadio(value) {
        return '<div class="ansButton radio radio-primary"><label><input class="button" type="radio" name="rightAnswers"  value=' + value + '><span class="circle"></span><span class="check"></span></label></div>';
    }

    function setupCheckBox(value) {
        return '<div class="ansButton checkbox"><label><input class="button" type="checkbox" name="rightAnswers" value=' + value + '><span class="checkbox-material"><span class="check"></span></span></label></div>';
    }
</script>
</body>
</html>