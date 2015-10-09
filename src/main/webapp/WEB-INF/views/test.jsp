<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../views/common/head.jsp"></jsp:include>
    <title>Run Test</title>
</head>
<body>
<form:form action="/test/check" modelAttribute="passedTestModel" method="post">

    <div class="container">
        <div class="text-center testing">
            <h1>Тестирование</h1>

            <div>
                Осталось времени:
                <span id="timer" class="timer"></span>
            </div>
            <input type="submit" class="btn btn-success" id="endTest" name="ok" value="Закончить тест">

        </div>

        <div class="row">
            <div class="sidebar">
                <ul class="items">
                    <c:forEach items="${questions}" var="question" varStatus="i">
                        <li>
                            <button type="button" id="first${i.index}" value="${i.index}"
                                    class='buttons btn btn-material-grey-300 btn-sm answer-button'>
                                Вопрос ${i.index + 1}</button>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col-xs-9">
                <c:forEach items="${questions}" var="question" varStatus="i">
                    <c:set var="buttonClass" value="checkbox"></c:set>
                    <c:if test="${question.questionType eq RADIO}">
                        <c:set var="buttonClass" value="radio"></c:set>
                    </c:if>
                    <input type="hidden" name="passedQuestions[${i.index}].id" value="${question.id}">

                    <div id="test${i.index}" class="test hidden">
                        <div class="question">
                            <p>
                                <span class="questionTitle">${question.title}</span>
                            </p>

                            <p>
                            <pre class="java">${question.questionContent}</pre>
                            </p>
                        </div>
                        <div class="answer">
                            <div id="${question.id}">
                                <span>Варианты ответа: </span>
                                <c:forEach items="${question.answers}" var="answer" varStatus="j">
                                    <div class="${buttonClass}">
                                        <label>
                                            <input type="${buttonClass}" id="${question.id}${answer.id}"
                                                   name="passedQuestions[${i.index}].userAnswers" value="${answer.id}">
                                            <span class="answer-text">${answer.answerText}</span>
                                        </label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <a id="${i.index + 1}" class='confirm btn btn-success btn-sm'>Ответить</a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</form:form>
<div class="clearfix"></div>
</body>
<script>
    $(document).ready(function () {
        $.material.init();
    });
</script>
<script type="text/javascript">
    $(document).ready(function () {
        var checkbox = $('input[type="checkbox"]'), radio = $('input[type="radio"]'), checkboxCookieName = 'checkbox-state';

        checkbox.each(function () {
            if ($.cookie(checkboxCookieName + $(this).attr('id')) == "true") {
                $(this).attr('checked', $.cookie(checkboxCookieName + $(this).attr('id')));
            }
        });
        radio.each(function () {
            if ($(this).attr("value") == $.cookie(checkboxCookieName + '|' + $(this).attr('name'))) {
                $(this).attr('checked', true);
            }
        });
        checkbox.click(function () {
            $.cookie(checkboxCookieName + $(this).attr('id'), $(this).prop('checked'), {path: '/'});
        });
        radio.click(function () {
            $.cookie(checkboxCookieName + '|' + $(this).attr('name'), $(this).attr("value"), {path: '/'});
        });

        $(".buttons").each(function () {
            if ($.cookie('active' + $(this).attr('value')) == $(this).attr('value')) {
                $(this).removeClass("btn-warning");
                $(this).addClass("btn-success");
            }
        });
        $(".buttons").click(function () {
            $(".test").hide();
            $(".buttons").removeClass("btn-material-grey-400");
            $(this).addClass("btn-material-grey-400");
            var id = $(this).attr("value");
            var test = $("#test" + id + "");
            test.removeClass("hidden");
            $("#test" + id + "").show("slow");
            $.cookie("current", id, {path: '/'});
        });
        $(".confirm").click(function () {
            var idButton = $.cookie("current");
            var next = ++idButton;
            $("#first" + --idButton).removeClass("btn-material-grey-300");
            $("#first" + idButton).addClass("btn-success");
            $("#first" + next).click();
            $.cookie("active" + idButton, idButton, {path: '/'});
        });
        var currentQuestion = $.cookie("current");
        if (currentQuestion == undefined) {
            $("#first0").click();
        } else {
            $("#first" + currentQuestion).click();
        }
    });

</script>
<script>
    function startTimer(duration, display) {
        var start = Date.now(),
                diff,
                minutes,
                seconds;

        function timer() {
            // get the number of seconds that have elapsed since
            // startTimer() was called
            diff = duration - (((Date.now() - start) / 1000) | 0);

            // does the same job as parseInt truncates the float
            minutes = (diff / 60) | 0;
            seconds = (diff % 60) | 0;

            minutes = minutes < 10 ? "0" + minutes : minutes;
            seconds = seconds < 10 ? "0" + seconds : seconds;

            display.textContent = minutes + ":" + seconds;

            if (diff <= 0) {
                // add one second so that the count down starts at the full duration
                // example 05:00 not 04:59
                $("#endTest").click();
                start = Date.now() + 1000;
            }
        };
        // we don't want to wait a full second before the timer starts
        timer();
        setInterval(timer, 1000);
    }

    window.onload = function () {
        var fiveMinutes = "${timer}",
                display = document.querySelector('#timer');
        startTimer(fiveMinutes, display);
    };
</script>
</html>
