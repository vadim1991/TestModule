<%--
  Created by IntelliJ IDEA.
  User: Вадим
  Date: 04.05.2015
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <script src="js/jquery-1.11.2.min.js"></script>
    <script src="js/jquery.cookie.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
    <script src="js/result.js"></script>
    <script src="js/jquery.json-2.4.js"></script>
</head>
<body>
<div class="container">
    <div class="text-center">
        <div class="text-center">
            <h1>Результат прохождения теста</h1>
        </div>
        <div class="text-center">
            <h3>Ваш результат: </h3>

            <h3>${result}</h3>
        </div>
        <div class="text-center">
            <a class="btn btn-success" href="runTest">Пройти еще раз</a>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-6">
        </div>
    </div>
</div>
<div class="clearfix"></div>
<div id="test" class="test">
    <div class="question">
        <input type="hidden" value="">

        <p>
            <span class="questionTitle"></span>
        </p>

        <p>
        <pre class="java"></pre>
        </p>
    </div>
    <div class="answer">
        <div class="checkbox-inline">
            <span></span>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $.ajax({
        url: "checkTest",
        dataType: "json",
        method: "get",
        data: {
            "show": "showAll"
        },
        success: function (data) {
            var valueFromCookie = $.cookie("result");
            var obj = jQuery.parseJSON(valueFromCookie);
            var errors = obj.errors;
            //var data = {"question":[{"id":1,"content":"Сколько будет 2+2?","answersList":[{"id":2,"flag":0,"content":"2"},{"id":1,"flag":1,"content":"4"},{"id":3,"flag":0,"content":"3"}]},{"id":2,"content":"Сколько пальцев у человека?","answersList":[{"id":4,"flag":1,"content":"5"},{"id":6,"flag":0,"content":"4"},{"id":5,"flag":0,"content":"3"}]},{"id":3,"content":"Сколько пальцеы у человека","answersList":[{"id":8,"flag":1,"content":"5"},{"id":7,"flag":1,"content":"4"},{"id":9,"flag":1,"content":"3"}]}]};
            var content = $(".col-xs-6");
            for (var i = 0; i < data.question.length; i++) {
                var testObj = new ResultObject("Ответьте на вопрос:", i, data.question[i].content, data.question[i].answersList, errors);
                content.append(testObj.build());
            }
        }
    });

    setTimeout(function () {

        var checkbox = $('input[type="checkbox"]'), radio = $('input[type="radio"]'), checkboxCookieName = 'checkbox-state';

        checkbox.each(function () {
            if ($.cookie(checkboxCookieName + '0' + $(this).attr('name')) == "true") {
                $(this).attr('checked', $.cookie(checkboxCookieName + '0' + $(this).attr('name')));
            }
        });
        radio.each(function () {
            if ($(this).attr("value") == $.cookie(checkboxCookieName + '|' + $(this).attr('name'))) {
                $(this).attr('checked', true);
            }
        });
        checkbox.click(function () {
            $.cookie(checkboxCookieName + '0' + $(this).attr('name'), $(this).prop('checked'));
        });
        radio.click(function () {
            $.cookie(checkboxCookieName + '|' + $(this).attr('name'), $(this).attr("value"));
        });
    }, 1000);
</script>
</html>

